package zuo.li.play.ocr.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuo.li.play.common.constants.CommonConstants;
import zuo.li.play.common.constants.JsonConstants;
import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.common.enums.FileTypeEnum;
import zuo.li.play.common.enums.FoodLicenceFieldEnum;
import zuo.li.play.common.enums.OperatingLicenceFieldEnum;
import zuo.li.play.common.utils.Base64Utils;
import zuo.li.play.common.utils.FileUtils;
import zuo.li.play.common.utils.HttpUtils;
import zuo.li.play.common.utils.OCRUtils;
import zuo.li.play.ocr.core.ao.FileAO;
import zuo.li.play.ocr.core.bo.FileBO;
import zuo.li.play.ocr.core.entity.FoodLicenceDetailDO;
import zuo.li.play.ocr.core.entity.OperatingLicenceDetailDO;
import zuo.li.play.ocr.core.vo.FileVO;
import zuo.li.play.ocr.dao.FileDao;
import zuo.li.play.ocr.service.FileService;

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
     * 模板id
     */
    private static final String TEMPLATE_SIGN = "bd429374c58e802dd1b0dede60bbc313";

    @Override
    public PaginationResult<FileVO> fileList(FileBO fileBO) {
        List<FileVO> fileVOS = fileDao.getFileList(fileBO);
        Long count = fileDao.countFile(fileBO);
        return new PaginationResult<>(count, fileVOS);
    }

    @Override
    public void fileAnalysis(FileAO fileAO) {
        try {
            byte[] imgData = FileUtils.readFileFromRemote(fileAO.getFileUrl());
            String imgStr = Base64Utils.encode(imgData);
            // 请求模板参数
            String classifierParams = "classifierId=1&image=" + URLEncoder.encode(imgStr, "UTF-8");
            String accessToken = OCRUtils.getAccessToken();
            String result = HttpUtils.post(RECOGNIZE_URL, accessToken, classifierParams);
            Map<String, String> map = jsonTransfer(result);
            fileDetailPersistence(fileAO, map);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public OperatingLicenceDetailDO getOperatingLicenceDetailByFileId(Long fileId) {
        return fileDao.getOperatingLicenceDetailByFileId(fileId);
    }

    @Override
    public FoodLicenceDetailDO getFoodLicenceDetailByFileId(Long fileId) {
        return fileDao.getFoodLicenceDetailByFileId(fileId);
    }

    /**
     * json字段提取
     *
     * @param result json字符串
     * @return map字段映射
     */
    private Map<String, String> jsonTransfer(String result) {
        JSONObject jsonObject = new JSONObject(result);
        int errorCode = jsonObject.getInt(JsonConstants.ERROR_CODE);
        if(errorCode != CommonConstants.INT_ZERO) {
            throw new RuntimeException(jsonObject.getString(JsonConstants.ERROR_MSG));
        }
        JSONObject jsonObject1 = jsonObject.getJSONObject(JsonConstants.DATA);
        if((boolean) jsonObject1.get(JsonConstants.IS_STRUCTED)) {
            JSONArray jsonArray = jsonObject1.getJSONArray(JsonConstants.RET);
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                map.put(jsonObject2.getString(JsonConstants.WORD_NAME), jsonObject2.getString(JsonConstants.WORD));
            }
            return map;
        } else {
            throw new RuntimeException("结构化失败");
        }
    }

    /**
     * 组装营业执照详情DO
     *
     * @param map 数据map
     * @param fileId 文件id
     * @return 营业执照详情DO
     */
    private OperatingLicenceDetailDO setDataIntoOperatingLicenceDetailDO(Map<String, String> map, Long fileId) {
        OperatingLicenceDetailDO operatingLicenceDetailDO = new OperatingLicenceDetailDO();
        operatingLicenceDetailDO.setFileId(fileId);
        operatingLicenceDetailDO.setBusinessScope(map.get(OperatingLicenceFieldEnum.BUSINESS_SCOPE.getName()));
        operatingLicenceDetailDO.setCreateDate(map.get(OperatingLicenceFieldEnum.CREATE_DATE.getName()));
        operatingLicenceDetailDO.setName(map.get(OperatingLicenceFieldEnum.NAME.getName()));
        operatingLicenceDetailDO.setOperatingPeriod(map.get(OperatingLicenceFieldEnum.OPERATING_PERIOD.getName()));
        operatingLicenceDetailDO.setOperatingPlace(map.get(OperatingLicenceFieldEnum.OPERATING_PLACE.getName()));
        operatingLicenceDetailDO.setPrincipal(map.get(OperatingLicenceFieldEnum.PRINCIPAL.getName()));
        operatingLicenceDetailDO.setSocietyCode(map.get(OperatingLicenceFieldEnum.SOCIETY_CODE.getName()));
        operatingLicenceDetailDO.setType(map.get(OperatingLicenceFieldEnum.TYPE.getName()));
        return operatingLicenceDetailDO;
    }

    /**
     * 组装食营证详情DO
     *
     * @param map 数据map
     * @param fileId 文件id
     * @return 食营证详情DO
     */
    private FoodLicenceDetailDO setDataIntoFoodLicenceDetailDO(Map<String, String> map, Long fileId) {
        FoodLicenceDetailDO foodLicenceDetailDO = new FoodLicenceDetailDO();
        foodLicenceDetailDO.setFileId(fileId);
        foodLicenceDetailDO.setCertificationAuthority(map.get(FoodLicenceFieldEnum.CERTIFICATION_AUTHORITY.getName()));
        foodLicenceDetailDO.setComplaintPhone(map.get(FoodLicenceFieldEnum.COMPLAINT_PHONE.getName()));
        foodLicenceDetailDO.setDailyManagementOrg(map.get(FoodLicenceFieldEnum.DAILY_MANAGEMENT_ORG.getName()));
        foodLicenceDetailDO.setDailyManagementPerson(map.get(FoodLicenceFieldEnum.DAILY_MANAGEMENT_PERSON.getName()));
        foodLicenceDetailDO.setIssuer(map.get(FoodLicenceFieldEnum.ISSUER.getName()));
        foodLicenceDetailDO.setMainBusinessFormat(map.get(FoodLicenceFieldEnum.MAIN_BUSINESS_FORMAT.getName()));
        foodLicenceDetailDO.setName(map.get(FoodLicenceFieldEnum.NAME.getName()));
        foodLicenceDetailDO.setOperatingPlace(map.get(FoodLicenceFieldEnum.OPERATING_PLACE.getName()));
        foodLicenceDetailDO.setOperatingProject(map.get(FoodLicenceFieldEnum.OPERATING_PROJECT.getName()));
        foodLicenceDetailDO.setPermissionCode(map.get(FoodLicenceFieldEnum.PERMISSION_CODE.getName()));
        foodLicenceDetailDO.setPlace(map.get(FoodLicenceFieldEnum.PLACE.getName()));
        foodLicenceDetailDO.setPrincipal(map.get(FoodLicenceFieldEnum.PRINCIPAL.getName()));
        foodLicenceDetailDO.setSocietyCode(map.get(FoodLicenceFieldEnum.SOCIETY_CODE.getName()));
        return foodLicenceDetailDO;
    }

    /**
     * 文件详情落库
     *
     * @param fileAO 文件AO
     * @param map 详情字段
     */
    private void fileDetailPersistence(FileAO fileAO, Map<String, String> map) {
        try {
            if(Objects.equals(fileAO.getFileType(), FileTypeEnum.LICENCE_YYZZWC_WATERMARK.getIndex())) {
                OperatingLicenceDetailDO operatingLicenceDetailDO = setDataIntoOperatingLicenceDetailDO(map, fileAO.getFileId());
                if(Objects.isNull(fileDao.getOperatingLicenceDetailByFileId(fileAO.getFileId()))) {
                    fileDao.insertOperatingLicenceDetail(operatingLicenceDetailDO);
                } else {
                    fileDao.updateOperatingLicenceDetail(operatingLicenceDetailDO);
                }
            }
            if(Objects.equals(fileAO.getFileType(), FileTypeEnum.LICENCE_SYZBL_WATERMARK.getIndex())) {
                FoodLicenceDetailDO foodLicenceDetailDO = setDataIntoFoodLicenceDetailDO(map, fileAO.getFileId());
                if(Objects.isNull(fileDao.getFoodLicenceDetailByFileId(fileAO.getFileId()))) {
                    fileDao.insertFoodLicenceDetail(foodLicenceDetailDO);
                } else {
                    fileDao.updateFoodLicenceDetail(foodLicenceDetailDO);
                }
            }
        } catch (Exception e) {
            log.error("错误信息:", e);
            throw new RuntimeException("文件详情落库失败");
        }
    }
}
