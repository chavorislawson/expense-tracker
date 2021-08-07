package com.lawson.app;

import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;
import com.lawson.expenseTracker.enums.ItemCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Author Chavoris Lawson
 *
 * Application Class for Expense Tracker
 *
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Hello World");
        readExpenses();
    }
    private static void readExpenses(){

        //String text="";
//        //System.out.println("Help:" +
//                            "Type any character"+
//                            "type \'-e\' to exit.");

        //do{
        Expense expense = enterExpenses();
        List<Item> items = expense.getItems();
        try {
            for (Item i : items) {
                System.out.println(i.getName());
                System.out.println(i.getDescription());
                System.out.println(i.getCategory());
                System.out.println(i.getPrice());
                System.out.println(i.getPurchaseDate());
                System.out.println(i.getPurchaseTime());
                System.out.println(i.getPurchasePlace());
                System.out.println(i.getPurchaseLocation());
            }
            System.out.println();
            System.out.println(expense.getCategory());
            System.out.println(expense.getPurchaseMethod());
            System.out.println(expense.getTotalPrice());
            System.out.println(expense.getDate());
            System.out.println(expense.getPurchasePlace());
            System.out.println(expense.getPurchaseLocation());
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        //}while(!text.equals("-e"))

    }
    private static Expense enterExpenses() { //is it better to make some parameters local variables?
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Expense Information:");
        System.out.println("Items (Enter as comma separated list): ");
        String text = s.nextLine();
        String[] items = text.split(","); //figure out how to ignore whitespace
        List<Item> eItems = null;
        try {
            eItems = enterItems(items, s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Category:\n" + //make code to read this out instead of hardcoding
                "Enter 1 - RESTAURANT\n" +
                "2 - GROCERY\n" +
                "3 - CONVENIENCE\n" +
                "4 - BILL\n" +
                "5 - CREDIT_CARD\n" +
                "6 - CLOTHING\n" +
                "7 - MEDICAL\n" +
                "8 - DENTAIL\n" +
                "9 - GENERAL\n" +
                "10 - RANDOM");
        ExpenseCategory eCat = convertExpenseCategory(s.nextInt());
        System.out.println("Purchase Method:\n" +
                "Enter 1 - CITI\n" +
                "2 - AMEX\n" +
                "3 - DISCOVER\n");
        PurchaseMethod purchaseMethod = convertPurchaseMethodCategory(s.nextInt());
        s.close();

        double totalPrice = 0;
        for (int i = 0; i < eItems.size(); i++) {
            totalPrice += eItems.get(i).getPrice();
        }
        Item firstItem = eItems.get(0);
        Date date = firstItem.getPurchaseDate();
        Date time = firstItem.getPurchaseTime();
        String place = firstItem.getPurchasePlace();
        String location = firstItem.getPurchaseLocation();

        return new Expense(eItems, eCat, purchaseMethod, totalPrice, date, time,
                place, location);
    }

    private static List<Item> enterItems(String[] items, Scanner s) throws ParseException {
        List<Item> eItems = new ArrayList<>();
        SimpleDateFormat sdfd = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
        for(String i: items){

            System.out.println(i +" Description:");
            String description = s.nextLine();

            //not sure if I have to do the println trick here or not

            //System.out.println();
            //System.out.println();
            if(eItems.isEmpty()) {
                System.out.println(i + " purchase date (MM-dd-yyyy):");
                String pDate = s.nextLine();
                System.out.println(pDate);
                Date date = sdfd.parse(pDate);
                System.out.println(i + " purchase time (HH:mm):");
                Date time = sdft.parse(s.nextLine());
                System.out.println(i + " place:");
                String place = s.nextLine();
                System.out.println(i + " location:");
                String location = s.nextLine();
                System.out.println(i +" Category:\n" +
                        "enter 1 - FOOD\n" +
                        "2 - CLOTHING\n" +
                        "3 - GROCERY\n" +
                        "4 - ELECTRONIC\n" +
                        "5 - SNACK\n" +
                        "6 - CANDY\n" +
                        "7 - NONE OF THE ABOVE");
                ItemCategory category = convertItemCategory(s.nextInt());
                System.out.println(i +" price:");
                double price = s.nextDouble();
                eItems.add(new Item(i, description, category, price, date,time, place, location));
            }else{
                System.out.println(i +" Category:\n" +
                        "enter 1 - FOOD\n" +
                        "2 - CLOTHING\n" +
                        "3 - GROCERY\n" +
                        "4 - ELECTRONIC\n" +
                        "5 - SNACK\n" +
                        "6 - CANDY\n" +
                        "7 - NONE OF THE ABOVE");
                ItemCategory category = convertItemCategory(s.nextInt());
                System.out.println(i +" price:");
                double price = s.nextDouble();
                Item firstItem = eItems.get(0);
                Date date = firstItem.getPurchaseDate();
                Date time = firstItem.getPurchaseTime();
                String place = firstItem.getPurchasePlace();
                String location = firstItem.getPurchaseLocation();

                eItems.add(new Item(i, description, category, price, date,time, place, location));
            }
        }
        return eItems;
    }

    private static ItemCategory convertItemCategory(int catNumber){
        switch(catNumber) {
            case 1: return ItemCategory.FOOD;
            case 2: return ItemCategory.CLOTHING;
            case 3: return ItemCategory.GROCERY;
            case 4: return ItemCategory.ELECTRONIC;
            case 5: return ItemCategory.SNACK;
            case 6: return ItemCategory.CANDY;
            default: return ItemCategory.RANDOM;
        }
    }

    private static ExpenseCategory convertExpenseCategory(int catNumber){
        switch(catNumber) {
            case 1: return ExpenseCategory.RESTAURANT;
            case 2: return ExpenseCategory.GROCERY;
            case 3: return ExpenseCategory.CONVENIENCE;
            case 4: return ExpenseCategory.BILL;
            case 5: return ExpenseCategory.CREDIT_CARD;
            case 6: return ExpenseCategory.CLOTHING;
            case 7: return ExpenseCategory.MEDICAL;
            case 8: return ExpenseCategory.DENTAL;
            case 9: return ExpenseCategory.GENERAL;
            default: return ExpenseCategory.RANDOM;
        }
    }

    private static PurchaseMethod convertPurchaseMethodCategory(int catNumber){
        switch(catNumber){
            case 1: return PurchaseMethod.CITI;
            case 2: return PurchaseMethod.AMEX;
            case 3: return PurchaseMethod.DISCOVER;
            default: return PurchaseMethod.UNKNOWNN;
        }
    }
}