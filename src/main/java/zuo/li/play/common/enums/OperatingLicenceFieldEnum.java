package zuo.li.play.common.enums;

/**
 * @Description: 文件字段枚举
 * @Author: zuo.li
 * @Date: 2020/1/6 16:57
 */
public enum OperatingLicenceFieldEnum {

    /**
     * 统一社会信用代码
     */
    SOCIETY_CODE(1, "统一社会信用代码"),

    /**
     * 名称
     */
    NAME(2, "名称"),

    /**
     * 类型
     */
    TYPE(3, "类型"),

    /**
     * 负责人
     */
    PRINCIPAL(4, "负责人"),

    /**
     * 经营范围
     */
    BUSINESS_SCOPE(5, "经营范围"),

    /**
     * 成立日期
     */
    CREATE_DATE(6, "成立日期"),

    /**
     * 营业期限
     */
    OPERATING_PERIOD(7, "营业期限"),

    /**
     * 营业场所
     */
    OPERATING_PLACE(8, "营业场所");

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
    OperatingLicenceFieldEnum(int index, String name) {
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
        for (OperatingLicenceFieldEnum c : OperatingLicenceFieldEnum.values()) {
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
    public static OperatingLicenceFieldEnum getByIndex(int index) {
        for (OperatingLicenceFieldEnum c : OperatingLicenceFieldEnum.values()) {
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
