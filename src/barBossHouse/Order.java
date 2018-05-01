package barBossHouse;

import java.time.LocalDateTime;
import java.util.List;

public interface Order extends List<MenuItem> {

    boolean add(MenuItem item);

    String[] itemsNames();

    int itemsQuantity();

    int itemsQuantity(String name);

    int itemsQuantity(MenuItem item);

    MenuItem[] getItems();

    boolean remove(String name);

    boolean remove(MenuItem item);

    int removeAll(String name);

    int removeAll(MenuItem item);

    MenuItem[] sortedItemsByCostDesc();

    double costTotal();

    Customer getCustomer();

    LocalDateTime getDateTime();

    void setDateTime(LocalDateTime dateTime);

    void setCustomer(Customer customer);

    String getType();

    String toString();

    boolean equals(Object obj);

    boolean customerAndDateEquals(Order order);

    int hashCode();
}