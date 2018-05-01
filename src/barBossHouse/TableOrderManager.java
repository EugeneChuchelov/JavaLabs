package barBossHouse;

import java.time.LocalDate;
import java.util.*;


public class TableOrderManager implements OrdersManager, List<Order> {
    private interface TablesCountPredicate {
        boolean execute(Order order);
    }

    private class PredicateFree implements TablesCountPredicate {
        public boolean execute(Order order) {
            return order == null;
        }
    }

    private class PredicateOccupied implements TablesCountPredicate {
        public boolean execute(Order order) {
            return order != null;
        }
    }

    private Order[] orders;

    public TableOrderManager(int tablesCount) {
        if (tablesCount < 0) {
            throw new NegativeSizeException("Array size must be > 0");
        }

        orders = new Order[tablesCount];
    }

    //методы из List
    public boolean add(Order order) {
        /*if(size() == 0){
            orders[0] = order;
        }
        for (int i = 0; i < size(); i++) {
            if (orders[i] == null) {
                orders[i] = order;
                return true;
            }
        }
        return false;*/
        if(size() >= orders.length){
            return false;
        }
        orders[size()] = order;
        return true;
    }

    public int size() {
        return ordersQuantity();
    }

    public boolean isEmpty() {
        return ordersQuantity() == 0;
    }

