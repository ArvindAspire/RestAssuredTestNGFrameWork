package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlaylistPojo;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.accessToken;

public class PlaylistApi {

   // static String accessToken = "BQCiLo9g1jpZO4RoX8i26LQx8mb--rEIfHaAYGeuVT7vfjThJrdvyQsTqJSFZEL0qeTnvEUZ8avvu0uzhBquQrX4HH4HOPOy2nSWCIGhZ10Q7X_14yPRk9QzafHP3GWzCAu7W_-YBzpFiwsJ-4trYgjwg3QbeKlA03UlQZkGP9NQ7wV_WKDAZYEWYiBPRSS0iKGTcO1QjsLPAxlDfIZcqrZKa0SY_KOgdrtGdesRHGLoFUZMsIsLy1gOzuQK7vuQBGPkYbee6aGD";


    @Step
    public static Response post(PlaylistPojo playlistPojo) throws Exception {

         return RestResource.post(playlistPojo, USERS +"/"+ ConfigLoader.getInstance().user_id() +PLAYLISTS, accessToken());

    }

    public static Response get(String playlistID) throws Exception {

        return RestResource.get(accessToken(),PLAYLISTS+"/"+playlistID);


    }

    public static Response put(Object playlistPojo, String playlistID) throws Exception {

        return RestResource.put(playlistPojo,accessToken(),PLAYLISTS+"/"+playlistID);

    }

}
