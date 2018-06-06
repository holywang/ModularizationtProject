package com.holy.dataprovider.database;

/**
 * Created by DR on 2018/6/6.
 */

public class DatabaseHelper {

    private static DatabaseHelper instance;
    private String url;


    private DatabaseHelper(){
    }

    public static DatabaseHelper getInstance(){
        if(instance == null){
            synchronized(DatabaseHelper.class){
                if(instance == null){
                    instance = new DatabaseHelper ();
                }
            }
        }
        return instance;
    }
}
