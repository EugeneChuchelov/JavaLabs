package barBossHouse;

public class Drink extends MenuItem implements Alcoholable {
    private double alcoholVol;
    private DrinkTypeEnum type;
    public static final double ALCOHOL_DEFAULT = 0;

    public Drink(String name, DrinkTypeEnum type) {
        this(name, type, MenuItem.NAME_EMPTY, MenuItem.COST_DEFAULT, ALCOHOL_DEFAULT);
    }

    public Drink(String name, DrinkTypeEnum type, String description, double cost) {
        this(name, type, description, cost, ALCOHOL_DEFAULT);
    }

    public Drink(String name, DrinkTypeEnum type, String description, double cost, double alcoholVol) {
        super(name, description, cost);
        if (alcoholVol < 0 || alcoholVol > 100) {
            throw new IllegalArgumentException("Alcohol volume must be between 0 and 100");
        }

        this.alcoholVol = alcoholVol;
        this.type = type;
    }

    public boolean isAlcoholicDrink() {
        return alcoholVol > 0;
    }

    public double getAlcoholVol() {
        return alcoholVol;
    }

    public DrinkTypeEnum getType() {
        return type;
    }

    public String toString() {
        String output;
        output = String.format("Drink: %s %s %s%s", type != null ? type : "", super.toString(), isAlcoholicDrink() ? "Alcohol: " + alcoholVol + "%. " : "",
                getDescription() != null ? getDescription() : "");
        return output;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Drink) {
            return super.equals(obj) && ((Drink) obj).type.equals(type) && ((Drink) obj).alcoholVol == alcoholVol;
        }
        return false;
    }

    public int hashCode() {
        int hash = super.hashCode();
        hash ^= type.hashCode();
        hash ^= (int) alcoholVol;
        return hash;
    }
}
