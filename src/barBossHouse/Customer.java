package barBossHouse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public final class Customer {
    private final String firstName;
    private final String secondName;
    private final LocalDate birthDate;
    private final Address address;
    private static final String NAME_DEFAULT = "";
    private static final LocalDate BIRTH_DATE_DEFAULT = LocalDate.of(0, Month.JANUARY, 1);
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.now().minusYears(21));
    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(LocalDate.now().minusYears(14));

    public Customer() {
        this(NAME_DEFAULT, NAME_DEFAULT, BIRTH_DATE_DEFAULT, Address.EMPTY_ADDRESS);
    }

    public Customer(LocalDate birthDate) {
        this(NAME_DEFAULT, NAME_DEFAULT, birthDate, Address.EMPTY_ADDRESS);
    }

    public Customer(String firstName, String secondName, LocalDate birthDate, Address address) {
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Time travelers not allowed");
        }
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String toString() {
        StringBuilder output = new StringBuilder("Customer: ");
        if (!secondName.equals(NAME_DEFAULT)) output.append(secondName).append(" ");
        if (!firstName.equals(NAME_DEFAULT)) output.append(firstName).append(", ");
        if (birthDate != BIRTH_DATE_DEFAULT) output.append(getAge()).append(", ");
        if (!address.equals(Address.EMPTY_ADDRESS)) output.append(address.toString());
        return output.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Customer) {
            return ((Customer) obj).firstName.equals(firstName) && ((Customer) obj).secondName.equals(secondName) &&
                    ((Customer) obj).birthDate.equals(birthDate) && ((Customer) obj).address.equals(address);
        }
        return false;
    }

    public int hashCode() {
        int hash = firstName.hashCode();
        hash ^= secondName.hashCode();
        hash ^= birthDate.hashCode();
        hash ^= address.hashCode();
        hash ^= birthDate.hashCode();
        return hash;
    }
}
