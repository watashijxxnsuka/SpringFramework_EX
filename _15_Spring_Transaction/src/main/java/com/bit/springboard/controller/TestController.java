package com.bit.springboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        // 화면에 전달할 데이터를 Model 객체에 담을 수 있다.
        // HandlerAdapter 객체는 Model 객체에 담긴 데이터와 ViewResolver 가 리턴한 화면을
        // ModelAndView 객체로 만들어서 DispatcherServlet 에게 전달하게 되고,
        // DispatcherServlet 은 Model 에 담긴 데이터를 화면에 바인딩하여 데이터가 바인딩된 화면을 클라이언트에게 리턴한다.
        model.addAttribute("name", "SpringFramework");
        model.addAttribute("language", "Java");

        // ViewResolver 객체에 의해서 /WEB-INF/views/hello.jsp 파일을 찾아서 리턴한다.
        return "hello";
    }
}
