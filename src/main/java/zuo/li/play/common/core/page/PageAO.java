package zuo.li.play.common.core.page;

/**
 * @Description: 公共分页AO
 * @Author: zuo.li
 * @Date: 2020/1/4 13:43
 */
public class PageAO {

    /**
     * 第几页（从1开始）
     */
    private int page;
    /**
     * 每页条数
     */
    private int rows;

    /**
     * 获取 第几页（从1开始）
     *
     * @return page 第几页（从1开始）
     */
    public int getPage() {
        return this.page;
    }

    /**
     * 设置 第几页（从1开始）
     *
     * @param page 第几页（从1开始）
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 获取 每页条数
     *
     * @return rows 每页条数
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * 设置 每页条数
     *
     * @param rows 每页条数
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
}
