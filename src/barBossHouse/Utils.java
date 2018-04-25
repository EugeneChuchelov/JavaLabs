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

class UsingMenuItem implements OrderPredicate<MenuItem> {
    public boolean test(MenuItem value, MenuItem item) {
        return value.equals(item);
    }
}

class UsingObjectOrder implements OrderPredicate<Object> {
    public boolean test(MenuItem value, Object o) {
        return value.equals(o);
    }
}

interface OrderManagerPredicate<T> {
    boolean test(Order value, T t);
}

class UsingOrder implements OrderManagerPredicate<Order> {
    public boolean test(Order value, Order order) {
        return value.equals(order);
    }
}

class UsingObjectOrderManager implements OrderManagerPredicate<Object> {
    public boolean test(Order value, Object o) {
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
    static private LocalTime PROHIBITION_TIME = LocalTime.of(22, 0);

    public static MenuItem[] sortItemsCostDesc(MenuItem[] itemsToSort) {
        Arrays.sort(itemsToSort, new MenuItemCostComparator().reversed());
        return itemsToSort;
    }

    public static void throwUnlawfulActionException(MenuItem item, int age) {
        if (item instanceof Drink) {
            if (((Drink) item).isAlcoholicDrink()) {
                if (age < 18) {
                    throw new UnlawfulActionException("Ordering alcoholic drink is prohibited for kids");
                }
                if (LocalTime.now().isAfter(PROHIBITION_TIME)) {
                    throw new UnlawfulActionException("Ordering alcoholic drink is prohibited after 22:00");
                }
            }
        }
    }
}