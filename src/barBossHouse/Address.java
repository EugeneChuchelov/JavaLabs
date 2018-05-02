package barBossHouse;

public final class Address implements java.io.Serializable {
    private final String cityName;
    private final int zipCode;
    private final String streetName;
    private final int buildingNumber;
    private final char buildingLetter;
    private final int appartmentNumber;
    public static final Address EMPTY_ADDRESS = new Address();
    private static final String NAME_EMPTY = "";
    private static final char LETTER_DEFAULT = 'a';
    private static final int NUMBER_EMPTY = 0;
    private static final String CITY_DEFAULT = "Samara";

    public Address() {
        this(NAME_EMPTY, NUMBER_EMPTY, LETTER_DEFAULT, NUMBER_EMPTY, NAME_EMPTY, NUMBER_EMPTY);
    }

    public Address(String streetName, int buildingNumber, char buildingLetter, int apartmentNumber) {
        this(streetName, buildingNumber, buildingLetter, apartmentNumber, CITY_DEFAULT, NUMBER_EMPTY);
    }

    public Address(String streetName, int buildingNumber, char buildingLetter, int apartmentNumber, String cityName, int zipCode) {
        if (buildingNumber < 0) {
            throw new IllegalArgumentException("Building number must be > 0");
        }
        if (!Character.isLetter(buildingLetter)) {
            throw new IllegalArgumentException("Building letter must be a letter");
        }
        if (apartmentNumber < 0) {
            throw new IllegalArgumentException("Apartment number must be > 0");
        }
        if (zipCode < 0) {
            throw new IllegalArgumentException("Zip code must be > 0");
        }

        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.appartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.cityName = cityName;
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return appartmentNumber;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Address: ");
        if (!cityName.equals(NAME_EMPTY)) output.append(cityName).append(" ");
        if (zipCode != NUMBER_EMPTY) output.append(zipCode).append(", ");
        if (!streetName.equals(NAME_EMPTY)) output.append(streetName).append(" ");
        if (buildingNumber != NUMBER_EMPTY) output.append(buildingNumber);
        if (buildingLetter != LETTER_DEFAULT) output.append(buildingLetter);
        output.append("-");
        if (appartmentNumber != NUMBER_EMPTY) output.append(appartmentNumber);
        return output.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Address) {
            return ((Address) obj).cityName.equals(cityName) && ((Address) obj).zipCode == zipCode && ((Address) obj).streetName.equals(streetName) &&
                    ((Address) obj).buildingNumber == buildingNumber && ((Address) obj).buildingLetter == buildingLetter &&
                    ((Address) obj).appartmentNumber == appartmentNumber;
        }
        return false;
    }

    public int hashCode() {
        int hash = cityName.hashCode();
        hash ^= zipCode;
        hash ^= streetName.hashCode();
        hash ^= buildingNumber;
        hash ^= buildingLetter;
        hash ^= appartmentNumber;
        return hash;
    }
}