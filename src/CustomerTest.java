import barBossHouse.Address;
import barBossHouse.Customer;
import java.time.LocalDate;
import java.time.Month;

public class CustomerTest {
    public static void main(String[] args){
        Address adr2 = new Address("Emptyness avenue", 127, 'a', 616, "Archangelsk", 646571);
        Customer emptyCust = new Customer();
        Customer cust1 = new Customer(LocalDate.of(2015, Month.MAY, 12));
        Customer cust2 = new Customer("Igor", "Ivanov", LocalDate.of(1911, Month.DECEMBER, 3), adr2);

        System.out.println(emptyCust.toString());
        System.out.println(cust1.toString());
        System.out.println(cust2.toString());
        System.out.println(cust1.equals(cust2));
        System.out.println(cust1.equals(cust1));
        System.out.println(cust2.getAge());

    }
}
