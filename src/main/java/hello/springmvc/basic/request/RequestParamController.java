package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberNm,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberNm, memberAge);
        return "ok";
    }

    /**
     * @RequestParam에서 () 생략 가능 =>파라미터명과 변수명이 같으면 생략 가능
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * @RequestParam 생략 가능 => String, int, Integer 같은 단순 타입이면 @RequestParam도 생략 가능
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,
                                  int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * @RequestParam.required => 필수 파라미터
     * Integer 는 null 들어가도 되서 Integer로 바꿔준다.
     * @param username
     * @param age
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired( @RequestParam(required = true) String username,
                                        @RequestParam(required = false) Integer age
                                        ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault( @RequestParam(defaultValue = "guest") String username,
                                        @RequestParam(defaultValue = "-1") int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * @RequestParam Map => 파라미터를 Map으로 조회
     * @param paramMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap
                                  ) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
