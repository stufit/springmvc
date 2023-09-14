package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 롬복 어노테이션 => 로그 사용가능
@RestController
@RequestMapping("/log")
public class LogTestController {


    @RequestMapping("/test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);
        return "ok";
    }
}
