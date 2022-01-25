package day01;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orders
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());

    }

    public long getNumberOfOrdesrByStatus(String status) {
        return orders
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .count();
    }

    public List<Order> getOrdersBetweenTwoDates(LocalDate from, LocalDate to) {
        return orders
                .stream()
                .filter(order -> order.getOrderDate().isAfter(from) && order.getOrderDate().isBefore(to))
                .collect(Collectors.toList());
    }

    public boolean isAnyOrderWithProductsLessThanParam(int limit) {
        return orders
                .stream()
//                .map(order -> order.getProducts().size())
//                .anyMatch(i -> i < limit);
                .anyMatch(order -> order.getProducts().size() < limit);
    }

    public Order getOrderWithMostProducts() {
        return orders
                .stream()
                .max(Comparator.comparingInt(order -> order.getProducts().size()))
                .orElseThrow(() -> new IllegalArgumentException("Orders list is empty"));
//             .sorted(Collections.reverseOrder(Comparator.comparing(order -> order.getProducts().size())))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Orders list is empty"));
    }

    public List<Order> getOrdersWithGivenCategory(String category) {
        return orders
                .stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory().equals(category)))
                .collect(Collectors.toList());

    }

}
