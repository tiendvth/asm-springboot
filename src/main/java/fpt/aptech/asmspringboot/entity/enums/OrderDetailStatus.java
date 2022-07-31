package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderDetailStatus {
    ACTIVE(1),
    DEACTIVE(0),
    CANCELLED(-1),
    UNDEFINED(2);
    private int value;

    public static OrderDetailStatus of(int value) {
        for (OrderDetailStatus orderDetailStatus : OrderDetailStatus.values()) {
            if(orderDetailStatus.getValue() == value) {
                return orderDetailStatus;
            }
        }
        return OrderDetailStatus.UNDEFINED;
    }
}