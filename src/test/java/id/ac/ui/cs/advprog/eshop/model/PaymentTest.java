package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private Order order;
    private List<Order> orders;

    @BeforeEach
    public void setup() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setProductId("13652556-012a-4c07-b546-54eb1396d79b");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(7);

        product2.setProductId("6cf7da61-62a0-4599-b67b-45a6ed766d2d");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(8);

        this.products.add(product1);
        this.products.add(product2);
    }

    // Main Payment
    @Test
    void testDuplicatePayment() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);

        assertSame(this.orders.getFirst(), payment.getOrder());
    }

    @Test
    void testPaymentInvalidPaymentMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "QRIS", this.orders.getFirst(), paymentData);
        });
    }

    @Test
    void testRejectedPaymentEmptyData() {
        Map<String, String> paymentData = new HashMap<>();

        Payment voucherPayment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);
        assertEquals("REJECTED", voucherPayment.getStatus());

        Payment banktransferPayment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), null, paymentData);
        assertEquals("REJECTED", banktransferPayment.getStatus());
    }

    // Voucher Code
    @Test
    void testSuccessValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeWrongPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SSOOP1234ABC5678");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot8CharNumerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567A");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSuccessValidPaymentByBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "001");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedPaymentByBankTransferEmptyBankName() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("referenceCode", "001");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedPaymentByBankTransferEmptyReferenceCode() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "BCA");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
}
