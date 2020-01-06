package zuo.li.play.wow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: WOW日常controller
 * @Author: zuo.li
 * @Date: 2019/12/14 14:31
 */
@RestController
public class WowController {

    /**
     * 测试
     *
     * @return 测试结果
     */
    @RequestMapping(method = RequestMethod.GET, value = "/index")
    public String index() {
        return "前后端联通";
    }

}
