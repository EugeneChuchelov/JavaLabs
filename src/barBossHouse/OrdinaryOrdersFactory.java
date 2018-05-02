package barBossHouse;

public class OrdinaryOrdersFactory extends OrdersFactory {
    @Override
    public Order createTableOrder() {
        return new TableOrder();
    }

    @Override
    public Order createTableOrder(Order order) {
        return new TableOrder(order);
    }

    @Override
    public Order createTableOrder(int size, Customer customer) {
        return new TableOrder(size, customer);
    }

    @Override
    public Order createTableOrder(MenuItem[] items, Customer customer) {
        return new TableOrder(items, customer);
    }

    @Override
    public Order createInternetOrder() {
        return new InternetOrder();
    }

    @Override
    public Order createInternetOrder(Order order) {
        return new InternetOrder(order);
    }

    @Override
    public Order createInternetOrder(MenuItem[] items, Customer customer) {
        return new InternetOrder(items, customer);
    }

    @Override
    public OrdersManager createTableOrderManager(int tablesCount) {
        return new TableOrderManager(tablesCount);
    }

    @Override
    public OrdersManager createInternetOrderManager() {
        return new InternetOrdersManager();
    }

    @Override
    public OrdersManager createInternetOrderManager(Order[] orders) {
        return new InternetOrdersManager(orders);
    }
}
