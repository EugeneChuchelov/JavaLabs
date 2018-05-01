import barBossHouse.*;
import io.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.Scanner;

public class TableOrderManagerTest {
    public static void main(String[] args) throws AlreadyAddedException, IOException {
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
        String file = newOrder.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() + ".bin";
        for(Order order : tom){
            System.out.println(order.toString());
        }
        /*try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("F:\\Documents\\GitHub\\JavaLabs\\tests\\" + file));
            out.writeObject(newOrder);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Order fdl;
        try {
            ObjectInputStream in = new ObjectInputStream(new
                    FileInputStream("F:\\Documents\\GitHub\\JavaLabs\\tests\\" + file));
            fdl = (Order) in.readObject();
            in.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            System.out.println("Wrong object type");
        }*/

        Source<Order> src = new OrderManagerSerializedFileSource();
        //Source<Order> src = new OrderManagerBinaryFileSource();
        ((OrderManagerSerializedFileSource) src).setPath("F:\\Documents\\GitHub\\JavaLabs\\tests\\");
        ControlledTableOrderManager ctom = new ControlledTableOrderManager(4);
        ctom.setSource(src);
        ctom.add(0,newOrder);
        ctom.add(2,antOrder);
        ctom.add(1,intOrder);
        ctom.addItem(dish2,0);
        ctom.addItem(drink,0);
        ctom.remove(2);
        ctom.remove(intOrder);
        /*create(newOrder);
        create(antOrder);
        create(intOrder);
        newOrder.add(dish2);
        newOrder.add(dish);
        create(newOrder);*/
    }
}