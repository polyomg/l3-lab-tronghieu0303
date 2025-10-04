package com.example.lab2.controller;

import com.example.lab2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    // Bài 4: when showing form, create default product and put to model (?1)
    @GetMapping("/form")
    public String form(Model model) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);
        model.addAttribute("product", p); // ?1
        model.addAttribute("items", getItems()); // share list too
        return "product/form";
    }

    // Bài 3 & 4: save product using @ModelAttribute (?2)
    @PostMapping("/save")
    public String save(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("items", getItems());
        return "product/form";
    }

    // ?3 - provide items via @ModelAttribute so template can use ${items}
    @ModelAttribute("items")
    public List<Product> getItems() {
        // create 20 sample products (Bài 6)
        return IntStream.rangeClosed(1,20)
                .mapToObj(i -> new Product("Product " + i, 100.0 * i))
                .collect(Collectors.toList());
    }

    // Pagination endpoint for Bài 6
    @GetMapping("/list")
    public String list(@RequestParam(name="page", defaultValue="1") int page,
                       @RequestParam(name="size", defaultValue="5") int size,
                       Model model) {
        List<Product> all = getItems();
        int from = Math.max(0, (page-1)*size);
        int to = Math.min(all.size(), from + size);
        List<Product> pageItems = all.subList(from, to);
        model.addAttribute("pageItems", pageItems);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", (all.size() + size -1)/size);
        return "product/list";
    }
}
