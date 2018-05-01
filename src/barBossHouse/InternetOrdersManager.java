package barBossHouse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;

public class InternetOrdersManager implements OrdersManager, Deque<Order> {
    private int size;
    private QueueNode head;
    private QueueNode tail;

    public InternetOrdersManager() {
    }

    public InternetOrdersManager(Order[] orders) throws AlreadyAddedException {
        head = new QueueNode();
        addAll(Arrays.asList(orders));
    }

    private class QueueNode {
        QueueNode next;
        QueueNode previous;
        Order value;

        QueueNode() {
        }

        QueueNode(Order order) {
            value = order;
        }
    }

    //Deque methods
    public void addFirst(Order order) {
        QueueNode node = new QueueNode(order);
        if (size == 0) {
            head = node;
            tail = node;
            head.next = null;
            head.previous = null;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
    }

    public void addLast(Order order) {
        QueueNode node = new QueueNode(order);
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
    }

    public boolean offerFirst(Order order) {
        addFirst(order);
        return true;
    }

    public boolean offerLast(Order order) {
        addLast(order);
        return true;
    }

    private Order removeHead() {
        QueueNode node = head;
        head = head.next;
        size--;
        return node.value;
    }

    private Order removeTail() {
        QueueNode node = tail;
        tail = tail.previous;
        size--;
        return node.value;
    }

    public Order removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Orders manager is empty");
        }
        return removeHead();
    }

    public Order removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Orders manager is empty");
        }
        return removeTail();
    }

    public Order pollFirst() {
        if (size == 0) {
            return null;
        }
        return removeHead();
    }

    public Order pollLast() {
        if (size == 0) {
            return null;
        }
        return removeTail();
    }

    public Order getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Orders manager is empty");
        }
        return head.value;
    }

    public Order getLast() {
        if (size == 0) {
            throw new NoSuchElementException("Orders manager is empty");
        }
        return tail.value;
    }

    public Order peekFirst() {
        if (size == 0) {
            return null;
        }
        return head.value;
    }

    public Order peekLast() {
        if (size == 0) {
            return null;
        }
        return tail.value;
    }

    public boolean removeFirstOccurrence(Object o) {
        return remove(o, null) != tail;
    }

    public boolean removeLastOccurrence(Object o) {
        for(QueueNode node = tail; node != null; node = node.previous){
            if (node.value.equals(o)) {
                if (node.previous == null) {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    node.previous.next = node.next;
                    if (node.next == null) {
                        tail = node.previous;
                    }
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean add(Order order) {
        addLast(order);
        return true;
    }

    public boolean offer(Order order) {
        return offerLast(order);
    }

    public Order remove() {
        return removeFirst();
    }

    public Order poll() {
        return pollFirst();
    }

    public Order element() {
        return getFirst();
    }

    public Order peek() {
        return peekFirst();
    }

    public void push(Order order) {
        addFirst(order);
    }

    public Order pop() {
        return removeFirst();
    }

    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    public boolean contains(Object o) {
        for(Order order : this){
            if(order.equals(o)){
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public java.util.Iterator<Order> iterator() {
        return new Iterator();
    }

    public java.util.Iterator<Order> descendingIterator() {
        return new DescendingIterator();
    }

    private class Iterator implements java.util.Iterator<Order> {
        QueueNode current;

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

        public Order next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next item don't exist");
            }
            Order toReturn = current.value;
            current = current.next;
            return toReturn;
        }
    }

    private class DescendingIterator implements java.util.Iterator<Order> {
        QueueNode current;

        DescendingIterator() {
            current = head;
        }

        DescendingIterator(int index) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public Order next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Previous item don't exist");
            }
            Order toReturn = current.value;
            current = current.previous;
            return toReturn;
        }
    }

    //end
//Collection methods
    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] newArray = new Object[size];
        int i = 0;
        for (Order order : this) {
            newArray[i++] = order;
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
        for (Order order : this) {
            result[i++] = order;
        }

        if (a.length > size)
            a[size] = null;

        return a;
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
        for (Order order : c) {
            add(order);
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            removeAll(o);
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        for (Order order : this) {
            if (!c.contains(order)) {
                removeAll(order);
            }
        }
        return true;
    }

    public void clear() {
        head = new QueueNode();
        tail = null;
        size = 0;
    }

//end
    /*public boolean add(Order order) throws AlreadyAddedException{
        if (contains(order)) {
            throw new AlreadyAddedException("This order is already exist");
        }
        QueueNode node = new QueueNode(order);
        if(size == 0){
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
    }*/
    private boolean containsByCustomerAndDate(Order order) {
        QueueNode node = head;
        while (node != null) {
            if (order.customerAndDateEquals(node.value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private QueueNode remove(Object o, QueueNode previous) {
        QueueNode node;
        if (previous == null) {
            node = head;
        } else {
            node = previous.next;
        }
        while (node != null) {
            if (node.value.equals(o)) {
                if (node.previous == null) {
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    node.previous.next = node.next;
                    if (node.next == null) {
                        tail = node.previous;
                    }
                }
                size--;
                return node.previous;
            }
            node = node.next;
        }
        return tail;
    }
    private void removeAll(Object o) {
        QueueNode returnedNode = null;
        do {
            returnedNode = remove(o, returnedNode);
        }
        while (returnedNode != tail);
    }
    /*public Order getFirst(){
        return head.value;
    }
    public Order getFirstAndRemove(){
        QueueNode node = head;
        head = head.next;
        size--
        return node.value;
    }*/
    public int ordersQuantity() {
        return size;
    }

    public Order[] getOrders() {
        QueueNode node = head;
        Order[] orders = new Order[size];
        int i = 0;
        while (node != null) {
            orders[i] = node.value;
            i++;
            node = node.next;
        }
        return orders;
    }

    public double ordersCostSummary() {
        QueueNode node = head;
        double cost = 0;
        while (node != null) {
            cost += node.value.costTotal();
            node = node.next;
        }
        return cost;
    }

    private <E> int countItemQuantity(Count<E> count, E e) {
        QueueNode node = head;
        int quantity = 0;
        while (node != null) {
            quantity += count.countItemsQuantity(node.value.getItems(), e);
            node = node.next;
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

    private <E> int ordersQuantity(PredicateDateCustomer<E> predicate, E e) {
        int counter = 0;
        QueueNode node = head;
        while (node != null) {
            if (predicate.test(node.value, e)) {
                counter++;
            }
            node = node.next;
        }
        return counter;
    }

    public int ordersQuantityOnDay(LocalDate date) {
        return ordersQuantity(new UsingDate(), date);
    }

    public int customerOrdersQuantity(Customer customer) {
        return ordersQuantity(new UsingCustomer(), customer);
    }

    private <E> Order[] getOrders(PredicateDateCustomer<E> predicate, E e) {
        QueueNode node = head;
        int counter = 0;
        Order[] orders = new Order[ordersQuantity(predicate, e)];
        while (node != null) {
            if (predicate.test(node.value, e)) {
                orders[counter] = node.value;
                counter++;
            }
            node = node.next;
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
        QueueNode node = head;
        int i = 0;
        while (node != null) {
            output.append("Order #").append(i).append("\n");
            output.append(node.value.toString()).append("\n");
            i++;
            node = node.next;
        }
        return output.toString();
    }

    public int orderQuantity(Order order) {
        QueueNode node = head;
        int quantity = 0;
        while (node != null) {
            if (node.value.equals(order)) quantity++;
            node = node.next;
        }
        return quantity;
    }

    private boolean isManagerEquals(InternetOrdersManager comparedManager) {
        int thisCount = 0, comparedCount = 0;
        QueueNode currentNode = head;
        while (currentNode != null) {
            thisCount = orderQuantity(currentNode.value);
            comparedCount = comparedManager.orderQuantity(currentNode.value);
            if (thisCount != comparedCount) {
                return false;
            }
            currentNode = currentNode.next;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetOrdersManager) {
            return ((InternetOrdersManager) obj).ordersQuantity() == ordersQuantity() && isManagerEquals((InternetOrdersManager) obj);
        }
        return false;
    }

    public int hashCode() {
        int hash = 0;
        QueueNode node = head;
        while (node != null) {
            hash ^= node.value.hashCode();
            node = node.next;
        }
        return hash;
    }
}
