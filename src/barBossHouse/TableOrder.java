package barBossHouse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TableOrder extends checkUnlawfulAction implements Order, java.io.Serializable{
    private MenuItem[] items;
    private int size;//хранит кол-во items
    private Customer customer;
    private LocalDateTime orderDateTime;
    public static final int ARRAY_LENGTH_DEFAULT = 16;
    public static final int SIZE_DEFAULT = 0;
    public static final Customer CUSTOMER_EMPTY = new Customer();

    public TableOrder() {
        items = new MenuItem[ARRAY_LENGTH_DEFAULT];
        size = SIZE_DEFAULT;
        customer = CUSTOMER_EMPTY;
        orderDateTime = LocalDateTime.now();
        //в одну милисекунду может создаваться несколько заказов, приходится ждать до следующей
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public TableOrder(Order order){
        items = order.getItems();
        size = order.size();
        customer = order.getCustomer();
        orderDateTime = order.getDateTime();
    }

    public TableOrder(int size, Customer customer) {
        if (size < 0) {
            throw new NegativeSizeException("Array size must be > 0");
        }

        items = new MenuItem[size];
        this.size = SIZE_DEFAULT;
        this.customer = customer;
        orderDateTime = LocalDateTime.now();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public TableOrder(MenuItem[] items, Customer customer) {
        MenuItem[] newItems = new MenuItem[items.length];
        System.arraycopy(items, 0, newItems, 0, items.length);
        this.items = newItems;
        size = newItems.length;
        this.customer = customer;
        orderDateTime = LocalDateTime.now();
    }

    //методы из List
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (MenuItem item : items) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public java.util.Iterator<MenuItem> iterator() {
        return new Iterator();
    }

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(items, 0, newArray, 0, size);
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean remove(Object o) {
        return remove(new UsingObject(), o, 0) != -1;
    }


    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends MenuItem> c) {
        for (MenuItem item : c) {
            add(item);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        MenuItem[] itemsToShift = new MenuItem[size - index];
        System.arraycopy(items, index, itemsToShift, 0, size - index);
        size = index;
        for (MenuItem item : c) {
            add(item);
        }
        for (MenuItem item : itemsToShift) {
            add(item);
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            removeAll((MenuItem) o);
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                removeAll(items[i]);
                i = 0;
            }
        }
        return true;
    }

    public void clear() {
        items = new MenuItem[ARRAY_LENGTH_DEFAULT];
        size = 0;
    }

    public MenuItem get(int index) {
        return items[index];
    }

    public MenuItem set(int index, MenuItem element) {
        MenuItem removedItem = items[index];
        items[index] = element;
        return removedItem;
    }

    public void add(int index, MenuItem item) {
        MenuItem[] itemsToShift = new MenuItem[size - index];
        System.arraycopy(items, index, itemsToShift, 0, size - index);
        size = index + 1;
        items[index] = item;
        addAll(Arrays.asList(itemsToShift));
    }

    public MenuItem remove(int index) {
        MenuItem removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;
        size--;
        return removedItem;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public java.util.ListIterator<MenuItem> listIterator() {
        return new ListIterator(0);
    }

    public java.util.ListIterator<MenuItem> listIterator(int index) {
        return new ListIterator(index);
    }

    public List<MenuItem> subList(int fromIndex, int toIndex) {
        List<MenuItem> a = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            a.add(get(i));
        }
        return a;
    }

    private class Iterator implements java.util.Iterator<MenuItem> {
        int current;

        Iterator() {
        }

        Iterator(int index) {
            current = index;
        }

        public boolean hasNext() {
            return current != size;
        }

        public MenuItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            return items[current++];
        }
    }

    private class ListIterator extends Iterator implements java.util.ListIterator<MenuItem> {
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

        public MenuItem previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            return items[current--];
        }

        public void set(MenuItem item) {
            items[current] = item;
        }

        public void add(MenuItem item) {
            TableOrder.this.add(item);
        }

        public void remove() {
            TableOrder.this.remove(current);
        }
    }

    //end

    public boolean add(MenuItem item) {
        //checkUnlawfulAction(item, customer);
        if (size < items.length) {
            items[size] = item;
            size++;
            return true;
        }
        MenuItem[] newItems = new MenuItem[size * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        newItems[size] = item;
        items = newItems;
        size++;
        return true;
    }

    //Удаление одного элемента по названию или объекту
    private <E> int remove(OrderPredicate<E> predicate, E e, int startPosition) {
        for (int i = startPosition; i < size; i++) {
            if (predicate.test(items[i], e)) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);
                items[size - 1] = null;
                size--;
                return i;
            }
        }
        return -1;
    }

    public boolean remove(String name) {
        return remove(new UsingString(), name, 0) != -1;
    }

    public boolean remove(MenuItem item) {
        return remove(new UsingObject(), item, 0) != -1;
    }

    //end
    //Удаление всех элементов выбранных по названию или объекту
    private <E> int removeAll(OrderPredicate<E> predicate, E e) {
        int lastPosition = 0;
        int removedCount = 0;
        do {
            lastPosition = remove(predicate, e, lastPosition);
            if (lastPosition != -1) removedCount++;
        }
        while (lastPosition != -1);
        return removedCount;
    }

    public int removeAll(String name) {
        return removeAll(new UsingString(), name);
    }

    public int removeAll(MenuItem item) {
        return removeAll(new UsingObject(), item);
    }

    //end
    //Подсчёт элементов выбранных по названию или объекту
    public int itemsQuantity() {
        return size;
    }

    public int itemsQuantity(String name) {
        Count<String> count = new Count<>();
        return count.countItemsQuantity(getItems(), name);
    }

    public int itemsQuantity(MenuItem item) {
        Count<MenuItem> count = new Count<>();
        return count.countItemsQuantity(getItems(), item);
    }

    //end
    //Получение массива элементов заказа
    public MenuItem[] getItems() {
        MenuItem[] items = new MenuItem[size];
        System.arraycopy(this.items, 0, items, 0, size);
        return items;
    }

    //get/set для заказчика
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //get/set для даты и времени
    public LocalDateTime getDateTime() {
        return orderDateTime;
    }

    public void setDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    //Возвращает общую стоимость заказа
    public double costTotal() {
        double cost = 0;
        for (int i = 0; i < size; i++) {
            cost += items[i].getCost();
        }
        return cost;
    }

    //Содержит ли массив строк повторы
    private boolean containsRepeats(String[] names, int counter) {
        for (int i = 0; i < counter; i++) {
            if (names[i].equals(names[counter])) return true;
        }
        return false;
    }

    //Возвращает массив названий блюд без повторов
    public String[] itemsNames() {
        String[] names = new String[size];
        int counter = 0;

        for (int i = 0; i < size; i++) {
            names[counter] = items[i].getName();
            if (!containsRepeats(names, counter)) counter++;
        }

        String[] namesReduced = new String[counter];
        System.arraycopy(names, 0, namesReduced, 0, counter);
        return namesReduced;
    }

    //Возвращает массив элементов, отсортированный по уменьшению цены
    public MenuItem[] sortedItemsByCostDesc() {
        return Utils.sortItemsCostDesc(getItems());
    }

    public String getType(){
        return "TableOrder";
    }

    public String toString() {
        StringBuilder output = new StringBuilder("TableOrder:\n");
        output.append(orderDateTime.toString()).append("\n");
        if (customer != null) {
            output.append(customer.toString()).append("\n");
        }
        output.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            output.append(items[i].toString()).append("\n");
        }
        return output.toString();
    }

    //Проверяет одинаковость элементов заказов учитывая разный порядок добавления в массив
    private boolean isItemsEqual(Order ComparedOrder) {
        int firstCount, secondCount;
        for (int i = 0; i < this.itemsQuantity(); i++) {
            firstCount = this.itemsQuantity(this.getItems()[i]);
            secondCount = ComparedOrder.itemsQuantity(this.getItems()[i]);
            if (firstCount != secondCount) return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TableOrder) {
            return ((TableOrder) obj).customer.equals(customer) &&
                    ((TableOrder) obj).size == size && isItemsEqual((TableOrder) obj) && orderDateTime.equals(((TableOrder) obj).orderDateTime);
        }
        return false;
    }

    public boolean customerAndDateEquals(Order order) {
        return order.getDateTime().equals(orderDateTime) && order.getCustomer().equals(customer);
    }

    public int hashCode() {
        int hash = size;
        hash ^= customer.hashCode();
        for (int i = 0; i < size; i++) {
            hash ^= items[i].hashCode();
        }
        hash ^= orderDateTime.hashCode();
        return hash;
    }
}