    public boolean contains(Object o) {
        for (Order order : orders) {
            if (order.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public java.util.Iterator<Order> iterator() {
        return new Iterator();
    }

    public Object[] toArray() {
        Object[] newArray = new Object[ordersQuantity()];
        int i = 0;
        for (Order order : orders) {
            if (order != null) {
                newArray[i] = order;
                i++;
            }
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int size = ordersQuantity();
        Object[] orders = toArray();
        if (a.length < size)
            return (T[]) Arrays.copyOf(orders, size, a.getClass());
        System.arraycopy(orders, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean remove(Object o) {
        int i;
        for (i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].equals(o)) {
                orders[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends Order> c) {
        int[] freeTables = freeTableNumbers();
        if (c.size() > freeTables.length) {
            throw new RuntimeException("Not enough free tables");
        }
        int i = 0;
        for (Order order : c) {
            orders[freeTables[i]] = order;
            i++;
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends Order> c) {
        int[] freeTables = freeTableNumbers();
        int i = 0;
        while (freeTables[i] < index) {
            i++;
        }
        if (c.size() > freeTables.length - i) {
            throw new RuntimeException("Not enough free tables");
        }
        for (Order order : c) {
            orders[freeTables[i]] = order;
            i++;
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            removeAll((Order) o);
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            if (!c.contains(orders[i])) {
                removeAll(orders[i]);
                i = 0;
            }
        }
        return true;
    }

    public void clear() {
        orders = new Order[orders.length];
    }

    public Order get(int index) {
        return orders[index];
    }

    public Order set(int index, Order element) {
        Order removedItem = orders[index];
        orders[index] = element;
        return removedItem;
    }

    public void add(int index, Order order) {
        if (orders[index] != null) {
            throw new RuntimeException("Table already occupied");
        }
        set(index, order);
    }

    public Order remove(int index) {
        Order removedItem = orders[index];
        orders[index] = null;
        return removedItem;
    }
    public int indexOf(Object o) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = orders.length - 1; i >= 0; i--) {
            if (orders[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public java.util.ListIterator<Order> listIterator() {
        return new ListIterator(0);
    }

    public java.util.ListIterator<Order> listIterator(int index) {
        return new ListIterator(index);
    }

    public List<Order> subList(int fromIndex, int toIndex) {
        List<Order> a = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            a.add(get(i));
        }
        return a;
    }

    private class Iterator implements java.util.Iterator<Order> {
        int current;

        Iterator() {
        }

        Iterator(int index) {
            current = index;
        }

        public boolean hasNext() {
            return current != size();
        }

        public Order next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            return orders[current++];
        }
    }

    private class ListIterator extends Iterator implements java.util.ListIterator<Order> {
        ListIterator(int index) {
            super(index);
        }

        public int nextIndex() {
            if (hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            return current + 1;
        }

        public boolean hasPrevious() {
            return current != 0;
        }

        public int previousIndex() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            return current - 1;
        }

        public Order previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            return orders[current--];
        }

        public void set(Order order) {
            orders[current] = order;
        }

        public void add(Order item) {
            TableOrderManager.this.add(item);
        }

        public void remove() {
            TableOrderManager.this.remove(current);
        }
    }

//end

    /*public void add(Order order, int tableNumber) throws AlreadyAddedException {
        if (orders[tableNumber] != null) {
            throw new AlreadyAddedException("Table already occupied");
        }

        orders[tableNumber] = order;
    }*/

    /*public Order getOrder(int tableNumber) {
        return orders[tableNumber];
    }*/

    public void addItem(MenuItem item, int tableNumber) {
        orders[tableNumber].add(item);
    }

    /*public void remove(int tableNumber)
    {
        orders[tableNumber] = null;
    }*/
    /*public int remove(Order order) {
        int i;
        for (i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].equals(order)) {
                orders[i] = null;
                return i;
            }
        }
        return -1;
    }*/

    public int removeAll(Order order) {
        boolean isRemoved;
        int removedCount = 0;
        do {
            isRemoved = remove(order);
            if (isRemoved) removedCount++;
        }
        while (isRemoved);
        if (removedCount == 0) return -1;
        else return removedCount;
    }

    public int freeTableNumber() throws NoFreeTableException {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) return i;
        }
        throw new NoFreeTableException("No free tables");
    }

    //Считает свободные или занятые столы
    private int[] CountTables(TablesCountPredicate predicate) {
        int count = 0, j = 0;
        for (Order order : orders) if (predicate.execute(order)) count++;
        int[] numbers = new int[count];
        for (int i = 0; i < orders.length; i++) {
            if (predicate.execute(orders[i])) {
                numbers[j] = i;
                j++;
            }
        }
        return numbers;
    }

    public int[] freeTableNumbers() {
        return CountTables(new PredicateFree());
    }

    public int[] occupiedTableNumbers() {
        return CountTables(new PredicateOccupied());
    }

    //end
    public Order[] getOrders() {
        int[] numbers = occupiedTableNumbers();
        Order[] orders = new Order[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            orders[i] = this.orders[numbers[i]];
        }
        return orders;
    }

    public double ordersCostSummary() {
        double costSummary = 0;
        for (Order order : orders) {
            if (order != null) costSummary += order.costTotal();
        }
        return costSummary;
    }

    private <E> int countItemQuantity(Count<E> count, E e) {
        int quantity = 0;
        for (Order order : orders) {
            if (order != null) quantity += count.countItemsQuantity(order.getItems(), e);
        }
        return quantity;
    }

    public int itemQuantity(String name) {
        Count<String> countString = new Count<>();
        return countItemQuantity(countString, name);
    }

    public int itemQuantity(MenuItem item) {
        Count<MenuItem> countMenuItem = new Count<>();
        return countItemQuantity(countMenuItem, item);
    }

    public int ordersQuantity() {
        int quantity = 0;
        for (Order order : orders) {
            if (order != null) quantity++;
        }
        return quantity;
    }

    private <E> int ordersQuantity(PredicateDateCustomer<E> predicate, E e) {
        int counter = 0;
        for (Order order : orders) {
            if (order != null && predicate.test(order, e)) {
                counter++;
            }
        }
        return counter;
    }

    public int ordersQuantityOnDay(LocalDate date) {
        return ordersQuantity(new UsingDate(), date);
    }

    public int ordersQuantityOnDay(Customer customer) {
        return ordersQuantity(new UsingCustomer(), customer);
    }

    private <E> Order[] getOrders(PredicateDateCustomer<E> predicate, E e) {
        int counter = 0;
        Order[] orders = new Order[ordersQuantity(predicate, e)];
        for (Order order : this.orders) {
            if (order != null && predicate.test(order, e)) {
                orders[counter] = order;
                counter++;
            }
        }
        return orders;
    }

    public Order[] getOrdersOnDay(LocalDate date) {
        return getOrders(new UsingDate(), date);
    }

    public Order[] getCustomerOrders(Customer customer) {
        return getOrders(new UsingCustomer(), customer);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(ordersQuantity()).append(" orders").append("\n\n");
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                output.append("Table #").append(i).append("\n");
                output.append(orders[i].toString()).append("\n");
            }
        }
        return output.toString();
    }

    public int orderQuantity(Order order) {
        int quantity = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].equals(order)) quantity++;
        }
        return quantity;
    }

    private boolean isManagerEquals(TableOrderManager comparedManager) {
        int thisCount, comparedCount;
        for (int i = 0; i < orders.length; i++) {
            thisCount = orderQuantity(orders[i]);
            comparedCount = comparedManager.orderQuantity(orders[i]);
            if (thisCount != comparedCount) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TableOrderManager) {
            return ordersQuantity() == ((TableOrderManager) obj).ordersQuantity() && isManagerEquals((TableOrderManager) obj);
        }
        return false;
    }

    public int hashCode() {
        int hash = 0;
        for (Order order : orders) {
            if (order != null) {
                hash ^= order.hashCode();
            }
        }
        return hash;
    }
}
