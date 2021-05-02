import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 5/1/2021
 * Time: 16:08
 * Project: redmind_atm
 * Copyright: MIT
 */
public class Demo {
    /**
     * a simple demo to check the functionality
     *
     * @param args
     */
    public static void main(String[] args) {
        //DEMO SET UP
        //set the bills for the ATM
        LinkedHashMap<Integer, Integer> availableBills = new LinkedHashMap<>();
        availableBills.put(1000, 2);
        availableBills.put(500, 3);
        availableBills.put(100, 5);

        //set the the withdraw operations to run
        List<Integer> withDrawList = new ArrayList<>() {{
            add(1500);
            add(700);
            add(400);
            add(1100);
            add(1000);
            add(700);
            add(300);
        }};
        //Setup the ATM and operate
        Atm atmDemo = new Atm(availableBills);
        atmDemo.demo(withDrawList);
    }
}
