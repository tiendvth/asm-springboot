package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CategoryStatus {
    ACTIVE(1),
    DEACTIVE(0),
    DELETED(-1),
    UNDEFINED(2);
    private int value;

    public static CategoryStatus of(int value) {
        for (CategoryStatus categoryStatus : CategoryStatus.values()) {
            if(categoryStatus.getValue() == value) {
                return categoryStatus;
            }
        }
        return CategoryStatus.UNDEFINED;
    }
}
