package zuo.li.play.ocr.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.common.enums.FileFieldEnum;
import zuo.li.play.common.utils.Base64Utils;
import zuo.li.play.common.utils.FileUtils;
import zuo.li.play.common.utils.HttpUtils;
import zuo.li.play.ocr.core.ao.FileAO;
import zuo.li.play.ocr.core.bo.FileBO;
import zuo.li.play.ocr.core.entity.FileDetailDO;
import zuo.li.play.ocr.core.vo.FileVO;
import zuo.li.play.ocr.dao.FileDao;
import zuo.li.play.ocr.service.FileService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 文件service实现
 * @Author: zuo.li
 * @Date: 2020/1/4 11:29
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    /**
     * 文件dao
     */
    @Autowired
    private FileDao fileDao;

    /**
     * iocr识别apiUrl
     */
    private static final String RECOGNIZE_URL = "https://aip.baidubce.com/rest/2.0/solution/v1/iocr/recognise";

    /**
     * 权限host
     */
    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";

    /**
     * 模板id
     */
    private static final String TEMPLATE_SIGN = "bd429374c58e802dd1b0dede60bbc313";

    /**
     * API KEY
     */
    private static final String API_KEY = "QXQu91ELKEakkPYCXGn3DtrK";

    /**
     * Secret Key
     */
    private static final String SECRET_KEY = "zSC9vMOhQRDKQ17GZ0IobCe0RD9ZrfaG";

    @Override
    public PaginationResult<FileVO> fileList(FileBO fileBO) {
        List<FileVO> fileVOS = fileDao.getFileList(fileBO);
        Long count = fileDao.countFile(fileBO);
        return new PaginationResult<>(count, fileVOS);
    }

    @Override
    public void fileAnalysis(FileAO fileAO) {
        try {
            URL url = new URL(fileAO.getFileUrl());
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            int fileLength = httpURLConnection.getContentLength();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(fileLength);
            byte[] buffer = new byte[1024];
            int len1;
            while (-1 != (len1 = bufferedInputStream.read(buffer, 0, 1024))) {
                byteArrayOutputStream.write(buffer, 0, len1);
            }

            byte[] imgData = byteArrayOutputStream.toByteArray();
            String imgStr = Base64Utils.encode(imgData);
            // 请求模板参数
            String recogniseParams = "templateSign=" + TEMPLATE_SIGN + "&image=" + URLEncoder.encode(imgStr, "UTF-8");
            String accessToken = getAccessToken();
            String result = HttpUtils.post(RECOGNIZE_URL, accessToken, recogniseParams);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("ret");
            Map<String, String> map = new HashMap<>();
            for(int i = 0;i < jsonArray.length();i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                map.put(jsonObject2.getString("word_name"), jsonObject2.getString("word"));
            }
            FileDetailDO fileDetailDO = new FileDetailDO();
            fileDetailDO.setFileId(fileAO.getFileId());
            fileDetailDO.setBusinessScope(map.get(FileFieldEnum.BUSINESS_SCOPE.getName()));
            fileDetailDO.setCreateDate(map.get(FileFieldEnum.CREATE_DATE.getName()));
            fileDetailDO.setName(map.get(FileFieldEnum.NAME.getName()));
            fileDetailDO.setOperatingPeriod(map.get(FileFieldEnum.OPERATING_PERIOD.getName()));
            fileDetailDO.setOperatingPlace(map.get(FileFieldEnum.OPERATING_PLACE.getName()));
            fileDetailDO.setPrincipal(map.get(FileFieldEnum.PRINCIPAL.getName()));
            fileDetailDO.setSocietyCode(map.get(FileFieldEnum.SOCIETY_CODE.getName()));
            fileDetailDO.setType(map.get(FileFieldEnum.TYPE.getName()));
            if(Objects.isNull(fileDao.getFileDetailByFileId(fileAO.getFileId()))) {
                fileDao.insertFileDetail(fileDetailDO);
            } else {
                fileDao.updateFileDetail(fileDetailDO);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public FileDetailDO getFileDetailByFileId(Long fileId) {
        return fileDao.getFileDetailByFileId(fileId);
    }

    /**
     * 获取API访问token
     *
     * @return assess_token
     */
    private String getAccessToken() {
        // 获取token地址
        String getAccessTokenUrl = AUTH_HOST + "grant_type=client_credentials" + "&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
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
            log.error("获取token失败:", e);
        }
        return null;
    }
}
