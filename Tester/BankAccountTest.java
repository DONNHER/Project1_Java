package Tester;

import static org.junit.Assert.assertEquals;

import Bank.Bank;
import Bank.BankLauncher;
import CreditAccount.CreditAccount;
import SavingsAccount.SavingsAccount;
import org.junit.Test;

public class BankAccountTest {
    private final BankLauncher bl;

    public BankAccountTest()
    {
        bl = new BankLauncher();
    }

    @Test
    public void test1() {
        Bank b = new Bank(1001, "Bank of Asia", "0001",100000.0, 50000.0, 50000.0, 15);
        bl.publicAddBank(b);
        Bank b1 = new Bank(1002, "Bank of the Philippines", "0001",100000.0, 50000.0, 50000.0, 15);
        bl.publicAddBank(b1);
        assertEquals(bl.getBanks().get(0),b);
        assertEquals(bl.getBanks().get(1),b1);

    }

    @Test
    public void test2() {
        Bank b3 = new Bank(1003, "Bank of the Philippines","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b1 = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b2 = new Bank(1002, "Land Bank",  "0001",100000.0, 50000.0, 50000.0, 15);
        bl.publicAddBank(b);
        bl.publicAddBank(b1);
        bl.publicAddBank(b2);
        bl.publicAddBank(b3);
        assertEquals(b, bl.getBanks().getFirst());
        assertEquals( b2,bl.getBanks().get(2));
    }

    @Test
    public void test3() {
        Bank b3 = new Bank(1003, "Bank of the Philippines","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b = new Bank(1001, "Bank Commerce","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b2 = new Bank(1002, "Land Bank",  "0001",100000.0, 50000.0, 50000.0, 15);
        Bank b4 = new Bank(10045, "C","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b5= new Bank(10061, "B","0001",100000.0, 50000.0, 50000.0, 15);
        Bank b6 = new Bank(1004, "A",  "0001",100000.0, 50000.0, 50000.0, 15);

        bl.publicAddBank(b);
        bl.publicAddBank(b2);
        bl.publicAddBank(b3);

        bl.publicAddBank(b4);
        bl.publicAddBank(b5);
        bl.publicAddBank(b6);
        assertEquals("A", bl.getBanks().get(0).getName());
        assertEquals("B", bl.getBanks().get(1).getName());
        assertEquals("C", bl.getBanks().get(2).getName());
        assertEquals("Bank Commerce", bl.getBanks().get(3).getName());

        assertEquals("Bank of the Philippines", bl.getBanks().get(4).getName());
        assertEquals("Land Bank", bl.getBanks().get(5).getName());

    }
//    public void test4() {
//        Bank b = new Bank(1, "1", "1",100000.0, 50000.0, 50000.0, 15);
//        bl.publicAddBank(b);
//
//    }


//    @Test
//    public void test4() {
//        Bank b = new Bank(1001, "Land Bank",  "0001",100000.0, 50000.0, 50000.0, 15);
//        bl.publicAddBank(b);
//        bl.setLoggedBank(b);
//        CreditAccount a1 = new CreditAccount (bl.getLoggedBank(),"01", "Juan", "Dela Cruz", "JuanDelaCruz@gmail.com", "0000" );
//        CreditAccount a2 = new CreditAccount (bl.getLoggedBank(),"01", "David", "Teeger", "JuanDelaCruz@gmail.com", "0000" );
//        SavingsAccount b1 = new SavingsAccount (bl.getLoggedBank(),"01", "Jose ", "Rizal", "JuanDelaCruz@gmail.com", "0000",500.0);
//        bl.getLoggedBank().addNewAccount(a1);
//        bl.getLoggedBank().addNewAccount(a1);
//        bl.getLoggedBank().addNewAccount(a1);
//        assertEquals(a2,b.getBankAccounts().getFirst());
//    }
//
//    @Test
//    public void Test6() {
//        Person p1 = new Person("123", "Juan", "Dela Cruz", "Male", "Faculty", "12345", 63, 81);
//        Person p2 = new Person("321", "Maria", "Clara", "Female", "Maiden", "18721", 60, 122);
//        Person p3 = new Person("67667", "Jose", "Rizal", "Male", "Makata", "19911", 84, 12);
//        Person p4 = new Person("11919", "Charlizz", "Betista", "Male", "Programmer", "10091", 84, 195);
//        Person p5 = new Person("86711", "David", "Teeger", "Male", "Teacher", "997751", 63, 100);
//
//        pb.insert(p1);
//        pb.insert(p2);
//        pb.insert(p4);
//        pb.insert(p3);
//        pb.insert(p5);
//
//        String expected = "Juan Dela Cruz is a Faculty. His number is 63-81-12345\n" +
//                "David Teeger is a Teacher. His number is 63-100-997751\n"+
//                "Charlizz Betista is a Programmer. His number is 84-195-10091\n" +
//                "Jose Rizal is a Makata. His number is 84-12-19911";
//
//        assertEquals(expected, pb.printContactsFromCountryCodes(63, 84));
//    }
//
//    @Test
//    public void test7() {
//        Person c1 = new Person("2018-1799", "Juan", "Dela Cruz", "Makata", "M", "63", 12, 677865);
//        Person c2 = new Person("1950-1900", "Maria", "Clara", "Binibini", "F", "63" ,10, 189006);
//        Person c3 = new Person("1968-1940", "Joseph", "Joestar", "Stand User", "M", "84", 20, 174265);
//
//        pb.insert(c1);
//        pb.insert(c2);
//        pb.insert(c3);
//
//        pb.deleteContact("2018-1799");
//
//        assertEquals(2, pb.getSize());
//        assertEquals("Joseph Joestar", pb.getContactAtIndex(1).getFullName());
//    }
//
//    @Test
//    public void test8() {
//        Person c1 = new Person("2018", "Jose", "Rizal", "Hero", "M", "12345", 63, 22922);
//        Person c2 = new Person("1999", "Joaquin", "Jacinto", "Person", "M", "18721", 98, 67251);
//        Person c3 = new Person("1950", "Yin", "Xie", "Gamer", "M", "19911", 45, 66771);
//        Person c4 = new Person("1980", "Maria", "Clara", "Binibini", "F", "10091", 63, 12991);
//        Person c5 = new Person("1770-", "Ahmed", "Rizal", "Poser", "M", "997751", 67, 17651);
//
//        pb.insert(c1);
//        pb.insert(c2);
//        pb.insert(c4);
//        pb.insert(c3);
//        pb.insert(c5);
//
//        pb.deleteContact("1950");
//        pb.deleteContact("1980");
//        assertEquals(3, pb.getSize());
//        assertEquals("Jose Rizal", pb.getContactAtIndex(pb.getSize() - 1).getFullName());
//        assertEquals("Joaquin Jacinto", pb.getContactAtIndex(0).getFullName());
//    }


}
