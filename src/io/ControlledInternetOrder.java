package io;

import barBossHouse.*;

import java.time.LocalDateTime;
import java.util.Collection;

public class ControlledInternetOrder extends InternetOrder {
    protected boolean isChanged = false;

    public ControlledInternetOrder() {
        super();
    }

    public ControlledInternetOrder(Order order) {
        super(order);
    }

    public ControlledInternetOrder(MenuItem[] items, Customer customer) {
        super(items, customer);
    }

    @Override
    public boolean remove(Object o) {
        isChanged = true;
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        isChanged = true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        isChanged = true;
        return super.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        isChanged = true;
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        isChanged = true;
        return super.retainAll(c);
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        isChanged = true;
        return super.set(index, element);
    }

    @Override
    public void add(int index, MenuItem item) {
        isChanged = true;
        super.add(index, item);
    }

    @Override
    public MenuItem remove(int index) {
        isChanged = true;
        return super.remove(index);
    }

    @Override
    public boolean add(MenuItem item) {
        isChanged = true;
        return super.add(item);
    }

    @Override
    public boolean remove(String name) {
        isChanged = true;
        return super.remove(name);
    }

    @Override
    public boolean remove(MenuItem item) {
        isChanged = true;
        return super.remove(item);
    }

    @Override
    public int removeAll(String name) {
        isChanged = true;
        return super.removeAll(name);
    }

    @Override
    public int removeAll(MenuItem item) {
        isChanged = true;
        return super.removeAll(item);
    }

    @Override
    public void setCustomer(Customer customer) {
        isChanged = true;
        super.setCustomer(customer);
    }

    @Override
    public void setDateTime(LocalDateTime orderDateTime) {
        isChanged = true;
        super.setDateTime(orderDateTime);
    }
}
