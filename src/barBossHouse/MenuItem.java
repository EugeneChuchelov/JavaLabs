package barBossHouse;

public abstract class MenuItem implements Comparable<MenuItem> {
    private double cost;
    private String name;
    private String description;
    public static final int COST_DEFAULT = 0;
    public static final String NAME_EMPTY = "";

    protected MenuItem(){}

    protected MenuItem(String name, String description) {
        this(name, description, COST_DEFAULT);
    }

    protected MenuItem(String name, String description, double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost must be > 0");
        }
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public static MenuItem parseMenuItem(String string){
        String[] fields = string.split(";");
        MenuItem item;
        if(fields.length == 3){
            item = new Dish(fields[0], fields[2], Double.parseDouble(fields[1]));
        } else {
            item = new Drink(fields[0], DrinkTypeEnum.valueOf(fields[3]), fields[2], Double.parseDouble(fields[1]), Double.parseDouble(fields[4]));
        }
        return item;
    }

    public String toString() {
        String output;
        output = String.format("%s, %.2f p.", name != null ? name : "", cost);
        return output;
    }

    public int compareTo(MenuItem o) {
        if (o.cost > cost) {
            return -1;
        } else if (o.cost < cost) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MenuItem) {
            return ((MenuItem) obj).name.equals(name) && ((MenuItem) obj).cost == cost;
        }
        return false;
    }

    public int hashCode() {
        int hash = (int) cost;
        hash ^= name.hashCode();
        hash ^= description.hashCode();
        return hash;
    }
}
