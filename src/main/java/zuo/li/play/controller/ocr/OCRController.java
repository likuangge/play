package zuo.li.play.controller.ocr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zuo.li.play.common.CommonConstants;
import zuo.li.play.common.core.page.PageAO;
import zuo.li.play.common.core.page.PageBO;
import zuo.li.play.common.core.result.PaginationResult;
import zuo.li.play.common.core.result.ResultInfo;
import zuo.li.play.core.ao.FileAO;
import zuo.li.play.core.bo.FileBO;
import zuo.li.play.core.vo.FileVO;
import zuo.li.play.service.FileService;

/**
 * @Description: OCR接口
 * @Author: zuo.li
 * @Date: 2020/1/3 17:56
 */
@RequestMapping("/ocr")
@RestController
@Slf4j
public class OCRController {

    /**
     * 文件service
     */
    @Autowired
    private FileService fileService;

    /**
     * 获取文件列表
     *
     * @return 文件列表
     */
    @PostMapping(value = "/fileList")
    public ResultInfo fileList(@RequestBody FileAO fileAO) {
        FileBO fileBO = new FileBO();
        convertToPageDTO(fileAO, fileBO);
        try {
            PaginationResult<FileVO> result = fileService.fileList(fileBO);
            return ResultInfo.success(result);
        } catch (Exception e) {
            log.error("错误信息:", e);
            return ResultInfo.errorMessage("获取文件列表失败");
        }
    }

    /**
     * 分页对象转换
     * @param pageAO 公共分页AO
     * @param pageBO 公共分页BO
     */
    protected void convertToPageDTO(PageAO pageAO, PageBO pageBO) {
        Assert.notNull(pageAO, "pageAO不能为null");
        Assert.notNull(pageBO, "pageBO不能为null");

        pageBO.setPageNo(pageAO.getPage());
        // 每页条数为0，则设置默认
        if (pageAO.getRows() == CommonConstants.INT_ZERO.intValue()) {
            pageBO.setPageSize(CommonConstants.SIZE);
        } else {
            pageBO.setPageSize(pageAO.getRows());
        }
    }
}
