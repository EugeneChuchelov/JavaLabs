package io;

import barBossHouse.*;

import java.util.ArrayList;
import java.util.Collection;

public class ControlledInternetOrderManager extends InternetOrdersManager{

    protected Source<Order> source;

    public ControlledInternetOrderManager(){

    }

    public ControlledInternetOrderManager(Order[] orders) throws AlreadyAddedException{
        super(orders);
    }

    public Source<Order> getSource() {
        return source;
    }

    public void setSource(Source<Order> source) {
        this.source = source;
    }

    @Override
    public void clear() {
        for(Order order : super.getOrders()){
            source.delete(order);
        }
        super.clear();
    }

    @Override
    public boolean add(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        return super.add(controlledInternetOrder);
    }

    @Override
    public boolean offer(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        return super.offer(controlledInternetOrder);
    }

    @Override
    public boolean offerFirst(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        return super.offerFirst(controlledInternetOrder);
    }

    @Override
    public boolean offerLast(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        return super.offerLast(controlledInternetOrder);
    }

    @Override
    public void addFirst(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        super.addFirst(controlledInternetOrder);
    }

    @Override
    public void addLast(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        super.addLast(controlledInternetOrder);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        ArrayList<ControlledInternetOrder> list = new ArrayList<>();
        ControlledInternetOrder controlledInternetOrder;
        for(Order order : c){
            controlledInternetOrder = new ControlledInternetOrder(order);
            list.add(controlledInternetOrder);
            source.create(controlledInternetOrder);
        }
        return super.addAll(list);
    }

    @Override
    public void push(Order order) {
        ControlledInternetOrder controlledInternetOrder = new ControlledInternetOrder(order);
        source.create(controlledInternetOrder);
        super.push(controlledInternetOrder);
    }

    @Override
    public Order pop() {
        Order order = super.pop();
        source.delete(order);
        return order;
    }

    @Override
    public Order pollFirst() {
        Order order = super.pollFirst();
        source.delete(order);
        return order;
    }

    @Override
    public Order pollLast() {
        Order order = super.pollLast();
        source.delete(order);
        return order;
    }

    @Override
    public Order poll() {
        Order order = super.poll();
        source.delete(order);
        return order;
    }

    @Override
    public Order removeFirst() {
        Order order = super.removeFirst();
        source.delete(order);
        return order;
    }

    @Override
    public Order removeLast() {
        Order order = super.removeLast();
        source.delete(order);
        return order;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        source.delete((Order) o);
        return super.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        source.delete((Order) o);
        return super.removeLastOccurrence(o);
    }

    @Override
    public Order remove() {
        Order order = super.remove();
        source.delete(order);
        return order;
    }

    @Override
    public boolean remove(Object o) {
        source.delete((Order) o);
        return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c){
            source.delete((Order) o);
        }
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Order order : super.getOrders()){
            if (!c.contains(order)) {
                for(int i = 0; i < super.orderQuantity(order); i++){
                    source.delete(order);
                }
            }
        }
        return super.retainAll(c);
    }
}
