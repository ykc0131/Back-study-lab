package com.yooki.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){ //Model : Controller에서 데이터를 실어서 View로 넘길 수 있다.
        model.addAttribute("data","hello!!");
        return "hello";
        //thymeleaf viewName 매핑 : templates/ + {ViewName} + .html 적용시킴
    }
}
