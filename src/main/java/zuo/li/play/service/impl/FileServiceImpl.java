package zuo.li.play.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.core.bo.FileBO;
import zuo.li.play.core.vo.FileVO;
import zuo.li.play.dao.FileDao;
import zuo.li.play.service.FileService;

import java.util.List;

/**
 * @Description: 文件service实现
 * @Author: zuo.li
 * @Date: 2020/1/4 11:29
 */
@Service
public class FileServiceImpl implements FileService {

    /**
     * 文件dao
     */
    @Autowired
    private FileDao fileDao;

    @Override
    public PaginationResult<FileVO> fileList(FileBO fileBO) {
        List<FileVO> fileVOS = fileDao.getFileList(fileBO);
        Long count  = fileDao.countFile(fileBO);
        return new PaginationResult<>(count, fileVOS);
    }
}
