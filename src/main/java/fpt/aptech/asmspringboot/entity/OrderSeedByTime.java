package fpt.aptech.asmspringboot.entity;

import fpt.aptech.asmspringboot.entity.enums.OrderSeedByType;
import fpt.aptech.asmspringboot.entity.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderSeedByTime {
    private OrderSeedByType seedByType;
    private int year;
    private int month;
    private int day;
    private int countOrder;
    private OrderStatus orderStatus;
}
