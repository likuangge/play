package zuo.li.play.dao;

import org.springframework.stereotype.Repository;
import zuo.li.play.core.bo.FileBO;
import zuo.li.play.core.vo.FileVO;

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
}
