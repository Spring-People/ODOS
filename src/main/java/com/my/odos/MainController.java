package com.my.odos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/test")
    public String test(Model model) {
        model.addAttribute("msg", "Hello world");
        return "index";
    }
}
