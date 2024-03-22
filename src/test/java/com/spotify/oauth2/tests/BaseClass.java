package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseClass {

    @BeforeMethod
    public  void beforeMethod(Method m){
        System.out.println("Starting method : "+m.getName());
        System.out.println("ThreadID : "+Thread.currentThread().getId());
    }
}
