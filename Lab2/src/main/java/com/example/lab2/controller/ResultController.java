package com.example.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResultController {

    @RequestMapping("/a")
    public String m1() {
        return "a";
    }

    @RequestMapping("/b")
    public String m2(Model model) {
        // add message to model and forward to /a so model attribute is shown
        model.addAttribute("message", "I come from b");
        return "forward:/a"; // ?1: forward preserves model attributes
    }

    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        // add message as redirect attribute (will appear in param.message)
        params.addAttribute("message", "I come from c");
        return "redirect:/a"; // ?2: redirect with attribute
    }

    // ?3: to have /d return body text directly we use @ResponseBody
    @RequestMapping("/d")
    @ResponseBody
    public String m4() {
        return "I come from d";
    }
}
