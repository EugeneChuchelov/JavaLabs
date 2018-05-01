package io;

import barBossHouse.*;

import java.util.ArrayList;
import java.util.Collection;

public class ControlledTableOrderManager extends TableOrderManager{

    protected Source<Order> source;

    public ControlledTableOrderManager(int tablesCount) {
        super(tablesCount);
    }

    public Source<Order> getSource() {
        return source;
    }

    public void setSource(Source<Order> source) {
        this.source = source;
    }

    public void store(){
        for(Order order : super.getOrders()){
            if(((ControlledTableOrder)order).isChanged){
                source.store(order);
            }
        }
    }

    public void load(){
        for(Order order : super.getOrders()){
            source.load(order);
        }
    }

    @Override
    public void clear() {
        for(Order order : super.getOrders()){
            source.delete(order);
        }
        super.clear();
    }

    @Override
    public void addItem(MenuItem item, int tableNumber) {
        super.addItem(item, tableNumber);
        source.store(super.get(tableNumber));
    }

    @Override
    public boolean add(Order order) {
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(order);
        source.create(controlledTableOrder);
        return super.add(controlledTableOrder);
    }

    @Override
    public void add(int index, Order order) {
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(order);
        source.create(controlledTableOrder);
        super.add(index, controlledTableOrder);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        ArrayList<ControlledTableOrder> list = new ArrayList<>();
        ControlledTableOrder controlledTableOrder;
        for(Order order : c){
            controlledTableOrder = new ControlledTableOrder(order);
            list.add(controlledTableOrder);
            source.create(controlledTableOrder);
        }
        return super.addAll(list);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        ArrayList<ControlledTableOrder> list = new ArrayList<>();
        ControlledTableOrder controlledTableOrder;
        for(Order order : c){
            controlledTableOrder = new ControlledTableOrder(order);
            list.add(controlledTableOrder);
            source.create(controlledTableOrder);
        }
        return super.addAll(index, list);
    }

    @Override
    public Order set(int index, Order element) {
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(element);
        source.create(controlledTableOrder);
        return super.set(index, controlledTableOrder);
    }

    @Override
    public boolean remove(Object o) {
        source.delete((Order) o);
        return super.remove(o);
    }

    @Override
    public Order remove(int index) {
        source.delete(super.get(index));
        return super.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c){
            source.delete((Order) o);
        }
        return super.removeAll(c);
    }

    @Override
    public int removeAll(Order order) {
        for (int i = 0; i < super.orderQuantity(order); i++){
            source.delete(order);
        }
        return super.removeAll(order);
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
