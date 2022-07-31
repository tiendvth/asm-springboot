package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AccountStatus {
    ACTIVE(1),
    DEACTIVE(0),
    DELETED(-1),
    UNDEFINED(-2);
    private int value;

    public static AccountStatus of(int value) {
        for (AccountStatus userStatus : AccountStatus.values()) {
            if(userStatus.getValue() == value) {
                return userStatus;
            }
        }
        return AccountStatus.UNDEFINED;
    }
}
