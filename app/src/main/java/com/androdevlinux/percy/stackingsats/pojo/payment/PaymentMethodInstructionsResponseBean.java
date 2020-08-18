package com.androdevlinux.percy.stackingsats.pojo.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentMethodInstructionsResponseBean {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payment_method_instruction")
    @Expose
    private PaymentMethodInstruction paymentMethodInstruction;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentMethodInstruction getPaymentMethodInstruction() {
        return paymentMethodInstruction;
    }

    public void setPaymentMethodInstruction(PaymentMethodInstruction paymentMethodInstruction) {
        this.paymentMethodInstruction = paymentMethodInstruction;
    }

}