package com.art.tacocloud.controllers;

import com.art.tacocloud.taco.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/orders")
@Controller
public class OrdersController {

    @GetMapping("/current")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
    return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {

        if(errors.hasErrors())
            return "orderForm";

        log.info("Processing order:" + order);
        return "redirect:/";
    }

}
