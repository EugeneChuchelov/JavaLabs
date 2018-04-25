import barBossHouse.Address;

public class AddressTest {
    public static void main(String[] args){
        Address emptyAdr = new Address();
        Address adr1 = new Address("Whiler st.", 22, 's', 124);
        Address adr2 = new Address("Emptyness avenue", 11, 'a', 616, "Archangelsk", 646571);

        System.out.println(emptyAdr.toString());
        System.out.println(adr1.toString());
        System.out.println(adr2.toString());
        System.out.println(adr1.equals(adr2));
        System.out.println(adr2.equals(adr2));
        System.out.println(adr1.equals(null));
    }
}
