package com.mycompany.teamoconsultingwebsite.service;

import com.mycompany.teamoconsultingwebsite.entity.Testimonial;
import com.mycompany.teamoconsultingwebsite.repository.TestimonialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    public Testimonial submitTestimonial(Testimonial testimonial) {
        validateTestimonial(testimonial);

        testimonial.setStatus("PENDING");
        return testimonialRepository.save(testimonial);
    }

    public List<Testimonial> getApprovedTestimonials() {
        return testimonialRepository.findByStatusOrderByCreatedAtDesc("APPROVED");
    }

    public List<Testimonial> getPendingTestimonials() {
        return testimonialRepository.findByStatusOrderByCreatedAtDesc("PENDING");
    }

    public Testimonial approveTestimonial(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        testimonial.setStatus("APPROVED");
        return testimonialRepository.save(testimonial);
    }

    public void rejectTestimonial(Long id) {
        testimonialRepository.deleteById(id);
    }

    private void validateTestimonial(Testimonial testimonial) {
        if (testimonial == null) {
            throw new RuntimeException("Invalid testimonial submission.");
        }

        if (isBlank(testimonial.getName())) {
            throw new RuntimeException("Name is required.");
        }

        if (isBlank(testimonial.getTitle())) {
            throw new RuntimeException("Job title or role is required.");
        }

        if (isBlank(testimonial.getCompany())) {
            throw new RuntimeException("Company or programme is required.");
        }

        if (isBlank(testimonial.getMessage())) {
            throw new RuntimeException("Testimonial message is required.");
        }

        if (testimonial.getMessage().length() > 2000) {
            throw new RuntimeException("Testimonial message must not exceed 2000 characters.");
        }

        if (testimonial.getName().length() > 100) {
            throw new RuntimeException("Name must not exceed 100 characters.");
        }

        if (testimonial.getTitle().length() > 100) {
            throw new RuntimeException("Job title must not exceed 100 characters.");
        }

        if (testimonial.getCompany().length() > 150) {
            throw new RuntimeException("Company name must not exceed 150 characters.");
        }

        if (testimonial.getRating() < 1 || testimonial.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5.");
        }

        if (containsSpamWords(testimonial.getMessage())) {
            throw new RuntimeException("Testimonial contains inappropriate content.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean containsSpamWords(String message) {
        String lowerMessage = message.toLowerCase();

        String[] blockedWords = {
                "http://",
                "https://",
                "www.",
                "casino",
                "betting",
                "crypto",
                "loan",
                "viagra"
        };

        for (String word : blockedWords) {
            if (lowerMessage.contains(word)) {
                return true;
            }
        }

        return false;
    }
}