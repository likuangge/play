package zuo.li.play.service;

import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.core.bo.FileBO;
import zuo.li.play.core.vo.FileVO;

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
}
