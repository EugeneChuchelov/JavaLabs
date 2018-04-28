import barBossHouse.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import io.ControlledTableOrderManager;

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
        intOrder.setCustomer(cust2);
        intOrder.add(drink);
        intOrder.add(dish2);
        intOrder.setCustomer(cust2);
        TableOrderManager tom = new TableOrderManager(4);

        tom.add(0,newOrder);
        tom.add(2,antOrder);
        tom.add(1,intOrder);
        tom.add(3,newOrder);
        tom.addItem(drink, 2);
        for(Order order : tom){
            System.out.println(order.toString());
        }

        ControlledTableOrderManager ctom  = new ControlledTableOrderManager(4);
        ctom.add(0,newOrder);
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
