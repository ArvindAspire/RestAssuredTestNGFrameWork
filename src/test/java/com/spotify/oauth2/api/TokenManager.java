package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.RestResource.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_Token;

    private static Instant expiry_time;


    public static  String accessToken() throws Exception {

        try {

            if (access_Token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing Token...");
                Response response = refreshToken();
                int expiryDuration = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDuration - 500);
                access_Token= response.path("access_token");

            } else {
               System.out.println("Token is good to use.");
            }

        }
        catch(Exception e){
          throw new RuntimeException("ABORT!!!  Unable to get Token.");
            }
        return access_Token;

    }



    private synchronized static Response refreshToken() throws Exception {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("grant_type", ConfigLoader.getInstance().grant_type());
        hashMap.put("refresh_token",ConfigLoader.getInstance().refresh_token());
        hashMap.put("client_id",ConfigLoader.getInstance().client_id());
        hashMap.put("client_secret",ConfigLoader.getInstance().client_secret());

        Response response=postAccount(hashMap);


        if(response.statusCode() != 200){
            throw new Exception("ABORT!!! Renew Expired Token.");
        }
        return response;
    }

}
