package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.PlaylistPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(Object playlistPojo,String path,String accessToken){

        return given(getRequestSpecification())
                .body(playlistPojo)
                .auth().oauth2(accessToken)
                .when()
                .post(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();


    }

    public static Response postAccount(HashMap<String,String> formParam){
       return given(getAccountRequestSpecification())
               .formParams(formParam)
                .when()
                .post(API+TOKEN)
                .then().spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response get(String accessToken,String path){

        return  given(getRequestSpecification())
                .auth().oauth2(accessToken)
                .when()
                .get(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();

    }

    public static Response put(Object playlistPojo, String accessToken, String path){

        return given(getRequestSpecification())
                .body(playlistPojo)
                .auth().oauth2(accessToken)
                .when()
                .put(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();
    }
}
