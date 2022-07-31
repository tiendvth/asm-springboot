package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatus {
    PENDING(1),
    PROCESSING(2),
    SHIPPING(3),
    COMPLETED(4),
    CANCELLED(0),
    DELETED(-1),
    UNDEFINED(-2);
    private int value;

    public static OrderStatus of(int value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.getValue() == value) {
                return orderStatus;
            }
        }
        return OrderStatus.UNDEFINED;
    }
}
