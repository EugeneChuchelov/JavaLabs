import barBossHouse.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TableOrderTest {
    public static void main(String[] args){
        Drink drink1 = new Drink("fgt", DrinkTypeEnum.COFEE);
        Drink drink2 = new Drink("Lahe", DrinkTypeEnum.WINE, "hugidsah", 420);//Oh, widzę cię z Polski
        Drink drink3 = new Drink("Booh", DrinkTypeEnum.VODKA, "bnv", 126.5, 40);
        Dish dish1 = new Dish("Bul", "");
        Dish dish2 = new Dish("Khark", "est", 99.99);
        Dish dish3 = new Dish("Wassa", "Ameno", 666);
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1453, Month.MAY, 12), adr2);

        TableOrder ord1 = new TableOrder();
        TableOrder ord2 = new TableOrder(12, cust2);
        MenuItem[] o = {dish1, drink3, dish2, drink1, drink3, drink3 ,dish1 ,dish1};
        TableOrder ord3 = new TableOrder(o,cust2);
        ord2.add(dish2);
        ord2.add(dish2);
        ord2.add(dish1);
        ord2.add(drink2);
        System.out.println("List things test");
        for(MenuItem item : ord3){
            System.out.println(item.getName());
        }
        List<MenuItem> lst = new ArrayList<>();
        lst = ord3.subList(2,5);
        //lst.add(dish3);
        //lst.add(drink2);
        //lst.add(dish1);
        //ord3.addAll(3,lst);
        //ord3.retainAll(lst);
        //ord3.remove(4);
        System.out.println("");
        /*for(MenuItem item : ord3){
            System.out.println(item.getName());
        }*/
        for(MenuItem item : lst){
            System.out.println(item.getName());
        }
        System.out.println("\nEnd of list things test");
        /*System.out.println(ord2.toString());
        ord2.remove("Lahe");
        System.out.println(ord2.toString());
        System.out.println(ord2.removeAll(dish2));
        System.out.println(ord2.toString());
        System.out.println(ord3.toString());
        System.out.println("ord3.itemsQuantity(): " + ord3.itemsQuantity());
        System.out.println("ord3.itemsQuantity(Bul): " + ord3.itemsQuantity("Bul"));
        String[] names = ord3.itemsNames();
        System.out.println();
        for(String n : names) System.out.println(n);
        System.out.println();
        MenuItem[] items = ord3.sortedItemsByCostDesc();
        for(MenuItem i : items){
            System.out.print(i.getCost() + " | ");
            System.out.print(i.getName() + " | ");
            System.out.println(i.getDescription());
        }
        System.out.println();
        ord2.add(drink1);
        ord1.add(drink1);
        ord1.add(dish1);
        ord1.setCustomer(cust2);
        System.out.println("ord1.equals(ord2): " + ord1.equals(ord2));
        System.out.println("ord1.equals(ord1): " + ord1.equals(ord1));
        System.out.println("ord1.equals(ord3): " + ord1.equals(ord3));
        System.out.println("ord1.hashCode(): " + ord1.hashCode());*/
    }
}
