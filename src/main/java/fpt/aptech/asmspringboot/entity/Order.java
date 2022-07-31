package fpt.aptech.asmspringboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import fpt.aptech.asmspringboot.entity.base.BaseEntity;
import fpt.aptech.asmspringboot.entity.enums.OrderStatus;
import fpt.aptech.asmspringboot.entity.enums.PaymentMethod;
import fpt.aptech.asmspringboot.entity.enums.PaymentStatus;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@Inheritance
public class Order extends BaseEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @NotNull(m = "User Id cannot null")
    private long userId;
    @NotEmpty(message = "Customer name cannot be empty")
    private String customerName;
    @NotEmpty(message = "Customer email cannot be empty")
    private String customerEmail;
    @NotEmpty(message = "Customer name cannot be empty")
    private String customerPhone;
    @NotEmpty(message = "Customer address cannot be empty")
    private String customerAddress;
    @Column(columnDefinition = "text")
    private String note;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private double totalMoney;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Account account;
}
