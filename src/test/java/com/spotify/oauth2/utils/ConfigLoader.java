package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private  final Properties properties;
    private static ConfigLoader configLoader = null;

    private ConfigLoader(){
        properties = PropertyUtils.PropertyLoader("src/test/resources/config.properties");
    }

    public  static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader= new ConfigLoader();
        }
        return configLoader;
    }

    public String client_id(){
        String prop = properties.getProperty("client_id");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property client_id is not specified in config property file");
    }

    public String client_secret(){
        String prop = properties.getProperty("client_secret");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property client_secret is not specified in config property file");
    }
    public String refresh_token(){
        String prop = properties.getProperty("refresh_token");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property refresh_token is not specified in config property file");
    }
    public String grant_type(){
        String prop = properties.getProperty("grant_type");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property grant_type is not specified in config property file");
    }
    public String user_id(){
        String prop = properties.getProperty("user_id");
        if(prop!= null) return prop;
        else throw new RuntimeException("Property user_id is not specified in config property file");
    }


}
