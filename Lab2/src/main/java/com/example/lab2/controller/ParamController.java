package com.example.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/form")
    public String form() {
        return "param/form";
    }

    @RequestMapping("/save/{x}")
    public String save(@PathVariable("x") String x, @RequestParam(name="y", required=false) String y, Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "param/form";
    }
}
