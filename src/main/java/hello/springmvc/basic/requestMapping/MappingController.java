package hello.springmvc.basic.requestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mapping")
public class MappingController {

    /**
     * 기본 요청
     * @return
     */
    @GetMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public String mappingPath(@PathVariable String userId){
        log.info("mappingPath UserId= {}",userId);
        return userId;
    }

    /**
     * PathVariable 사용 다중
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath UserId= {}, orderId= {}",userId,orderId);
        return userId + orderId;
    }

    /**
     * 파라미터로 추가 매핑
     *
     */
    @GetMapping(value = "/mapping-params", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers = "mode=debug"
     * 헤더에 mode=debug가 있어야 호출됨
     * headers = "!mode" => 헤더에 mode가 없어야 호출됨
     * headers = "mode!=debug" => 헤더에 mode가 있고, 그 값이 debug가 아니어야 호출됨
     */
    @GetMapping(value="/mapping-header",headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /** * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
}
