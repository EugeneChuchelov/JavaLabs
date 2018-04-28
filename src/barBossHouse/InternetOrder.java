package barBossHouse;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class InternetOrder extends checkUnlawfulAction implements Order {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime orderDateTime;

    public InternetOrder() {
        head = new ListNode();
        orderDateTime = LocalDateTime.now();
    }

    public InternetOrder(Order order){
        size = order.size();
        customer = order.getCustomer();
        orderDateTime = order.getDateTime();
        addAll(Arrays.asList(order.getItems()));
    }

    public InternetOrder(MenuItem[] items, Customer customer) {
        this.customer = customer;
        orderDateTime = LocalDateTime.now();
        head = new ListNode();
        addAll(Arrays.asList(items));
    }

    private class ListNode {
        ListNode next;
        ListNode previous;
        MenuItem value;

        ListNode() {
        }

        ListNode(MenuItem value) {
            this.value = value;
        }
    }

//методы из List
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (MenuItem item : this) {
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public java.util.Iterator<MenuItem> iterator() {
        return new InternetOrder.Iterator(); //todo: rename Iter+
    }

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        int i = 0;
        for (MenuItem item : this) {
            newArray[i++] = item;
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (MenuItem item : this) {
            result[i++] = item;
        }

        if (a.length > size)
            a[size] = null;

        return a;
    }

    public boolean remove(Object o) {
        return remove(new UsingObject(), o, head) != null;
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
        for (MenuItem item : c) {
            add(index, item);
            index++;
        }
        return true;
    }

    //todo+
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            removeAll((MenuItem) o);
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (MenuItem item : this) {
            if (!c.contains(item)) {
                removeAll(item);
            }
        }
        return true;
    }

    public void clear() {
        head = new ListNode();
        tail = null;
    }

    public MenuItem get(int index) {
        return getNode(index).value;
    }

    public MenuItem set(int index, MenuItem element) {
        ListNode current = getNode(index);
        MenuItem removedItem = current.value;
        current.value = element;
        return removedItem;
    }

    public void add(int index, MenuItem item) {
        checkUnlawfulAction(item, customer);
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Oversize");
        }
        ListNode current = getNode(index);
        ListNode newNode = new ListNode(item);
        if (current.previous == null) {
            newNode.next = head;
            head = newNode;
        } else {
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
        }
        size++;
    }

    public MenuItem remove(int index) {

        ListNode current = getNode(index);
        MenuItem removedItem = current.value;
        current.next.previous = current.previous;
        current.previous.next = current.next;
        size--;
        return removedItem;
    }

    public int indexOf(Object o) {
        int i = 0;
        for (MenuItem item : this) {
            if (item.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int i = 0;
        for (ListNode node = tail; node != null; node = node.previous) {
            if (node.value.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public java.util.ListIterator<MenuItem> listIterator() {
        return new InternetOrder.ListIterator(0);
    }

    public java.util.ListIterator<MenuItem> listIterator(int index) {
        return new InternetOrder.ListIterator(index);
    }

    public List<MenuItem> subList(int fromIndex, int toIndex) {
        List<MenuItem> a = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            a.add(get(i));
        }
        return a;
    }

    private class Iterator implements java.util.Iterator<MenuItem> {
        ListNode current;

        Iterator() {
            current = head;
        }

        Iterator(int index) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public MenuItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            MenuItem toReturn = current.value;
            current = current.next;
            return toReturn;
        }
    }

    private class ListIterator extends InternetOrder.Iterator implements java.util.ListIterator<MenuItem> {
        ListIterator(int index) {
            super(index);
        }

        public int nextIndex() {
            if (hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            return InternetOrder.this.indexOf(current) + 1;
        }

        public boolean hasPrevious() {
            return current != null;
        }

        public int previousIndex() {
            if (hasPrevious()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            return InternetOrder.this.indexOf(current) + 1;
        }

        public MenuItem previous() {
            if (hasPrevious()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            MenuItem toReturn = current.value;
            current = current.previous;
            return toReturn;
        }

        public void set(MenuItem item) {
            current.value = item;
        }

        public void add(MenuItem item) {
            InternetOrder.this.add(item);
        }

        public void remove() {
            InternetOrder.this.remove(current);
        }
    }

//end
    public boolean add(MenuItem item) {
        checkUnlawfulAction(item, customer);
        ListNode node = new ListNode(item);
        if (size == 0) {
            head = node;
            tail = node;
            head.next = null;
            head.previous = null;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    //Удаление одного элемента по названию или объекту
    private <E> ListNode remove(OrderPredicate<E> strategy, E e, ListNode previous) {
        ListNode current;
        if (previous == null) {
            current = head;
        } else {
            current = previous.next;
        }
        while (current != null) {
            if (strategy.test(current.value, e)) {
                if (previous == null) {
                    head = head.next;
                    head.previous = null;
                    if (head == null) tail = null;
                } else {
                    current.next.previous = previous;
                    previous.next = current.next;
                    if (current.next == null) tail = previous;
                }
                size--;
                return previous;
            }
            previous = current;
            current = current.next;
        }
        return tail;
    }

    public boolean remove(String name) {
        return remove(new UsingString(), name, null) != null;
    }

    public boolean remove(MenuItem item) {
        return remove(new UsingObject(), item, null) != null;
    }

    //end
    //Удаление всех элементов, выбранных по названию или объекту
    private <E> int removeAll(OrderPredicate<E> predicate, E e) {
        int removedCount = 0;
        ListNode returnedNode = null;
        do {
            returnedNode = remove(predicate, e, returnedNode);
            if (returnedNode != null) removedCount++;
        }
        while (returnedNode != tail);
        return removedCount;
    }

    public int removeAll(String name) {
        return removeAll(new UsingString(), name);
    }

    public int removeAll(MenuItem item) {
        return removeAll(new UsingObject(), item);
    }

    //end
    public int itemsQuantity() {
        return size;
    }

    //Подсчёт элементов, выбранных по названию или объекту
    private <E> int countItemsQuantity(E nameOrItem) {
        ListNode node = head;
        int quantity = 0;
        Count<E> comp = new Count<>();
        while (node != null) {
            if (comp.compare(node.value, nameOrItem)) quantity++;
            node = node.next;
        }
        return quantity;
    }

    public int itemsQuantity(String name) {
        //Count<String> count = new Count<>();
        return countItemsQuantity(name);
    }

    public int itemsQuantity(MenuItem item) {
        //Count<MenuItem> count = new Count<>();
        return countItemsQuantity(item);
    }

    //end
    //Получение массива элементов заказа
    public MenuItem[] getItems() {
        ListNode node = head;
        MenuItem[] items = new MenuItem[size];
        int i = 0;
        while (node != null) {
            items[i] = node.value;
            node = node.next;
            i++;
        }
        return items;
    }

    private ListNode getNode(int index) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
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
        ListNode node = head;
        double cost = 0;
        while (node != null) {
            cost += node.value.getCost();
            node = node.next;
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
        ListNode node = head;
        String[] names = new String[size];
        int counter = 0;
        while (node != null) {
            names[counter] = node.value.getName();
            if (!containsRepeats(names, counter)) counter++;
            node = node.next;
        }

        String[] namesReduced = new String[counter];
        System.arraycopy(names, 0, namesReduced, 0, counter);
        return namesReduced;
    }

    //Возвращает массив элементов, отсортированный по уменьшению цены
    public MenuItem[] sortedItemsByCostDesc() {
        return Utils.sortItemsCostDesc(getItems());
    }

    public String toString() {
        ListNode node = head;
        StringBuilder output = new StringBuilder("InternetOrder:\n");
        output.append(orderDateTime.toString()).append("\n");
        if (customer != null) output.append(customer.toString()).append("\n");
        output.append(size).append("\n");
        while (node != null) {
            output.append(node.value.toString()).append("\n");
            node = node.next;
        }
        return output.toString();
    }

    //Проверяет одинаковость элементов заказов учитывая разный порядок добавления в список
    private boolean isItemsEqual(Order comparedOrder) {
        int firstCount, secondCount;
        ListNode currentNode = head;
        while (currentNode != null) {
            firstCount = itemsQuantity(currentNode.value);
            secondCount = comparedOrder.itemsQuantity(currentNode.value);
            if (firstCount != secondCount) return false;
            currentNode = currentNode.next;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetOrder) {
            return ((InternetOrder) obj).customer.equals(customer) &&
                    ((InternetOrder) obj).size == size && isItemsEqual((InternetOrder) obj) && orderDateTime.equals(((InternetOrder) obj).orderDateTime);
        }
        return false;
    }

    public boolean customerAndDateEquals(Order order) {
        return order.getDateTime().equals(orderDateTime) && order.getCustomer().equals(customer);
    }

    public int hashCode() {
        int hash = size;
        ListNode node = head;
        hash ^= customer.hashCode();
        while (node != null) {
            hash ^= node.value.hashCode();
            node = node.next;
        }
        hash ^= orderDateTime.hashCode();
        return hash;
    }
}
