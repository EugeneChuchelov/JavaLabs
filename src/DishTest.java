import barBossHouse.Dish;

public class DishTest {
    public static void main(String[] args){
        Dish dish1 = new Dish("Bul", "");
        Dish dish2 = new Dish("Khark", "est", 799.99);

        System.out.println(dish1.toString());
        System.out.println(dish2.toString());
        System.out.println(dish1.equals(dish2));
        System.out.println(dish1.equals(dish1));
    }
}
