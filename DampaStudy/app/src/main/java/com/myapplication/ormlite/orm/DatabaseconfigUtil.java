package com.myapplication.ormlite.orm;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.myapplication.ormlite.orm.model.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseconfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[]{
            User.class,
    };

    public static void main(String[] args) throws SQLException, IOException {
        File conffile = new File("app/src/main/res/raw/ormlite_config.txt");
        writeConfigFile(conffile, classes);
    }
}
