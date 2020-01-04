package zuo.li.play.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @Author: zuo.li
 * @Date: 2020/1/4 14:41
 */
public class DateTimeUtils {

    /**
     * 格式化日期时间
     * @param date 日期时间
     * @return 格式化
     */
    public static String formatDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
