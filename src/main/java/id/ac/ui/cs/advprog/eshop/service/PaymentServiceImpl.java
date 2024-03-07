package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import enums.PaymentStatus;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(String paymentId, Order order, String method, Map<String, String> paymentData) {
        Payment existingPayment = paymentRepository.findById(paymentId);
        if (existingPayment != null) {
            return null;
        }
        Payment payment = new Payment(paymentId, method, order, paymentData);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (!Arrays.asList("SUCCESS", "REJECTED").contains(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        // Assuming the order's status is being set directly in the Payment object
        Order order = payment.getOrder();
        if (status.equals("SUCCESS")) {
            order.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            order.setStatus(PaymentStatus.REJECTED.getValue());
        }
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }
}
