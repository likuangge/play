package zuo.li.play.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Description: OCR工具类
 * @Author:
 * @Date: 2020/1/7 10:20
 */
@Slf4j
public class OCRUtils {

    /**
     * 权限host
     */
    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";

    /**
     * API KEY
     */
    private static final String API_KEY = "QXQu91ELKEakkPYCXGn3DtrK";

    /**
     * Secret Key
     */
    private static final String SECRET_KEY = "zSC9vMOhQRDKQ17GZ0IobCe0RD9ZrfaG";

    /**
     * 获取API访问token
     *
     * @return assess_token
     */
    public static String getAccessToken() {
        try {
            // 获取token地址
            String getAccessTokenUrl = AUTH_HOST + "grant_type=client_credentials" + "&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new RuntimeException("获取token失败");
        }
    }
}
