package Controller;

import Model.GoogleAccount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.Iconstant;
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
        try {
            // Gửi request để lấy access token
            String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                    .bodyForm(
                            Form.form()
                                    .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                    .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                    .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                    .add("code", code)
                                    .add("grant_type", "authorization_code")
                                    .build()
                    )
                    .execute()
                    .returnContent()
                    .asString();

            // Parse JSON response
            JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
            
            if (jobj.has("access_token")) {
                return jobj.get("access_token").getAsString();
            } else {
                System.err.println("Error: Không lấy được access_token từ response!");
                System.err.println("Response từ Google: " + response);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lấy thông tin tài khoản Google bằng access token
     * @param accessToken access token của người dùng
     * @return đối tượng GoogleAccount chứa thông tin người dùng
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        try {
            // Kiểm tra access token có hợp lệ không
            if (accessToken == null || accessToken.isEmpty()) {
                System.err.println("Error: Access token không hợp lệ!");
                return null;
            }

            // Gửi request lấy thông tin người dùng từ Google
            String response = Request.Get(Iconstant.GOOGLE_LINK_GET_USER_INFO + "?access_token=" + accessToken)
                    .execute()
                    .returnContent()
                    .asString();

            // Kiểm tra nếu response có dữ liệu hợp lệ
            if (response == null || response.isEmpty()) {
                System.err.println("Error: Không nhận được dữ liệu người dùng từ Google!");
                return null;
            }

            // Parse JSON thành GoogleAccount object
            GoogleAccount googleAccount = new Gson().fromJson(response, GoogleAccount.class);
            return googleAccount;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}