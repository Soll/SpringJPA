package com.art.tacocloud.controllers;

import com.art.tacocloud.data.OrderRepository;
import com.art.tacocloud.security.User;
import com.art.tacocloud.taco.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/orders")
@SessionAttributes("order")
@Controller
public class OrdersController {

    @Autowired
    private OrderRepository orderRepo;

    public OrdersController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String showOrderForm(Model model) {
        model.addAttribute("order");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {

        if (errors.hasErrors())
            return "orderForm";

        order.setUser(user);

        orderRepo.save(order);
        sessionStatus.setComplete();

        log.info("Processing order:" + order);
        return "redirect:/";
    }

}
