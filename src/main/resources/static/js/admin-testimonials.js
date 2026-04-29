document.addEventListener('DOMContentLoaded', loadPendingTestimonials);

async function loadPendingTestimonials() {
    const container = document.getElementById('pendingTestimonials');

    try {
        const response = await fetch('/api/testimonials/pending');
        const testimonials = await response.json();

        if (testimonials.length === 0) {
            container.innerHTML = '<p class="empty-message">No pending testimonials.</p>';
            return;
        }

        container.innerHTML = testimonials.map(testimonial => `
            <div class="testimonial">
                <div class="stars">${'★'.repeat(testimonial.rating)}${'☆'.repeat(5 - testimonial.rating)}</div>
                <p>"${escapeHTML(testimonial.message)}"</p>
                <strong>
                    — ${escapeHTML(testimonial.name)}, ${escapeHTML(testimonial.title)}
                    <br>
                    <small>${escapeHTML(testimonial.company)}</small>
                </strong>

                <div style="margin-top:20px;">
                    <button onclick="approveTestimonial(${testimonial.id})">Approve</button>
                    <button onclick="rejectTestimonial(${testimonial.id})" style="background:#ef4444; margin-left:10px;">Reject</button>
                </div>
            </div>
        `).join('');

    } catch (error) {
        container.innerHTML = '<p class="empty-message">Could not load pending testimonials.</p>';
    }
}

async function approveTestimonial(id) {
    await fetch(`/api/testimonials/${id}/approve`, {
        method: 'PUT'
    });

    loadPendingTestimonials();
}

async function rejectTestimonial(id) {
    await fetch(`/api/testimonials/${id}/reject`, {
        method: 'DELETE'
    });

    loadPendingTestimonials();
}

function escapeHTML(value) {
    return String(value)
        .replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}