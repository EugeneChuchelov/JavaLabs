import barBossHouse.Drink;
import barBossHouse.DrinkTypeEnum;

public class DrinkTest {
    public static void main(String[] args){
        Drink drink1 = new Drink("fgt", DrinkTypeEnum.COFEE);
        Drink drink2 = new Drink("Lahe", DrinkTypeEnum.WINE, "hugidsah", 420);
        Drink drink3 = new Drink("Booh", DrinkTypeEnum.VODKA, "bnv", 126.5, 40);

        System.out.println(drink3.isAlcoholicDrink());
        System.out.println(drink1.toString());
        System.out.println(drink2.toString());
        System.out.println(drink3.toString());
        System.out.println(drink1.equals(drink3));
        System.out.println(drink1.equals(drink1));
        System.out.println(drink3.equals(DrinkTypeEnum.VODKA));

    }
}
