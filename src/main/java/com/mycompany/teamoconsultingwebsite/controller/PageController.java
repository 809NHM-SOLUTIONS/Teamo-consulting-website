package com.mycompany.teamoconsultingwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin-login")
    public String adminLogin() {
        return "admin-login";
    }

    @GetMapping("/admin-testimonials")
    public String adminTestimonials() {
        return "admin-testimonials";
    }
}