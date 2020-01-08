package zuo.li.play.ocr.dao;

import org.springframework.stereotype.Repository;
import zuo.li.play.ocr.core.bo.FileBO;
import zuo.li.play.ocr.core.entity.FoodLicenceDetailDO;
import zuo.li.play.ocr.core.entity.OperatingLicenceDetailDO;
import zuo.li.play.ocr.core.vo.FileVO;

import java.util.List;

/**
 * @Description: 文件dao
 * @Author: zuo.li
 * @Date: 2020/1/4 11:32
 */
@Repository
public interface FileDao {

    /**
     * 获取文件列表
     *
     * @param fileBO 文件BO
     * @return 文件列表
     */
    List<FileVO> getFileList(FileBO fileBO);

    /**
     * 获取文件数量
     *
     * @param fileBO 文件BO
     * @return 文件数量
     */
    Long countFile(FileBO fileBO);

    /**
     * 通过文件id获取营业执照详情
     *
     * @param fileId 文件id
     * @return 营业执照详情
     */
    OperatingLicenceDetailDO getOperatingLicenceDetailByFileId(Long fileId);

    /**
     * 新增营业执照详情
     *
     * @param operatingLicenceDetailDO 营业执照详情DO
     */
    void insertOperatingLicenceDetail(OperatingLicenceDetailDO operatingLicenceDetailDO);

    /**
     * 更新营业执照详情
     *
     * @param operatingLicenceDetailDO 营业执照详情DO
     */
    void updateOperatingLicenceDetail(OperatingLicenceDetailDO operatingLicenceDetailDO);

    /**
     * 通过文件id获取食营证详情
     *
     * @param fileId 文件id
     * @return 食营证详情
     */
    FoodLicenceDetailDO getFoodLicenceDetailByFileId(Long fileId);

    /**
     * 新增食营证详情
     *
     * @param foodLicenceDetailDO 食营证详情DO
     */
    void insertFoodLicenceDetail(FoodLicenceDetailDO foodLicenceDetailDO);

    /**
     * 更新食营证详情
     *
     * @param foodLicenceDetailDO 食营证详情DO
     */
    void updateFoodLicenceDetail(FoodLicenceDetailDO foodLicenceDetailDO);
}
