import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 5/1/2021
 * Time: 16:08
 * Project: redmind_atm
 * Copyright: MIT
 */

/**
 * Represantes the ATM as an object. Has only availableBills as field
 */
public class Atm {
    private LinkedHashMap<Integer, Integer> availableBills;


    public Atm(LinkedHashMap<Integer, Integer> availableBills) {
        this.availableBills = availableBills;
    }

    public LinkedHashMap<Integer, Integer> getAvailableBills() {
        return availableBills;
    }

    public void setAvailableBills(LinkedHashMap<Integer, Integer> availableBills) {
        this.availableBills = availableBills;
    }

    /**
     * Simple operate method, created just for test
     */
    public String operate(int toWithDraw) {
        LinkedHashMap<Integer, Integer> availableBills = getAvailableBills();
        if (AtmOperator.hasBills(toWithDraw, new LinkedHashMap<>(availableBills)))
            return String.valueOf(AtmOperator.billsToCash(AtmOperator.removeBills(toWithDraw, availableBills)));
        return "declined";

    }

    /**
     * method for demo. Uses AtmOperator operate method to run methods to run.
     */
    public void demo(List<Integer> toWithDrawList) {
        System.out.println("THIS IS A DEMO"+ "\n"+"*****************************");
        for (Integer withDraw : toWithDrawList) {
            try {
                System.out.println("Amount to withdraw:" + withDraw);
                setAvailableBills(AtmOperator.operate(withDraw, availableBills));

                System.out.println(
                        "ATM returns this bills: " + AtmOperator.billsToString(AtmOperator.returnSmallestAmount(withDraw, availableBills))
                        + "\n" + "ATM actual money amount: " + AtmOperator.billsToCash(availableBills)
                        + " and ATMs actual bill account:" + AtmOperator.billsToString(availableBills) + "\n");
            } catch (Exception e) {
                System.out.println("*** OPERATION DECLINED ***"+"\n");
                ;
            }
        }
    }

}
