package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        App testApp = new App();

        String[] pricingUpdate = { "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001",
                "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002",
                "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002",
                "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100",
                "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110" };

        System.out.println("print this");

        for (int i = 0; i < pricingUpdate.length; i++) {
            testApp.processTrade(pricingUpdate[i]);
        }

        boolean dateCheck = false;
        String format = "dd-MM-yyyy HH:mm:ss:SSS";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        String[][] itemisedUpdate = new String[pricingUpdate.length][5];
        String[][] itemisedPrices = new String[testApp.listOfPrices.size()][5];
        System.out.println("size " + testApp.listOfPrices.size());
        for (int i = 0; i < testApp.listOfPrices.size(); i++) {
            itemisedPrices[i] = testApp.convert(testApp.listOfPrices.get(i));
        }
        for (int i = 0; i < pricingUpdate.length; i++) {
            itemisedUpdate[i] = testApp.convert(pricingUpdate[i]);
        }
        for (int i = 0; i < testApp.listOfPrices.size(); i++) {
            for (int j = 0; j < itemisedPrices.length; j++) {
                if (itemisedUpdate[i][1].equals(itemisedPrices[j][1])) {
                    try {
                        Date date1 = dateFormat.parse(itemisedUpdate[i][4]);
                        Date date2 = dateFormat.parse(itemisedPrices[j][4]);
                        if (date1.after(date2)) {
                            dateCheck = true;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("finished");
    }
}
