package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import enums.PaymentStatus;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Order order;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.order = order;
        this.paymentData = paymentData;
        setStatus();
    }

    public void setStatus() {
        if (this.method.equals("VOUCHER_CODE")) {
            if (!this.paymentData.containsKey("voucherCode") || this.paymentData.get("voucherCode").isEmpty()) {
                this.status = PaymentStatus.REJECTED.getValue();
                return;
            }
            this.status = verifyCode();
        } else if (this.method.equals("PAYMENT_BY_BANK_TRANSFER")) {
            if (!this.paymentData.containsKey("bankName") || this.paymentData.get("bankName").isEmpty() ||
                    !this.paymentData.containsKey("referenceCode") || this.paymentData.get("referenceCode").isEmpty()) {
                this.status = PaymentStatus.REJECTED.getValue();
                return;
            }
            this.status = verifyBankTransfer();
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }


    private String verifyCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return PaymentStatus.REJECTED.getValue();
        }
        if (voucherCode.length() != 16) {
            return PaymentStatus.REJECTED.getValue();
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED.getValue();
        }
        int numCount = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private String verifyBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }
}




