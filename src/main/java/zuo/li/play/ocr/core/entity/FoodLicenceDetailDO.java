package zuo.li.play.ocr.core.entity;

import lombok.Data;

/**
 * @Description: 食营证详情DO
 * @Author: zuo.li
 * @Date: 2020/1/7 15:39
 */
@Data
public class FoodLicenceDetailDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 统一社会信用代码
     */
    private String societyCode;

    /**
     * 经营者名称
     */
    private String name;

    /**
     * 住所
     */
    private String place;

    /**
     * 法定代表人(负责人)
     */
    private String principal;

    /**
     * 经营项目
     */
    private String operatingProject;

    /**
     * 经营场所
     */
    private String operatingPlace;

    /**
     * 主体业态
     */
    private String mainBusinessFormat;

    /**
     * 许可证编号
     */
    private String permissionCode;

    /**
     * 日常监督管理机构
     */
    private String dailyManagementOrg;

    /**
     * 日常监督管理人员
     */
    private String dailyManagementPerson;

    /**
     * 投诉举报电话
     */
    private String complaintPhone;

    /**
     * 发证机关
     */
    private String certificationAuthority;

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 文件id
     */
    private Long fileId;
}
