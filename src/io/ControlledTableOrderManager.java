package io;

import barBossHouse.*;

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

    /*public void store(){
        for(Order order : super){
            
        }
    }*/

    @Override
    public boolean add(Order order) {
        source.create(new ControlledTableOrder(order));
        return super.add(order);
    }

    @Override
    public void add(int index, Order order) {
        source.create(new ControlledTableOrder(order));
        super.add(index, order);
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        for(Order order : c){
            source.create(new ControlledTableOrder(order));
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        for(Order order : c){
            source.create(new ControlledTableOrder(order));
        }
        return super.addAll(index, c);
    }

    @Override
    public Order set(int index, Order element) {
        source.create(new ControlledTableOrder(element));
        return super.set(index, element);
    }

    @Override
    public boolean remove(Object o) {
        source.delete(new ControlledTableOrder((Order) o));
        return super.remove(o);
    }

    @Override
    public Order remove(int index) {
        source.delete(new ControlledTableOrder(super.get(index)));
        return super.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c){
            source.create(new ControlledTableOrder((Order) o));
        }
        return super.removeAll(c);
    }

    @Override
    public int removeAll(Order order) {
        for (int i = 0; i < super.orderQuantity(order); i++){
            source.delete(new ControlledTableOrder(order));
        }
        return super.removeAll(order);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //todo: Think out something
        return super.retainAll(c);
    }
}
