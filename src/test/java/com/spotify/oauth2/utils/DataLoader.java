package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {

    private  final Properties properties;
    private static DataLoader dataLoader = null;

    private DataLoader(){
        properties = PropertyUtils.PropertyLoader("src/test/resources/data.properties");
    }

    public  static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader= new DataLoader();
        }
        return dataLoader;
    }

    public String get_playlist_id(){
        String prop = properties.getProperty("get_playlist_id");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property get_playlist_id is not specified in config property file");
    }

    public String update_playlist_id(){
        String prop = properties.getProperty("update_playlist_id");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property update_playlist_id is not specified in config property file");
    }



}
