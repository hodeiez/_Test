import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Hodei Eceiza
 * Date: 5/1/2021
 * Time: 16:08
 * Project: redmind_atm
 * Copyright: MIT
 */
public class AtmOperator {
    /**
     * Returns the list of bills in ATM after withdrawal.
     * @param toWithDraw the amount to withdraw int
     * @param availableBills the available bills in the ATM
     * @return the bills list after resting with the withdrawal.
     */
    public static LinkedHashMap<Integer, Integer> removeBills(int toWithDraw, LinkedHashMap<Integer, Integer> availableBills) {
        LinkedHashMap<Integer, Integer> billsToReturn = returnSmallestAmount(toWithDraw, availableBills);

        availableBills.forEach((k, v) -> {
            if (billsToReturn.containsKey(k)) {
                availableBills.replace(k, v, v - billsToReturn.get(k));
            }
        });

        return availableBills;
    }

    /**
     * returns the smallest amount of bills possible
     *
     * @param toWithDraw     the amount of money to withdraw
     * @param availableBills the available bills in the ATM
     * @return returns HashMap of bills and their amount
     */
    public static LinkedHashMap<Integer, Integer> returnSmallestAmount(int toWithDraw, LinkedHashMap<Integer, Integer> availableBills) {
        List<Integer> billValues = billValues(availableBills);
        LinkedHashMap<Integer, Integer> toReturn = new LinkedHashMap<Integer, Integer>();
        for (Integer bill : billValues) {
            int amount = toWithDraw / bill;
            if (toWithDraw / bill >= 1) {
                toReturn.put(bill, amount);

                toWithDraw -= bill * amount;
            }
        }
        return toReturn;
    }

    /**
     * Returns a list of the different bill values available
     * @param availableBills the actual amount of bills in ATM
     * @return return a list of values (1000,500,100)
     */
    public static List<Integer> billValues(LinkedHashMap<Integer, Integer> availableBills) {
        return new ArrayList<>(availableBills.keySet())
                .stream()
                .sorted(Comparator.comparingInt(Integer::intValue)
                        .reversed())
                .collect(Collectors.toList());

    }

    /**
     * Returns true if the ATM can return the bills, based on its available disposal
     * @param toWithDraw the amount of money to withdraw
     * @param availableBills the available bills in the ATM
     * @return true if ATM has the bills to return
     */
    public static boolean hasBills(int toWithDraw, LinkedHashMap<Integer, Integer> availableBills) {


        List<Integer> zero = new ArrayList<>();
        LinkedHashMap<Integer, Integer> removed = removeBills(toWithDraw, availableBills);
        removed.forEach((k, v) -> {
            if (removed.get(k) < 0) zero.add(-1);
        });

        return zero.size() == 0;
    }

    /**
     * Checks if the ATM has enough money to withdraw.
     * @param toWithDraw the amount to withdraw
     * @param availableBills the available bills in the ATM
     * @return true if has money for the transaction
     */
    public static boolean hasCash(int toWithDraw, LinkedHashMap<Integer, Integer> availableBills) {

        return billsToCash(availableBills) - toWithDraw >= 0;
    }

    /**
     * Shows the amount of money in the ATM
     *
     * @param availableBills the available bills
     * @return as Int (could be nice to return as double to use with coins, but no need for this example)
     */
    public static int billsToCash(LinkedHashMap<Integer, Integer> availableBills) {

        return availableBills.entrySet().stream()
                .map(set -> set.getKey() * set.getValue())
                .collect(Collectors.toList())
                .stream()
                .reduce(Integer::sum)
                .get();
    }

    /**
     * To operate the ATM, if it has no enough money or has no available bills for the transaction returns "transaction declined" exception
     * @param toWithDraw the amount to withdraw
     * @param availableBills the availables bills in the ATM
     * @return the quantity of bills in the ATM after the transaction.
     */
    public static LinkedHashMap<Integer, Integer> operate(int toWithDraw, LinkedHashMap<Integer, Integer> availableBills) throws Exception {

        if (hasCash(toWithDraw, availableBills) && hasBills(toWithDraw, new LinkedHashMap<Integer,Integer>(availableBills)))
            return removeBills(toWithDraw, availableBills);
        else
            throw new Exception("Transaction declined");

    }

    /**
     * Formats the LinkedHashMap to a string
     * @param availableBills the bills to show as String
     * @return the string in english
     */
    public static String billsToString(LinkedHashMap<Integer, Integer> availableBills){
            StringBuilder string = new StringBuilder();
            availableBills.forEach((k,v)->string.append(v.toString()+" of "+ k.toString()+", "));
            return string.toString();
        }

}
