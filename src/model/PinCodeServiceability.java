package model;

public class PinCodeServiceability {
    private final String destinationPinCode;
    private final PaymentMode paymentMode;

    public PinCodeServiceability(String destPincode, PaymentMode paymentMode) {
        this.destinationPinCode = destPincode;
        this.paymentMode = paymentMode;
    }

    public String getDestinationPinCode() {
        return destinationPinCode;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }
}
