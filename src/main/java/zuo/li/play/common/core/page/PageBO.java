package zuo.li.play.common.core.page;

/**
 * @Description: 分页公用BO
 * @Author: zuo.li
 * @Date: 2020/1/4 14:12
 */
public class PageBO {

    /**
     * 页码（从1开始）
     */
    private Integer pageNo;

    /**
     * 每页多少条
     */
    private Integer pageSize;

    /**
     * 偏移量
     */
    private Integer offset;

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        if(pageNo != null && pageSize != null){
            this.offset = (this.pageNo > 0 ? this.pageNo - 1 : 0) * this.pageSize;
            return offset;
        }else{
            return 0;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
