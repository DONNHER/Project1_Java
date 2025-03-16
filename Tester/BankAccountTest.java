package Tester;

import static org.junit.Assert.assertEquals;

import Bank.Bank;
import Bank.BankLauncher;
import org.junit.Test;

public class BankAccountTest {
    private final BankLauncher bl;

    public BankAccountTest()
    {
        bl = new BankLauncher();
    }

    @Test
    public void test1() {
        Bank b = new Bank(1001, "Bank of Asia", "0001",100000.0, 50000.0, 50000.0, 15.0);
        bl.publicAddBank(b);
        Bank b1 = new Bank(1002, "Bank of the Philippines", "0001",100000.0, 50000.0, 50000.0, 15.0);
        bl.publicAddBank(b1);
        assertEquals(bl.getBanks().get(0),b);
        assertEquals(bl.getBanks().get(1),b1);

    }

    @Test
    public void test2() {
        Bank b3 = new Bank(1003, "Bank of the Philippines","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b1 = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b2 = new Bank(1002, "Land Bank",  "0001",100000.0, 50000.0, 50000.0, 15.0);
        bl.publicAddBank(b);
        bl.publicAddBank(b1);
        bl.publicAddBank(b2);
        bl.publicAddBank(b3);
        assertEquals(b, bl.getBanks().getFirst());
        assertEquals( b2,bl.getBanks().get(2));
    }

    @Test
    public void test3() {
        Bank b3 = new Bank(1003, "Bank of the Philippines","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b2 = new Bank(1002, "Land Bank",  "0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b4 = new Bank(10045, "C","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b5= new Bank(10061, "B","0001",100000.0, 50000.0, 50000.0, 15.0);
        Bank b6 = new Bank(1004, "A",  "0001",100000.0, 50000.0, 50000.0, 15.0);

        bl.publicAddBank(b);
        bl.publicAddBank(b2);
        bl.publicAddBank(b3);

        bl.publicAddBank(b4);
        bl.publicAddBank(b5);
        bl.publicAddBank(b6);
        assertEquals("A", bl.getBanks().get(0).getName());
        assertEquals("B", bl.getBanks().get(1).getName());
        assertEquals("Bank Commerce", bl.getBanks().get(2).getName());
        assertEquals("Bank of the Philippines", bl.getBanks().get(3).getName());

        assertEquals("C", bl.getBanks().get(4).getName());
        assertEquals("Land Bank", bl.getBanks().get(5).getName());

    }

}
