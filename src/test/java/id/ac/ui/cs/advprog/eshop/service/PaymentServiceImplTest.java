package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentService;

    private Order order;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", null, 1708560000L, "Safira Sudrajat");

        payments = List.of(
                new Payment(UUID.randomUUID().toString(), "VOUCHER_CODE", order, Map.of("voucherCode", "ESHOP1234ABC567A")),
                new Payment(UUID.randomUUID().toString(), "PAYMENT_BY_BANK_TRANSFER", order, Map.of("bankName", "BCA", "referenceCode", "001"))
        );
    }

    @Test
    void testAddPayment() {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = payments.get(0);

        when(paymentRepository.findById(paymentId)).thenReturn(null);
        when(paymentRepository.save(any())).thenReturn(payment);

        Payment result = paymentService.addPayment(paymentId, order, "VOUCHER_CODE", Map.of("voucherCode", "ESHOP1234ABC567A"));

        verify(paymentRepository, times(1)).save(any());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfNull() {
        Payment payment = payments.get(0);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        assertNull(paymentService.addPayment(payment.getId(), order, "VOUCHER_CODE", Map.of("voucherCode", "ESHOP1234ABC567A")));
        verify(paymentRepository, times(0)).save(any());
    }

    @Test
    void testSetValidStatus() {
        Payment payment = payments.get(1);
        Payment result1 = paymentService.setStatus(payment, "SUCCESS");

        assertEquals(payment.getId(), result1.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result1.getOrder().getStatus());

        Payment result2 = paymentService.setStatus(payment, "REJECTED");

        assertEquals(payment.getId(), result2.getId());
        assertEquals(OrderStatus.FAILED.getValue(), result2.getOrder().getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "MEOW"));
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        when(paymentRepository.findById("zczc")).thenReturn(null);
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.getAllPayment()).thenReturn(payments);

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);
    }
}
