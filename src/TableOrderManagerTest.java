import barBossHouse.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.LocalDate;
import java.time.Month;

public class TableOrderManagerTest {
    public static void main(String[] args) throws AlreadyAddedException{
        Dish dish = new Dish("Sacra", "test", 35);
        Dish dish2 = new Dish("Glara", "Dwsf", 99.99);
        Drink drink = new Drink("Klig", DrinkTypeEnum.WHISKEY, "noIce", 520, 15);
        Drink drink2 = new Drink("Tea", DrinkTypeEnum.GREEN_TEA, "qwesd", 12.5);
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1993, Month.MAY, 12), adr2);

        TableOrder newOrder = new TableOrder();
        newOrder.add(dish);
        newOrder.add(drink);
        newOrder.add(dish2);
        newOrder.add(drink2);
        newOrder.add(dish2);
        newOrder.setCustomer(cust2);
        TableOrder antOrder = new TableOrder();
        antOrder.add(dish);
        antOrder.add(drink2);
        antOrder.add(dish2);
        antOrder.add(drink);
        InternetOrder intOrder = new InternetOrder();
        intOrder.add(drink);
        intOrder.add(dish2);
        intOrder.setCustomer(cust2);
        TableOrderManager tom = new TableOrderManager(4);

        tom.add(newOrder,0);
        tom.add(antOrder,2);
        tom.add(intOrder,1);
        tom.add(newOrder,3);
        tom.addItem(drink, 2);
        for(Order order : tom){
            System.out.println(order.toString());
        }
        //System.out.println(tom.removeAll(newOrder));
        //System.out.print("occupiedTableNumbers(): ");
        //for(int g : tom.occupiedTableNumbers()) System.out.print(g + " ");
        //System.out.println();
        //for (Order ord : tom.getOrders()) System.out.println(ord.toString());
        //System.out.println(tom.toString());
        /*Order[] das = tom.getCustomerOrders(cust2);
        for(Order order : das){
            System.out.println(order.toString());
        }*/
    }
}
