package com.myapplication.ormlite.orm;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.myapplication.ormlite.orm.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;

import static com.myapplication.MyApplication.databaseHelper;


public class DatabaseUtil {


    //Get All Saved Colors
    public static List<User> getAllUsers() throws SQLException {
        return databaseHelper.getUserDao().queryForAll();
    }

    //Add Color
    public static int insertUser(User user) throws SQLException {
        Dao<User, Integer> userDao = databaseHelper.getUserDao();
        return userDao.create(user);

    }


    public static User getUserByName(String name) throws SQLException {
        return databaseHelper.getUserDao().queryBuilder().where().in("userName", name).query().get(0);
    }


    public static void clearUserTable() throws SQLException {
        databaseHelper.delteUserTable();
    }

    public static int updateUser(String name) throws SQLException {
        try {

            UpdateBuilder<User, Integer> updateBuilder = databaseHelper.getUserDao().updateBuilder();
            updateBuilder.where().in("userName", name);
            int id = updateBuilder.update();

            return id;
        } catch (SQLException e) {
            return 0;
        }
    }


    public static int deleteUserByName(int userId) throws SQLException {
        Dao<User, Integer> dao = databaseHelper.getUserDao();
        int id = dao.deleteById(userId);
        return id;
    }


    public static void updateDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
    }
  /*  //Get All families
    public static List<Family> getFamilyById(int id) throws SQLException {
        return databaseHelper.getFamilyDao().queryBuilder().where().in("_id", id).query();
    }

    public static List<Family> getFamilyByRGB(String r, String g, String b) throws SQLException {
        return databaseHelper.getFamilyDao().queryBuilder().where().in("R", r).and().eq("G", g).and().eq("B", b).query();
    }


    public static Family getRGBByFamilyName(String familyName) throws SQLException {
        return databaseHelper.getFamilyDao().queryBuilder().where().in("family_name", familyName).query().get(0);
    }

    //Add a family
    public static int addFamily(Family family) throws SQLException {
        Dao<Family, Integer> familyDao = databaseHelper.getFamilyDao();
        familyDao.create(family);
        return family.getId();
    }


    //get All Colors by CFamily
    public static List<SavedColors.Colors> getColorsByFamily(int familyId) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().where().in("family_id", familyId).query();
        return colorsList;
    }


    //get All Colors by CFamily
    public static List<SavedColors.Colors> getColorsByFamilySortByPageNo(int familyId) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().orderBy("page_no", true).where().in("family_id", familyId).query();
        return colorsList;
    }

   *//* public static List<SavedColors.Colors> getColorsByFamilyByFNameSortByPageNo(int familyName) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().orderBy("page_no", true).where().in("family_id", familyId).query();
        return colorsList;
    }*//*


    //get All Colors by CFamily
    public static List<SavedColors.Colors> getColorsByFamilyIDPageNoId(int familyId, int pageId) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().where().in("family_id", familyId).and().eq("page_no", pageId).query();
        return colorsList;
    }


    // get a layer by project Id
    public static ArrayList<String> getColorFamilyNameByCategory(String category) throws SQLException {
        List<Family> familyList = databaseHelper.getFamilyDao().queryBuilder().where().in("category", category).query();

        ArrayList<String> familyNameList = new ArrayList<String>();
        for (int i = 0; i < familyList.size(); i++) {
            familyNameList.add(familyList.get(i).getFamilyName());
        }
        return familyNameList;
    }


    // get All Colors By Category
    public static List<SavedColors.Colors> getColorByCategory(String category) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().where().in("category", category).query();
        return colorsList;
    }

    //get Nearest Color
    public static List<SavedColors.Colors> getNearestColor(int r, int g, int b) throws SQLException {

//        Log.e("color_search", "R-" + r + "G-" + g + "B-" + b);
        List<SavedColors.Colors> colorsList = new ArrayList<>();
        colorsList = getAllColors();
        SavedColors.Colors nearestColor = null;
        int minDistance = 100;

        for (int i = 0; i < colorsList.size(); i++) {
            SavedColors.Colors rootColor = colorsList.get(i);
            int ana_red = Integer.parseInt(rootColor.getR());
            int ana_green = Integer.parseInt(rootColor.getG());
            int ana_blue = Integer.parseInt(rootColor.getB());

            int red_diff = Math.abs(r - ana_red);
            int green_diff = Math.abs(g - ana_green);
            int blue_diff = Math.abs(b - ana_blue);


            int colourDistance = (int) Math.sqrt(Math.pow(red_diff, 2) + Math.pow(green_diff, 2) + Math.pow(blue_diff, 2));
            if (colourDistance <= minDistance) {
                minDistance = colourDistance;
                nearestColor = rootColor;
                Log.e("near", "R-" + nearestColor.getR() + "G-" + nearestColor.getG() + "B-" + nearestColor.getB());
            }
        }
        if (nearestColor != null) {
            colorsList.clear();
            colorsList.add(nearestColor);
            return colorsList;
        }

        return null;
    }


    public static List<SavedColors.Colors> getColorsByCode(String shadeCode) {
        try {
            QueryBuilder<SavedColors.Colors, Integer> qb = databaseHelper.getSavedColorsDao().queryBuilder();
            qb.where().like("shade_code", "%" + shadeCode + "%");
            PreparedQuery<SavedColors.Colors> pq = qb.prepare();
            return databaseHelper.getSavedColorsDao().query(pq);
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<SavedColors.Colors> getColorByShadeCode(String shadeCode) {
        try {
            QueryBuilder<SavedColors.Colors, Integer> qb = databaseHelper.getSavedColorsDao().queryBuilder();
            qb.where().eq("shade_code", shadeCode);
            PreparedQuery<SavedColors.Colors> pq = qb.prepare();

            databaseHelper.getSavedColorsDao().query(pq);
            return databaseHelper.getSavedColorsDao().query(pq);
        } catch (SQLException e) {
            return null;
        }
    }

    // get all favourite Color
    public static List<SavedColors.Colors> getAllFavorite() {
        List<SavedColors.Colors> colorsList = null;
        try {
            colorsList = databaseHelper.getSavedColorsDao().queryBuilder().where().in("favourite", true).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorsList;
    }

    //get AP colors by Name
    public static List<SavedColors.Colors> getColorsByNameOrCode(String colorName) {
        try {
            QueryBuilder<SavedColors.Colors, Integer> qb = databaseHelper.getSavedColorsDao().queryBuilder();
            qb.where().like("shade_name", "%" + colorName + "%").or().like("shade_code", "%" + colorName + "%");
            PreparedQuery<SavedColors.Colors> pq = qb.prepare();
            return databaseHelper.getSavedColorsDao().query(pq);
        } catch (SQLException e) {
            return null;
        }
    }

    // clear all tables data
    public static void clearAllTables() throws SQLException {
        databaseHelper.deleteAllDetails();
    }


    // clear colors table
    public static void clearAllColors() throws SQLException {
        databaseHelper.deleteAllColors();
    }

    // clear Families table
    public static void clearAllFamilies() throws SQLException {
        databaseHelper.deleteAllFamilies();
    }


    //get All Colors by CFamily
    public static SavedColors.Colors getColorByScrollingPosition(String scrollingPostion) throws SQLException {
        List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().where().in("scrolling_position", scrollingPostion).query();
        //SavedColors.Colors color=null;
        if (colorsList != null && colorsList.size() > 0) {
            return colorsList.get(0);
        }
        return null;
    }


    //Get All textures
    public static List<TextureModel.Texture> getAllTextures() throws SQLException {
        return databaseHelper.getTextureDao().queryForAll();
    }

    //Add All Textures
    public static void addAllTextures(final List<TextureModel.Texture> addAllTextures) throws Exception {
        final Dao<TextureModel.Texture, Integer> textureDao = databaseHelper.getTextureDao();

        textureDao.callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                for (TextureModel.Texture textureData : addAllTextures) {
                    textureDao.create(textureData);
                }
                return null;
            }
        });
    }


    //GetAll Textures Category
    public static List<TextureModel.Texture> getAllTexturesCategory() {
        try {
            QueryBuilder<TextureModel.Texture, Integer> qb = databaseHelper.getTextureDao().queryBuilder();
            qb.distinct().selectColumns("textureCategory");
            PreparedQuery<TextureModel.Texture> pq = qb.prepare();
            return databaseHelper.getTextureDao().query(pq);
        } catch (SQLException e) {

            return null;
        }

    }


    // Get Textures by Category
    public static List<TextureModel.Texture> getAllTexturesByCategory(String category) {
        try {
            QueryBuilder<TextureModel.Texture, Integer> qb = databaseHelper.getTextureDao().queryBuilder();
            qb.where().eq("textureCategory", category);
            PreparedQuery<TextureModel.Texture> pq = qb.prepare();
            return databaseHelper.getTextureDao().query(pq);
        } catch (SQLException e) {
            return null;
        }
    }


    //get Textures by Id
    public static TextureModel.Texture getTexturesById(String textureId) throws SQLException {
        List<TextureModel.Texture> texture = databaseHelper.getTextureDao().queryBuilder().where().in("textureId", textureId).query();
        if (texture != null && texture.size() > 0)
            return texture.get(0);
        else
            return null;
    }

    // clear Texture Table
    public static void clearAllTextures() throws SQLException {
        databaseHelper.deleteAllTextures();
    }


    //Get All Layesrs
    public static List<TextureModel.Layer> getAllLayers() throws SQLException {
        return databaseHelper.getLayerDao().queryForAll();
    }

    //Add All Layers
    public static void addAllLayers(final List<TextureModel.Layer> addAllLayers) throws Exception {
        final Dao<TextureModel.Layer, Integer> layerDao = databaseHelper.getLayerDao();

        layerDao.callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                for (TextureModel.Layer layerData : addAllLayers) {
                    layerDao.create(layerData);
                }
                return null;
            }
        });
    }

    //get Layers by TextureId
    public static List<TextureModel.Layer> getLayersByTextureId(String textureId) throws SQLException {
        List<TextureModel.Layer> textureList = databaseHelper.getLayerDao().queryBuilder().where().in("textureId", textureId).query();
        return textureList;
    }

    // clear LayersTable
    public static void clearAllLayers() throws SQLException {
        databaseHelper.deleteAllLayers();
        ;
    }


    //Get All Combination Layers
    public static List<TextureModel.CombLayers> getAllCombLayers() throws SQLException {
        return databaseHelper.getCombLayerDao().queryForAll();
    }

    //Add All Combination ProjectLayer
    public static void addAllCombLayers(final List<TextureModel.CombLayers> addAllCombLayers) throws Exception {
        final Dao<TextureModel.CombLayers, Integer> combLayerDao = databaseHelper.getCombLayerDao();

        combLayerDao.callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                for (TextureModel.CombLayers combLayerData : addAllCombLayers) {
                    combLayerDao.create(combLayerData);
                }
                return null;
            }
        });
    }


    //GetAll Unique Combination
    public static List<TextureModel.CombLayers> getAllDistinctCombination() {
        try {
            QueryBuilder<TextureModel.CombLayers, Integer> qb = databaseHelper.getCombLayerDao().queryBuilder();
            qb.distinct().selectColumns("combinationId");
            PreparedQuery<TextureModel.CombLayers> pq = qb.prepare();
            return databaseHelper.getCombLayerDao().query(pq);
        } catch (SQLException e) {

            return null;
        }

    }

    //get combinationLayers by TextureId
    public static List<TextureModel.CombLayers> getCombLayersByTextureId(String textureId) throws SQLException {
        List<TextureModel.CombLayers> combLayerList = databaseHelper.getCombLayerDao().queryBuilder().where().in("textureId", textureId).query();
        return combLayerList;
    }


    public static List<TextureModel.CombLayers> getCombLayersByTextureIdAndCombID(String textureId, String combId) throws SQLException {
        //        return databaseHelper.getFamilyDao().queryBuilder().where().in("R", r).and().eq("G", g).and().eq("B", b).query();

        List<TextureModel.CombLayers> combLayerList = databaseHelper.getCombLayerDao().queryBuilder().where().in("textureId", textureId).and().eq("combinationId", combId).query();
        return combLayerList;
    }


   *//* public static List<TextureModel.Combination> getAllCombinations(String textureId) throws SQLException {
        //        return databaseHelper.getFamilyDao().queryBuilder().where().in("R", r).and().eq("G", g).and().eq("B", b).query();

        List<TextureModel.Combination> combLayerList = databaseHelper.get().queryBuilder().where().in("textureId", textureId).and().eq("combinationId", combId).query();
        return combLayerList;
    }
*//*

    //get combinationLayers by CombId
    public static List<TextureModel.CombLayers> getCombLayersByCombinationId(String combinationId) throws SQLException {
        List<TextureModel.CombLayers> combLayerList = databaseHelper.getCombLayerDao().queryBuilder().where().in("combinationId", combinationId).query();
        return combLayerList;
    }

    // clear Combination Table
    public static void clearAllCombLayers() throws SQLException {
        databaseHelper.deleteAllCombLayers();
    }


    // Get ApiSync Status
    public static ApiSync getApiSync() throws SQLException {
        List<ApiSync> apiSyncList = databaseHelper.getApiSyncDao().queryForAll();

        return apiSyncList.get(0);

    }

    //Add Api Sync
    public static int addApiSync(ApiSync apiSync) throws Exception {
        clearApiSync();
        Dao<ApiSync, Integer> apiDao = databaseHelper.getApiSyncDao();
        return apiDao.create(apiSync);
    }

    // clear Combination Table
    public static void clearApiSync() throws SQLException {
        databaseHelper.deleteApiSync();
    }


    //Get All Texture Categories
    public static List<TextureModel.Categories> getAllTextureCategories() throws SQLException {
        return databaseHelper.getTextureCategoryDao().queryForAll();
    }

    //Add All Texture Categories
    public static void addAllTextureCategory(final List<TextureModel.Categories> textureCategoryList) throws Exception {
        final Dao<TextureModel.Categories, Integer> textureCategoryDao = databaseHelper.getTextureCategoryDao();

        textureCategoryDao.callBatchTasks(new Callable<Void>() {
            public Void call() throws Exception {
                for (TextureModel.Categories textureCategory : textureCategoryList) {
                    textureCategoryDao.create(textureCategory);
                }
                return null;
            }
        });
    }

    // delete Texture Category
    public static void clearTextureCategory() throws SQLException {
        databaseHelper.deleteAllTextureCategory();
    }


    // LB Inspirations
    public static int insertLBInspirations(LBInspiration lbInspiration) throws SQLException {
        Dao<LBInspiration, Integer> inspirationsDao = databaseHelper.getLbInspirationsDao();
        inspirationsDao.create(lbInspiration);
        return lbInspiration.getInspirationId();
    }


    // LB Colours
    public static int insertLBColour(LBColours lbColours) throws SQLException {
        Dao<LBColours, Integer> coloursDao = databaseHelper.getLbColoursDao();
        coloursDao.create(lbColours);
        return lbColours.getColourId();
    }


    // LB Textures
    public static int insertLBTextures(LBTextures lbTextures) throws SQLException {
        Dao<LBTextures, Integer> texturesDao = databaseHelper.getLbTexturesDao();
        texturesDao.create(lbTextures);
        return lbTextures.getTextureId();
    }

    public static List<LBInspiration> getAllLBInspirations() throws SQLException {
        List<LBInspiration> inspirationList = databaseHelper.getLbInspirationsDao().queryBuilder().orderBy("timestamp", false).query();

        return inspirationList;
    }

    // delete Layers by project Id
    public static int deleteLBInspiration(int inspirationId) throws SQLException {
        try {
            DeleteBuilder<LBInspiration, Integer> qb = databaseHelper.getLbInspirationsDao().deleteBuilder();
            qb.where().eq("inspirationId", inspirationId);
            int deleteId = qb.delete();

            return deleteId;
        } catch (SQLException e) {
            return 0;
        }
    }


    public static List<LBColours> getLookBookColours() throws SQLException {
        //         List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().orderBy("page_no", true).where().in("family_id", familyId).query();

        List<LBColours> colorsList = databaseHelper.getLbColoursDao().queryBuilder().orderBy("timestamp", false).query();

        return colorsList;
    }


    public static List<LBColours> getLookBookColoursAsPerInspirationId(int inspirationId) throws SQLException {
        //         List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().orderBy("page_no", true).where().in("family_id", familyId).query();

        List<LBColours> colorsList = databaseHelper.getLbColoursDao().queryBuilder().orderBy("timestamp", false).where().in("inspirationId", inspirationId).query();

        return colorsList;
    }


    public static List<LBTextures> getAllLookBookTextures() throws SQLException {
        List<LBTextures> texturesList = databaseHelper.getLbTexturesDao().queryBuilder().orderBy("timestamp", false).query();

        return texturesList;
    }


    public static List<LBTextures> getLookBookTexturesAsPerInspirationId(int inspirationId) throws SQLException {
        //         List<SavedColors.Colors> colorsList = databaseHelper.getSavedColorsDao().queryBuilder().orderBy("page_no", true).where().in("family_id", familyId).query();

        List<LBTextures> texturesList = databaseHelper.getLbTexturesDao().queryBuilder().orderBy("timestamp", false).where().in("inspirationId", inspirationId).query();

        return texturesList;
    }

   *//* public static List<LBColours> getLookBookOnlyColours(String titleName) throws SQLException {
        List<LBColours> colorsList = databaseHelper.getLbColoursDao().queryBuilder().where().in("titleName", titleName).query();

        return colorsList;
    }



    public static List<LBColours> getLookBookColoursDistinct() throws SQLException {
        List<LBColours> results = databaseHelper.getLbColoursDao().queryBuilder()
                .distinct().selectColumns("titleName").query();

        return results;
    }*//*

    public static int updateLBColourDB(int inspirationId, String shadeCode) throws SQLException {
        try {
            UpdateBuilder<LBColours, Integer> updateBuilder = databaseHelper.getLbColoursDao().updateBuilder();
            updateBuilder.updateColumnValue("inspirationId", inspirationId);

            updateBuilder.where().eq("shadeCode", shadeCode);
            int id = updateBuilder.update();

            return id;
        } catch (SQLException e) {
            return 0;
        }
    }


    public static int updateLBTextureDB(int inspirationId, String textureCode, String CombId) throws SQLException {
        try {

            UpdateBuilder<LBTextures, Integer> updateBuilder = databaseHelper.getLbTexturesDao().updateBuilder();
            updateBuilder.updateColumnValue("inspirationId", inspirationId);
            updateBuilder.where().in("textureCode", textureCode).and().eq("combId", CombId);
            int id = updateBuilder.update();

            return id;
        } catch (SQLException e) {
            return 0;
        }
    }

    *//*************************************** get projects  *****************************************//*


    public static List<APProjectFolder> getAPProjectFolderByName(String projectName) throws SQLException {

        List<APProjectFolder> texturesList = databaseHelper.getAPProjectsFolderDao().queryBuilder().where().in("folderName", projectName).query();
        return texturesList;

    }

    // intsert projectFolder
    public static APProjectFolder insertProjectFolder(APProjectFolder apProjectFolder) throws SQLException {
        Dao<APProjectFolder, Integer> apProjectsFolderDao = databaseHelper.getAPProjectsFolderDao();
        apProjectsFolderDao.create(apProjectFolder);
        return apProjectFolder;
    }

    public static int insertProject(APProjects apProject) throws SQLException {
        Dao<APProjects, Integer> apProjectsFolderDao = databaseHelper.getAPProjectsDao();
        apProjectsFolderDao.create(apProject);
        return apProject.getProjectId();
    }

    public static APLayer insertOverlay(APLayer apProject) throws SQLException {
        Dao<APLayer, Integer> apProjectsFolderDao = databaseHelper.getAPLayerDao();
        apProjectsFolderDao.create(apProject);
        return apProject;
    }

    public static List<APProjectFolder> getAllAPProjectFolders() throws SQLException {
        List<APProjectFolder> apProjectFolderList = databaseHelper.getAPProjectsFolderDao().queryBuilder().query();

        return apProjectFolderList;
    }

    public static List<APProjects> getProjectsByFolderName(String folderName) throws SQLException {
        List<APProjects> apProjectFolderList = databaseHelper.getAPProjectsDao().queryBuilder().where().in("folderName", folderName).query();
        return apProjectFolderList;
    }

    public static List<APLayer> getLayerByProjectId(String projectid) throws SQLException {
        List<APLayer> apLayers = databaseHelper.getAPLayerDao().queryBuilder().where().in("projectId", projectid).query();
        return apLayers;
    }*/

}



