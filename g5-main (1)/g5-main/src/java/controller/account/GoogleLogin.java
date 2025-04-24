package controller.account;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import Constant.Iconstant;
import Model.GoogleAccount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 * Xử lý đăng nhập bằng Google
 * @author Doan Quan
 */
public class GoogleLogin {
    /**
     * Lấy access token từ Google OAuth 2.0
     * @param code mã xác thực từ Google
     * @return access token
     * @throws ClientProtocolException
     * @throws IOException
     */
     public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
       .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                        .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                        .build()
                )
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);
        return googlePojo;
    }
}