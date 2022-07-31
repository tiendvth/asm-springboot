package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PaymentStatus {
    PAID(1),
    UNPAID(0),
    DEBT(2),
    UNDEFINED(-1);
    private int value;

    public static PaymentStatus of(int value) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if(paymentStatus.getValue() == value) {
                return paymentStatus;
            }
        }
        return PaymentStatus.UNDEFINED;
    }
}
