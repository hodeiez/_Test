
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Created by Hodei Eceiza
 * Date: 5/1/2021
 * Time: 20:34
 * Project: redmind_atm
 * Copyright: MIT
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AtmOperatorTest {

 LinkedHashMap<Integer, Integer>  availableBills;
    @BeforeAll
    public void reset() {

        availableBills=new LinkedHashMap<>();
        availableBills.put(1000, 2);
        availableBills.put(500, 3);
        availableBills.put(100, 5);
    }

    @Test
    public void removeBills (){
      Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,0);put(500,2);put(100,1);}},AtmOperator.removeBills(2900,availableBills));
      reset();
      Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,2);put(500,2);put(100,1);}},AtmOperator.removeBills(900,availableBills));
    }
    @Test
    public void returnSmallestAmount(){
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(500,1);}},AtmOperator.returnSmallestAmount(500,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(500,1);put(100,3);}},AtmOperator.returnSmallestAmount(800,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,4);put(500,1);}},AtmOperator.returnSmallestAmount(4500,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,1);put(500,1);put(100,4);}},AtmOperator.returnSmallestAmount(1900,availableBills));

    }
    @Test
    public void listOfBillValues(){
        Assertions.assertEquals(new ArrayList<Integer>(){{add(1000);add(500);add(100);}},AtmOperator.billValues(availableBills));
        Assertions.assertNotEquals(new ArrayList<Integer>(){{add(1000);add(100);}},AtmOperator.billValues(availableBills));
    }
    @Test
    public void atmHasCash(){
        Assertions.assertTrue(AtmOperator.hasCash(4000,availableBills));
        Assertions.assertTrue(AtmOperator.hasCash(3000,availableBills));
        Assertions.assertTrue(AtmOperator.hasCash(2000,availableBills));
        Assertions.assertFalse(AtmOperator.hasCash(5000,availableBills));
    }
    @Test
    public void billsToCash(){
reset();
        Assertions.assertEquals(4000,AtmOperator.billsToCash(availableBills));
        Assertions.assertNotEquals(3000,AtmOperator.billsToCash(availableBills));
    }
    @Test
    public void hasBills(){
       LinkedHashMap<Integer, Integer>  billsInAtm;

       billsInAtm=new LinkedHashMap<>();
        billsInAtm.put(1000, 1);
        billsInAtm.put(500, 1);
        billsInAtm.put(100, 3);

        Assertions.assertFalse(AtmOperator.hasBills(400,billsInAtm));


        billsInAtm=new LinkedHashMap<>();
        billsInAtm.put(1000, 0);
        billsInAtm.put(500, 0);
        billsInAtm.put(100, 4);
        Assertions.assertTrue(AtmOperator.hasBills(400,billsInAtm));
    }

    @Test
    public void operate() throws Exception {

        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,1);put(500,2);put(100,5);}},AtmOperator.operate(1500,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,1);put(500,1);put(100,3);}},AtmOperator.operate(700,availableBills));
        Assertions.assertThrows(Exception.class,()-> AtmOperator.operate(400,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,0);put(500,1);put(100,2);}},AtmOperator.operate(1100,availableBills));
        Assertions.assertThrows(Exception.class,()-> AtmOperator.operate(1000,availableBills));
        Assertions.assertEquals(new LinkedHashMap<Integer,Integer>(){{put(1000,0);put(500,0);put(100,0);}},AtmOperator.operate(700,availableBills));
        Assertions.assertThrows(Exception.class,()-> AtmOperator.operate(300,availableBills));


    }


}
