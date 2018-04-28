package barBossHouse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

interface OrderPredicate<T> {
    boolean test(MenuItem value, T t);
}

class UsingString implements OrderPredicate<String> {
    public boolean test(MenuItem value, String name) {
        return value.getName().equals(name);
    }
}

class UsingObject implements OrderPredicate<Object> {
    public boolean test(MenuItem value, Object o) {
        return value.equals(o);
    }
}

interface PredicateDateCustomer<T> {
    boolean test(Order order, T t);
}

class UsingDate implements PredicateDateCustomer<LocalDate> {
    public boolean test(Order order, LocalDate date) {
        return order.getDateTime().toLocalDate().equals(date);
    }
}

class UsingCustomer implements PredicateDateCustomer<Customer> {
    public boolean test(Order order, Customer customer) {
        return order.getCustomer().equals(customer);
    }
}

class Count<T> {
    public int countItemsQuantity(MenuItem[] items, T t) {
        int quantity = 0;
        for (MenuItem item : items) {
            if (compare(item, t)) quantity++;
        }
        return quantity;
    }

    public boolean compare(MenuItem item, T t) {
        if (t instanceof String) {
            return item.getName().equals(t);
        }
        if (t instanceof MenuItem) {
            return item.equals(t);
        }
        return false;
    }
}

class MenuItemCostComparator implements java.util.Comparator<MenuItem> {
    public int compare(MenuItem o1, MenuItem o2) {
        return o1.compareTo(o2);
    }
}

class Utils {
    public static MenuItem[] sortItemsCostDesc(MenuItem[] itemsToSort) {
        Arrays.sort(itemsToSort, new MenuItemCostComparator().reversed());
        return itemsToSort;
    }
}