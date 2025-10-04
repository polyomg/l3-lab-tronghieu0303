package com.example.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ctrl")
public class OkController {

    // Default mapping -- will show ok.html
    @RequestMapping("/ok")
    public String ok() {
        return "ok";
    }

    // OK1: form action="/ctrl/ok" method="post" -> m1()
    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("method", "m1");
        return "ok";
    }

    // OK2: button formmethod="get" -> GET /ctrl/ok -> m2()
    @GetMapping(value = "/ok", params = "!x")
    public String m2(Model model) {
        model.addAttribute("method", "m2");
        return "ok";
    }

    // OK3: formaction="/ctrl/ok?x" -> GET to /ctrl/ok with parameter x present -> m3()
    @GetMapping(value = "/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("method", "m3");
        return "ok";
    }
}
