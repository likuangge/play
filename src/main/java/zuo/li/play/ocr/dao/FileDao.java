package zuo.li.play.ocr.dao;

import org.springframework.stereotype.Repository;
import zuo.li.play.ocr.core.bo.FileBO;
import zuo.li.play.ocr.core.entity.FileDetailDO;
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
     * 通过文件id获取文件详情
     *
     * @param fileId 文件id
     * @return 文件详情
     */
    FileDetailDO getFileDetailByFileId(Long fileId);

    /**
     * 新增文件详情
     *
     * @param fileDetailDO 文件详情DO
     */
    void insertFileDetail(FileDetailDO fileDetailDO);

    /**
     * 更新文件详情
     *
     * @param fileDetailDO 文件详情DO
     */
    void updateFileDetail(FileDetailDO fileDetailDO);
}
