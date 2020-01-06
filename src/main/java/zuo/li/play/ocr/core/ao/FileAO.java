package zuo.li.play.ocr.core.ao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zuo.li.play.common.core.page.PageAO;

/**
 * @Description: 文件AO
 * @Author: zuo.li
 * @Date: 2020/1/4 13:49
 */
@Getter
@Setter
@ToString
public class FileAO extends PageAO {

    /**
     * 文件id
     */
    private Long fileId;

    /**
     * 文件路径
     */
    private String fileUrl;
}
