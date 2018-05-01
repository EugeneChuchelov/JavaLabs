import barBossHouse.*;
import io.ControlledInternetOrderManager;
import io.OrderManagerTextFileSource;
import io.Source;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class InternetOrderTest {
    public static void main(String[] args){
        Drink drink1 = new Drink("fgt", DrinkTypeEnum.COFEE);
        Drink drink2 = new Drink("Lahe", DrinkTypeEnum.WINE, "hugidsah", 420);
        Drink drink3 = new Drink("Booh", DrinkTypeEnum.VODKA, "bnv", 126.5, 40);
        Dish dish1 = new Dish("Bul", "");
        Dish dish2 = new Dish("Khark", "est", 99.99);
        Dish dish3 = new Dish("Wassa", "Ameno", 666);
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1993, Month.MAY, 12), adr2);
        Address adr3 = new Address("Rainbow street", 255, 'b', 402, "Belogvardeysk", 463174);
        Customer cust3 = new Customer("Egor", "Zimnov", LocalDate.of(2007, Month.OCTOBER, 24), adr3);

        //System.out.println(cust3.getAge());
        InternetOrder ord1 = new InternetOrder();
        MenuItem[] o = {dish1, drink3, dish2, drink1, drink3, drink3 ,dish1 ,dish1};
        InternetOrder ord3 = new InternetOrder(o,cust2);
        System.out.println("List things test");
        for(MenuItem item : ord3){
            System.out.println(item.getName());
        }
        List<MenuItem> lst = new ArrayList<>();
        //lst.add(dish3);
        //lst.add(drink2);
        //ord3.remove(4);
        //ord3.addAll(3,lst);
        System.out.println("\n"+"\n");
        lst = ord3.subList(2,5);
        /*for(MenuItem item : ord3){
            System.out.println(item.getName());
        }*/
        for(MenuItem item : lst){
            System.out.println(item.getName());
        }
        System.out.println("\nEnd of list things test");
        try {
            PrintWriter writer = new PrintWriter("F:\\Documents\\GitHub\\JavaLabs\\src\\" + ord3.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".txt");
            writer.println(ord3.getType());
            writer.println(ord3.getCustomer().toString());
            writer.println(ord3.getCustomer().getAddress().toString());
            writer.println(ord3.size());
            for(MenuItem menuItem : ord3){
                writer.println(menuItem.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ord1.add(drink3);
        Source<Order> src = new OrderManagerTextFileSource();
        ((OrderManagerTextFileSource) src).setPath("F:\\Documents\\GitHub\\JavaLabs\\tests\\");
        ord1.setCustomer(cust3);
        ControlledInternetOrderManager ciom = new ControlledInternetOrderManager();
        ciom.setSource(src);
        ciom.add(ord1);
        ciom.add(ord3);
        /*ord1.add(dish2);
        ord1.add(dish2);
        ord1.add(dish1);
        ord1.add(drink2);
        System.out.println(ord1.toString());
        ord1.remove("Lahe");
        ord1.remove(dish1);
        System.out.println(ord1.toString());
        System.out.println(ord1.removeAll(dish2));
        System.out.println(ord1.toString());
        System.out.println(ord3.toString());
        System.out.println("ord3.itemsQuantity(): " + ord3.itemsQuantity());
        System.out.println("ord3.itemsQuantity(Bul): " + ord3.itemsQuantity("Bul"));
        String[] names = ord3.itemsNames();
        System.out.println();
        for(String n : names) System.out.println(n);
        System.out.println();
        MenuItem[] items = ord3.sortedItemsByCostDesc();
        for(MenuItem i : items){
            System.out.print(i.getCost() + "\t| ");
            System.out.print(i.getName() + "\t| ");
            System.out.println(i.getDescription());
        }
        System.out.println();
        ord1.setCustomer(cust3);
        ord1.add(drink1);
        ord1.add(drink1);
        ord1.add(dish1);
        ord1.add(dish2);
        ord1.add(drink3);
        ord1.add(drink3);
        ord1.add(drink3);
        ord1.add(drink3);
        ord1.setCustomer(cust2);
        InternetOrder ord2 = new InternetOrder();
        ord2.add(drink3);
        ord2.add(drink3);
        ord2.add(drink3);
        ord2.add(drink3);
        ord2.add(dish1);
        ord2.add(dish2);
        ord2.add(drink1);
        ord2.add(drink1);
        ord2.setCustomer(cust2);

        System.out.println("ord1.equals(ord1): " + ord1.equals(ord1));
        System.out.println("ord1.equals(ord2): " + ord1.equals(ord2));
        System.out.println("ord1.equals(ord3): " + ord1.equals(ord3));*/
    }
}
