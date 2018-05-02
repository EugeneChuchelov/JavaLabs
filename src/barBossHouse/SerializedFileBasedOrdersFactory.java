package barBossHouse;

import io.*;

public class SerializedFileBasedOrdersFactory extends OrdersFactory {
    @Override
    public Order createTableOrder() {
        return new ControlledTableOrder();
    }

    @Override
    public Order createTableOrder(Order order) {
        return new ControlledTableOrder(order);
    }

    @Override
    public Order createTableOrder(int size, Customer customer) {
        return new ControlledTableOrder(size, customer);
    }

    @Override
    public Order createTableOrder(MenuItem[] items, Customer customer) {
        return new ControlledTableOrder(items, customer);
    }

    @Override
    public Order createInternetOrder() {
        return new ControlledInternetOrder();
    }

    @Override
    public Order createInternetOrder(Order order) {
        return new ControlledInternetOrder(order);
    }

    @Override
    public Order createInternetOrder(MenuItem[] items, Customer customer) {
        return new ControlledInternetOrder(items, customer);
    }

    @Override
    public OrdersManager createTableOrderManager(int tablesCount) {
        ControlledTableOrderManager createdManager = new ControlledTableOrderManager(tablesCount);
        OrderManagerSerializedFileSource source = new OrderManagerSerializedFileSource();
        source.setPath(getPath());
        createdManager.setSource(source);
        return createdManager;
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        ControlledInternetOrderManager createdManager = new ControlledInternetOrderManager();
        OrderManagerSerializedFileSource source = new OrderManagerSerializedFileSource();
        source.setPath(getPath());
        createdManager.setSource(source);
        return createdManager;
    }

    @Override
    public OrdersManager createInternetOrderManager(Order[] orders) {
        ControlledInternetOrderManager createdManager = new ControlledInternetOrderManager(orders);
        OrderManagerSerializedFileSource source = new OrderManagerSerializedFileSource();
        source.setPath(getPath());
        createdManager.setSource(source);
        return createdManager;
    }
}
