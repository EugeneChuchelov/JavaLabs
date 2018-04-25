package barBossHouse;

public abstract class MenuItem implements Comparable<MenuItem> {
    private double cost;
    private String name;
    private String description;
    public static final int COST_DEFAULT = 0;
    public static final String NAME_EMPTY = "";

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
