package zuo.li.play.common.enums;

/**
 * @Description: 文件类型枚举
 * @Author: zuo.li
 * @Date: 2020/1/3 17:39
 */
public enum FileTypeEnum {

    /**
     * 食营证办理加水印
     */
    LICENCE_SYZBL_WATERMARK("LICENCE_SYZBL_WATERMARK", "食营证办理加水印"),

    /**
     * 营业执照完成加水印
     */
    LICENCE_YYZZWC_WATERMARK("LICENCE_YYZZWC_WATERMARK", "营业执照完成加水印");

    /**
     * 索引
     */
    private String index;
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
    FileTypeEnum(String index, String name) {
        this.index = index;
        this.name = name;
    }


    /**
     * 根据索引获取名称
     *
     * @param index 索引
     * @return name
     */
    public static String getNameByIndex(String index) {
        for (FileTypeEnum c : FileTypeEnum.values()) {
            if (c.getIndex().equals(index)) {
                return c.name;
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
    public static FileTypeEnum getByIndex(String index) {
        for (FileTypeEnum c : FileTypeEnum.values()) {
            if (c.getIndex().equals(index)) {
                return c;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }
}
