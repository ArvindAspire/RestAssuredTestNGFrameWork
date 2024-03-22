package com.spotify.oauth2.tests;


import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.pojo.PlaylistPojo;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.applicationApi.PlaylistApi.*;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseClass{

    public static  PlaylistPojo playListBuilder(String name, String _description, Boolean scope){


     return     PlaylistPojo.builder()
                .name(name)
                .description(_description)
                ._public(scope).build();

    }

    @Step
    public static void assertPlaylist(PlaylistPojo playlistPojo,PlaylistPojo playlistPojoResponse ){
        assertThat(playlistPojoResponse.getName(),equalTo(playlistPojo.getName()));
        assertThat(playlistPojoResponse.getDescription(),equalTo(playlistPojo.getDescription()));
        assertThat(playlistPojoResponse.get_public(),equalTo(playlistPojo.get_public()));
    }

    @Step
    public static void statusCode(int actualStatusCode, StatusCode expectedStatusCode){
        assertThat(actualStatusCode,equalTo(expectedStatusCode.code));
    }

    @Description("The test create the playlist")
    @Test(description = "Should be able to create playlist")
    public void ableToCreatePlayList() throws Exception {


        PlaylistPojo playlistPojo= playListBuilder(generateName(),generateDescription(),true);

        Response response =post(playlistPojo);
        statusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylist(response.as(PlaylistPojo.class),playlistPojo);


    }


    @Description("The test gets the playlist")
    @Test(description = "Able to get the playlist")
    public void ableToGetPlaylist() throws Exception {

        PlaylistPojo playlistPojo= playListBuilder("New Playlist today","New playlist description today",true);

        Response response = get(DataLoader.getInstance().get_playlist_id());
        statusCode(response.statusCode(),StatusCode.CODE_200);
        assertPlaylist(response.as(PlaylistPojo.class),playlistPojo);


    }

    @Description("The test update the playlist")
    @Test(description = "Able to update the playlist")
    public void ableToUpdatePlaylist() throws Exception {

        PlaylistPojo playlistPojo= playListBuilder(generateName(),generateDescription(),true);

        Response response = put(playlistPojo, DataLoader.getInstance().update_playlist_id());
        statusCode(response.statusCode(),StatusCode.CODE_200);


    }


}
