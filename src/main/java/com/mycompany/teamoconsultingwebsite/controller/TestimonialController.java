package com.mycompany.teamoconsultingwebsite.controller;

import com.mycompany.teamoconsultingwebsite.entity.Testimonial;
import com.mycompany.teamoconsultingwebsite.service.TestimonialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @PostMapping
    public Testimonial submitTestimonial(@RequestBody Testimonial testimonial) {
        return testimonialService.submitTestimonial(testimonial);
    }

    @GetMapping("/approved")
    public List<Testimonial> getApprovedTestimonials() {
        return testimonialService.getApprovedTestimonials();
    }

    @GetMapping("/pending")
    public List<Testimonial> getPendingTestimonials() {
        return testimonialService.getPendingTestimonials();
    }

    @PutMapping("/{id}/approve")
    public Testimonial approveTestimonial(@PathVariable Long id) {
        return testimonialService.approveTestimonial(id);
    }

    @DeleteMapping("/{id}/reject")
    public void rejectTestimonial(@PathVariable Long id) {
        testimonialService.rejectTestimonial(id);
    }
}