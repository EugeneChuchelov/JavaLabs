import barBossHouse.*;
import io.*;

import java.time.LocalDate;
import java.time.Month;

public class Test {
    public static void main(String[] args){
        Drink drink1 = new Drink("fgt", DrinkTypeEnum.COFEE);
        Drink drink2 = new Drink("Lahe", DrinkTypeEnum.WINE, "hugidsah", 420);//Oh, widzę cię z Polski
        Drink drink3 = new Drink("Booh", DrinkTypeEnum.VODKA, "bnv", 126.5, 40);
        Dish dish1 = new Dish("Bul", "");
        Dish dish2 = new Dish("Khark", "est", 99.99);
        Dish dish3 = new Dish("Wassa", "Ameno", 666);
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1453, Month.MAY, 12), adr2);
        Address adr3 = new Address("Rainbow street", 255, 'b', 402, "Belogvardeysk", 463174);
        Customer cust3 = new Customer("Egor", "Zimnov", LocalDate.of(2007, Month.OCTOBER, 24), adr3);

        OrdersFactory factory = OrdersFactory.getOrdersFactory(OrdersFactoryTypesEnumeration.TEXT_FILE_BASED_ORDERS_FACTORY);
        factory.setPath("F:\\Documents\\GitHub\\JavaLabs\\tests\\");
        ControlledTableOrderManager manager = (ControlledTableOrderManager) factory.createTableOrderManager(4);
        ControlledTableOrder ord1 = (ControlledTableOrder) factory.createTableOrder(3, cust2);
        ord1.add(drink3);
        ord1.add(drink3);
        ord1.add(dish2);
        ControlledTableOrder ord2 = (ControlledTableOrder) factory.createTableOrder(2, cust2);
        ord2.add(dish2);
        ord2.add(dish3);

        manager.add(ord1);
        manager.add(ord2);

        manager.load();
    }
}
