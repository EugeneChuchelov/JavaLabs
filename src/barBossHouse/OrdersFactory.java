package barBossHouse;

public abstract class OrdersFactory {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public abstract Order createTableOrder();
    public abstract Order createTableOrder(Order order);
    public abstract Order createTableOrder(int size, Customer customer);
    public abstract Order createTableOrder(MenuItem[] items, Customer customer);
    public abstract Order createInternetOrder();
    public abstract Order createInternetOrder(Order order);
    public abstract Order createInternetOrder(MenuItem[] items, Customer customer);
    public abstract OrdersManager createTableOrderManager(int tablesCount);
    public abstract OrdersManager createInternetOrderManager();
    public abstract OrdersManager createInternetOrderManager(Order[] orders);
    static public OrdersFactory getOrdersFactory(OrdersFactoryTypesEnumeration type){
        switch (type){
            case ORDINARY_ORDERS_FACTORY:
                return new OrdinaryOrdersFactory();
            case TEXT_FILE_BASED_ORDERS_FACTORY:
                return new TextFileBasedOrdersFactory();
            case BINARY_FILE_BASED_ORDERS_FACTORY:
                return new BinaryFileBasedOrdersFactory();
            case SERIALIZED_FILE_BASED_ORDERS_FACTORY:
                return new SerializedFileBasedOrdersFactory();
            case SOCKET_BASED_ORDERS_FACTORY:
                //Про сокеты в задании ничего нет
                return null;
            default: return null;
        }
    }
}
