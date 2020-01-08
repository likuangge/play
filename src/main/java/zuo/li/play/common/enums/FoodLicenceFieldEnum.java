package zuo.li.play.common.enums;

/**
 * @Description: 食营证详情字段
 * @Author: zuo.li
 * @Date: 2020/1/7 17:38
 */
public enum FoodLicenceFieldEnum {

    /**
     * 统一社会信用代码
     */
    SOCIETY_CODE(1, "统一社会信用代码"),

    /**
     * 经营者名称
     */
    NAME(2, "经营者名称"),

    /**
     * 住所
     */
    PLACE(3, "住所"),

    /**
     * 法定代表人
     */
    PRINCIPAL(4, "法定代表人"),

    /**
     * 经营项目
     */
    OPERATING_PROJECT(5, "经营项目"),

    /**
     * 经营场所
     */
    OPERATING_PLACE(6, "经营场所"),

    /**
     * 主体业态
     */
    MAIN_BUSINESS_FORMAT(7, "主体业态"),

    /**
     * 许可证编号
     */
    PERMISSION_CODE(8, "许可证编号"),

    /**
     * 日常监督管理机构
     */
    DAILY_MANAGEMENT_ORG(9, "日常监督管理机构"),

    /**
     * 日常监督管理人员
     */
    DAILY_MANAGEMENT_PERSON(10, "日常监督管理人员"),

    /**
     * 投诉举报电话
     */
    COMPLAINT_PHONE(11, "投诉举报电话"),

    /**
     * 发证机关
     */
    CERTIFICATION_AUTHORITY(12, "发证机关"),

    /**
     * 签发人
     */
    ISSUER(13, "签发人");

    /**
     * 索引
     */
    private int index;
    /**
     * name
     */
    private String name;

    /**
     * 构造
     *
     * @param index 索引
     * @param name  值
     */
    FoodLicenceFieldEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }


    /**
     * 根据索引获取名称
     *
     * @param index 索引
     * @return name
     */
    public static String getNameByIndex(int index) {
        for (FoodLicenceFieldEnum c : FoodLicenceFieldEnum.values()) {
            if (c.getIndex() == index) {
                return c.getName();
            }
        }
        return null;
    }
    /**
     * 根据索引获取名称
     *
     * @param index 索引
     * @return name
     */
    public static FoodLicenceFieldEnum getByIndex(int index) {
        for (FoodLicenceFieldEnum c : FoodLicenceFieldEnum.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
