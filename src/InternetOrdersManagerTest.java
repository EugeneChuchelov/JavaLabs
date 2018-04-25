import barBossHouse.*;

import java.util.ArrayList;
import java.util.List;

public class InternetOrdersManagerTest {
    public static void main(String[] args) throws AlreadyAddedException{
        Dish dish = new Dish("Sacra", "test", 35);
        Dish dish2 = new Dish("Glara", "Dwsf", 99.99);
        Drink drink = new Drink("Klig", DrinkTypeEnum.WHISKEY, "noIce", 520, 15);
        Drink drink2 = new Drink("Tea", DrinkTypeEnum.GREEN_TEA, "qwesd", 12.5);
        MenuItem[] d = {dish, drink, drink2, dish2};
        Customer c = new Customer();
        InternetOrder ord = new InternetOrder(d,c);
        InternetOrder ord2 = new InternetOrder(d,c);
        TableOrder ord3 = new TableOrder(d,c);
        ord.add(dish);
        ord2.add(drink2);
        ord2.itemsQuantity(dish);
        Order[] jj = {ord,ord2};
        InternetOrdersManager iom = new InternetOrdersManager(jj);
        List<Order> lst = new ArrayList<>();
        lst.add(ord3);
        Order[] asd = new Order[2];
        iom.toArray(asd);
        iom.addLast(ord3);
     //   iom.removeFirstOccurrence(ord3);
        iom.removeAll(lst);
        for(Order order: asd){
            System.out.println(order.toString());
        }
        /*for(Order order: iom){
            System.out.println(order.toString());
        }
        System.out.println("~~--*Doing some things*--~~");
        iom.addLast(ord3);
        iom.removeFirstOccurrence(ord2);
        for(Order order: iom){
            System.out.println(order.toString());
        }*/

        //iom.add(ord);
        //Order[] ff = {ord2,ord,ord};
        //InternetOrdersManager aiom = new InternetOrdersManager(ff);
        //iom.add(ord);
        //System.out.println("iom.equals(aiom): " + iom.equals(aiom));
    }
}
