package fpt.aptech.asmspringboot.seeder;

import com.github.javafaker.Faker;
import fpt.aptech.asmspringboot.entity.*;
import fpt.aptech.asmspringboot.entity.enums.OrderSeedByType;
import fpt.aptech.asmspringboot.entity.enums.OrderStatus;
import fpt.aptech.asmspringboot.entity.enums.PaymentMethod;
import fpt.aptech.asmspringboot.entity.enums.PaymentStatus;
import fpt.aptech.asmspringboot.repository.OrderRepository;
import fpt.aptech.asmspringboot.util.DateTimeHelper;
import fpt.aptech.asmspringboot.util.NumberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderSeeder {
    private static final int NUMBER_OF_ORDERS = 10000;
    private static List<Order> orders;
    private List<OrderSeedByTime> seeder;
    final OrderRepository orderRepository;

    public void generate() {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ORDERS; i++
        ) {
            Order order = Order.builder()
                    .id(System.currentTimeMillis() + i + 2)
                    .build();
            Set<OrderDetail> orderDetails = new HashSet<>();
            HashSet<Long> productIds = new HashSet<>();
            for (int j = 0; j < NumberHelper.generateRandom(1, 5); j++
            ) {
                int productRandomIndex = NumberHelper.generateRandom(0, ProductSeeder.productList.size() - 1);
                Product product = ProductSeeder.productList.get(productRandomIndex);
                if(productIds.contains(product.getId())) {
                    continue;
                }
                OrderDetail orderDetail = OrderDetail.builder()
                        .id(OrderDetailId.builder()
                                .orderId(order.getId())
                                .productId(product.getId())
                                .build())
                        .quantity(NumberHelper.generateRandom(1, 10))
                        .unitPrice(NumberHelper.generateRandom(1000, 50000))
                        .build();
                orderDetails.add(orderDetail);
                productIds.add(product.getId());
            }
            order.setOrderDetails(orderDetails);
            orderList.add(order);
        }
        orderRepository.saveAll(orderList);
    }

    public void configData() {
        seeder = new ArrayList<>();
        orders = new ArrayList<>();
        seeder.add(OrderSeedByTime.builder()
                .orderStatus(OrderStatus.COMPLETED)
                .seedByType(OrderSeedByType.YEAR)
                .year(2021)
                .countOrder(100)
                .build());
    }

    public void superGenerate() {
        configData();
        Faker faker = new Faker();
        for (OrderSeedByTime seedByTime : seeder) {
            int numberOfOrders = seedByTime.getCountOrder();
            for(int i = 0; i < numberOfOrders; i++) {
                LocalDateTime orderCreated = calculateOrderCreatedTime(seedByTime);
                Order order = Order.builder()
                        .id(System.currentTimeMillis() + i + 2)
                        .customerName(faker.superhero().name())
                        .customerAddress(faker.address().fullAddress())
                        .customerEmail("dangjinneryq@gmail.com")
                        .customerPhone(faker.phoneNumber().cellPhone())
                        .status(OrderStatus.of(NumberHelper.generateRandom(0, 4)))
                        .paymentStatus(PaymentStatus.of(NumberHelper.generateRandom(0, 2)))
                        .paymentMethod(PaymentMethod.of(NumberHelper.generateRandom(0, 5)))
                        .build();
                order.setCreatedAt(orderCreated);
                Set<OrderDetail> orderDetails = new HashSet<>();
                HashSet<Long> productIds = new HashSet<>();
                double totalMoney = 0;
                for (int j = 0; j < NumberHelper.generateRandom(1, 5); j++) {
                    int productRandomIndex = NumberHelper.generateRandom(0, ProductSeeder.productList.size() - 1);
                    Product product = ProductSeeder.productList.get(productRandomIndex);
                    if(productIds.contains(product.getId())) {
                        continue;
                    }
                    double unitPrice = NumberHelper.generateRandom(10000, 500000);
                    int quantity = NumberHelper.generateRandom(1, 10);
                    totalMoney += unitPrice * quantity;
                    OrderDetail orderDetail = OrderDetail.builder()
                            .id(OrderDetailId.builder()
                                    .orderId(order.getId())
                                    .productId(product.getId())
                                    .build())
                            .quantity(quantity)
                            .unitPrice(unitPrice)
                            .productName(faker.name().name())
                            .thumbnail(product.getThumbnail())
                            .build();
                    orderDetails.add(orderDetail);
                    productIds.add(product.getId());
                }
                order.setOrderDetails(orderDetails);
                order.setTotalMoney(totalMoney);
                orders.add(order);
            }
        }
        orderRepository.saveAll(orders);
    }

    private LocalDateTime calculateOrderCreatedTime(OrderSeedByTime orderSeedByTime) {
        LocalDateTime result = null;
        LocalDateTime tempLocalDatetime = null;
        int tempMonth = 1;
        int tempYear = 2022;
        switch (orderSeedByTime.getSeedByType()) {
            case YEAR:
                tempMonth = DateTimeHelper.getRandomMonth().getValue();
                tempYear = orderSeedByTime.getYear();
                tempLocalDatetime = LocalDateTime.of(tempYear, tempMonth, 1, 0, 0, 0);
                LocalDateTime lastDayOfMonth = tempLocalDatetime.plusMonths(1).minusDays(1);
                int randomDay = NumberHelper.generateRandom(1, lastDayOfMonth.getDayOfMonth());
                result = LocalDateTime.of(tempYear, tempMonth, randomDay, 0, 0, 0);
                return result;
            case MONTH:
                break;
            case DAY:
                break;
        }
        return null;
    }
}