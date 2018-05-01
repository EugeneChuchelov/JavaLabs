package barBossHouse;

public class Dish extends MenuItem implements java.io.Serializable {
    public Dish(String name, String description) {
        super(name, description);
    }

    public Dish(String name, String description, double cost) {
        super(name, description, cost);
    }

    public String toString() {
        String output;
        output = String.format("Dish: %s %s", super.toString(), getDescription() != null ? getDescription() : "");
        return output;
    }
}
