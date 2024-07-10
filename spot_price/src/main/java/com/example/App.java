package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Hello world!
 *
 */
public class App {

    ArrayList<String> listOfPrices = new ArrayList<String>();

    public String[] convert(String csvString) {
        String[] split = csvString.split(",");
        for (String item : split) {
            item = item.trim();
        }
        return split;
    }

    public String reformat(String[] components) {
        String convertedString = "";
        for (String item : components) {
            convertedString = convertedString + item + ",";
        }
        return convertedString;
    }

    public String bid(String csvString) {
        String[] components = convert(csvString);
        double newBid = Double.parseDouble(components[2]) - (Double.parseDouble(components[2]) / 10);
        components[2] = String.format("%.4f", newBid);
        return reformat(components);
    }

    public String ask(String csvString) {
        String[] components = convert(csvString);
        double newBid = Double.parseDouble(components[3]) + (Double.parseDouble(components[3]) / 10);
        components[3] = String.format("%.4f", newBid);
        return reformat(components);
    }

    public boolean checkUpdatePrice(String csvString) {
        String[] instrument = this.convert(csvString);
        for (int i = 0; i < listOfPrices.size(); i++) {
            String[] otherInstrument = this.convert(listOfPrices.get(i));
            if (instrument[1].equals(otherInstrument[1])
                    && Integer.parseInt(instrument[0]) > Integer.parseInt(otherInstrument[0])) {
                listOfPrices.set(i, csvString);
                return true;
            }
        }
        return false;
    }

    public String[][] identifyComponents() {
        String[][] components = new String[this.listOfPrices.size()][5];
        for (int i = 0; i < this.listOfPrices.size(); i++) {
            components[i] = convert(this.listOfPrices.get(i));
        }
        return components;
    }

    public void sort() {
        String[][] components = identifyComponents();

        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                return Integer.parseInt(a[0]) - Integer.parseInt(b[0]);
            }
        };

        // Sort the array using the custom comparator
        Arrays.sort(components, comparator);

        for (int i = 0; i < this.listOfPrices.size(); i++) {
            this.listOfPrices.set(i, reformat(components[i]));
        }
    }

    public String processTrade(String csvString) {
        String trade = ask(csvString);
        trade = bid(trade);
        if (!checkUpdatePrice(trade)) {
            listOfPrices.add(trade);

        }
        sort();
        return trade;
    }
}
