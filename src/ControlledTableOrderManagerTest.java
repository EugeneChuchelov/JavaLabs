import barBossHouse.*;
import io.*;

import java.time.LocalDate;
import java.time.Month;

public class ControlledTableOrderManagerTest {
    public static void main(String[] args){
        Dish dish = new Dish("Sacra", "test", 35);
        Dish dish2 = new Dish("Glara", "Dwsf", 99.99);
        Drink drink = new Drink("Klig", DrinkTypeEnum.WHISKEY, "noIce", 520, 15);
        Drink drink2 = new Drink("Tea", DrinkTypeEnum.GREEN_TEA, "qwesd", 12.5);
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1993, Month.MAY, 12), adr2);

        ControlledTableOrderManager fags = new ControlledTableOrderManager(5);

        ControlledTableOrder ord0 = new ControlledTableOrder();
        {
            ord0.add(dish);
            ord0.add(drink);
        }
        ControlledTableOrder ord1 = new ControlledTableOrder();
        {
            ord1.add(dish2);
            ord1.add(drink2);
        }


        fags.add(ord0);
        fags.add(ord1);
        fags.addItem(drink, 0);
    }
}
