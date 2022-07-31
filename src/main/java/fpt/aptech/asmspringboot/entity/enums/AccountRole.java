package fpt.aptech.asmspringboot.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AccountRole {
    ADMIN(1),
    USER(0),
    UNDEFINED(2);
    private int value;

    public static AccountRole of(int value) {
        for (AccountRole userRole : AccountRole.values()) {
            if(userRole.getValue() == value) {
                return userRole;
            }
        }
        return AccountRole.UNDEFINED;
    }
}