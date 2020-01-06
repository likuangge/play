package zuo.li.play.ocr.core.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zuo.li.play.common.enums.FileTypeEnum;
import zuo.li.play.common.utils.DateTimeUtils;

import java.util.Date;

/**
 * @Description: 文件VO
 * @Author: zuo.li
 * @Date: 2020/1/4 11:15
 */
@Getter
@Setter
@ToString
public class FileVO {

    /**
     * id
     */
    private Long id;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 获取文件类型字符串
     *
     * @return 文件类型
     */
    public String getFileTypeDesc() {
        return null == getFileType() ? "--" : FileTypeEnum.getNameByIndex(getFileType());
    }

    /**
     * 获取上传时间字符串
     *
     * @return 上传时间
     */
    public String getCreateTimeDesc() {
        return null == getCreateTime() ? "--" : DateTimeUtils.formatDateTime(getCreateTime());
    }
}
