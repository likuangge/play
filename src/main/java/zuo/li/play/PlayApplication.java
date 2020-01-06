package zuo.li.play;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zuo.li
 */
@MapperScan("zuo.li.play.ocr.dao")
@SpringBootApplication
public class PlayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayApplication.class, args);
    }

}
