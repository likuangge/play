package zuo.li.play.ocr.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 文件详情DO
 * @Author: zuo.li
 * @Date: 2020/1/6 16:40
 */
@Getter
@Setter
@ToString
public class FileDetailDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 文件id
     */
    private Long fileId;

    /**
     * 统一社会信用代码
     */
    private String societyCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 经营范围
     */
    private String businessScope;

    /**
     * 成立日期
     */
    private String createDate;

    /**
     * 营业期限
     */
    private String operatingPeriod;

    /**
     * 营业场所
     */
    private String operatingPlace;
}
