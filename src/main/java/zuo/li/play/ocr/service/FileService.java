package zuo.li.play.ocr.service;

import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.ocr.core.ao.FileAO;
import zuo.li.play.ocr.core.bo.FileBO;
import zuo.li.play.ocr.core.entity.FileDetailDO;
import zuo.li.play.ocr.core.vo.FileVO;

/**
 * @Description: 文件service
 * @Author: zuo.li
 * @Date: 2020/1/4 11:27
 */
public interface FileService {

    /**
     * 获取文件列表
     *
     * @param fileBO 文件BO
     * @return 文件列表
     */
    PaginationResult<FileVO> fileList(FileBO fileBO);

    /**
     * 文件解析
     *
     * @param fileAO 文件AO
     */
    void fileAnalysis(FileAO fileAO);

    /**
     * 获取文件详情
     *
     * @param fileId 文件id
     * @return 文件详情
     */
    FileDetailDO getFileDetailByFileId(Long fileId);
}
