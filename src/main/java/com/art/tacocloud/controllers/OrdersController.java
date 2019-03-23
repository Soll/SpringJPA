package com.art.tacocloud.controllers;

import com.art.tacocloud.data.OrderRepository;
import com.art.tacocloud.taco.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/orders")
@SessionAttributes("order")
@Controller
public class OrdersController {

    private OrderRepository orderRepo;

    public OrdersController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
    return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

        if(errors.hasErrors())
            return "orderForm";

        orderRepo.save(order);
        sessionStatus.setComplete();

        log.info("Processing order:" + order);
        return "redirect:/";
    }

}
