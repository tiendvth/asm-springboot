package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PaymentMethod {
    CASH(1),
    DEBIT_CASH(2),
    CREDIT_CASH(3),
    MOBILE_PAYMENT(4),
    ELECTRONIC_BANK_TRANSFER(5),
    UNDEFINED(-0);
    private int value;

    public static PaymentMethod of(int value) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if(paymentMethod.getValue() == value) {
                return paymentMethod;
            }
        }
        return PaymentMethod.UNDEFINED;
    }
}