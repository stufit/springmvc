package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJson {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 서블렛을 사용한 리퀘스트를 바디로 받을때 형식
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }

    /**
     * @RequestBody를 사용한 리퀘스트 바디를 받을때 형식
     * @ResponseBody를 사용한 리스폰스 바디를 보낼때 형식
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @RequestBody를 사용한 리퀘스트 바디를 받을때 형식
     * 이 때, HelloData(인터페이스) 자체로 받을 수 있음.
     * @param messageBody
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData messageBody)  {
        log.info("messageBody = {}", messageBody);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity)  {
        HelloData data = httpEntity.getBody();
        log.info("username = {}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    /**
     * @RequestBody를 사용한 리퀘스트 바디를 받을때 형식
     * return 값으로 HelloData(인터페이스)를 사용할 수 있음.
     * @param messageBody
     * @return
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData messageBody)  {
        log.info("messageBody = {}", messageBody);
        return messageBody;
    }
}
