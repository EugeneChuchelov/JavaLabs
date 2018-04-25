package barBossHouse;

import java.time.LocalDate;

public interface OrdersManager extends java.util.Collection<Order> {
    int ordersQuantity();

    Order[] getOrders();

    double ordersCostSummary();

    int itemQuantity(String name);

    int itemQuantity(MenuItem item);

    int ordersQuantityOnDay(LocalDate date);

    Order[] getOrdersOnDay(LocalDate date);

    Order[] getCustomerOrders(Customer customer);
}
