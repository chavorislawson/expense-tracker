package com.lawson.app;

import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;
import com.lawson.expenseTracker.enums.ItemCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        readExpenses(); //Type check entries
    }
    private static void readExpenses(){// Debating on if this should call more than one method or have this method return a type
        List<Expense> expenses = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String text;

        do{
            Expense expense = enterExpenses();
            List<Item> items = expense.getItems();
            System.out.println("Help:\n" +
                    "Press \"enter\" to continue\n"+
                    "Type \"-e\" to exit.\n");
            text = s.nextLine();
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
            expenses.add(expense);
        }while(!text.equals("-e")); //Get action listener to listen for enter button press
        s.close();
        storeExpenses(expenses);
    }

    private static Expense enterExpenses() {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter Expense Information:");
        System.out.println("Items (Enter as comma separated list): ");
        String text = s.nextLine();
        String[] items = text.split(",");
        // if contains number,  character, or excessive spaces in name
        List<Item> eItems = null;
        try {
            eItems = enterItems(items, s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int counter=1;
        System.out.println("Expense Category: ");
        for(ExpenseCategory e: ExpenseCategory.values()){
            System.out.println("Enter " + counter + " - " + e);
            counter++;
        }
        ExpenseCategory eCat = convertExpenseCategory(s.nextInt());
        counter =1;

        System.out.println("Expense Purchase Method: ");
        for(PurchaseMethod p: PurchaseMethod.values()){
            System.out.println("Enter " + counter + " - " + p);
            counter++;
        }
        PurchaseMethod purchaseMethod = convertPurchaseMethodCategory(s.nextInt());

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
        String description;
        ItemCategory category;
        double price;
        Date date;
        Date time;
        String place;
        String location;
        int counter = 1;
        for(String i: items){
            i = i.trim();
            System.out.println(i +" Description:");
            description = s.nextLine();
            System.out.println(i +" Category:");
            for(ItemCategory ic: ItemCategory.values()){
                System.out.println("Enter " + counter + " - " + ic);
                counter++;
            }
            category = convertItemCategory(s.nextInt());
            System.out.println(i +" price:");
            price = s.nextDouble();
            s.nextLine();
            counter = 1;

            if(eItems.isEmpty()) {
                System.out.println(i + " purchase date (MM-dd-yyyy):");
                date = sdfd.parse(s.nextLine());
                System.out.println(i + " purchase time (HH:mm):");
                time = sdft.parse(s.nextLine());
                System.out.println(i + " place:");
                place = s.nextLine();
                System.out.println(i + " location:");
                location = s.nextLine();
            }else{
                Item firstItem = eItems.get(0);
                date = firstItem.getPurchaseDate();
                time = firstItem.getPurchaseTime();
                place = firstItem.getPurchasePlace();
                location = firstItem.getPurchaseLocation();
            }
                eItems.add(new Item(i, description, category, price, date,time, place, location));
        }
        return eItems;
    }

    private static ItemCategory convertItemCategory(int catNumber){
        for(ItemCategory it: ItemCategory.values()){
            if(catNumber-1 == it.ordinal()){ return it; }
        }
        return ItemCategory.RANDOM;
    }

    private static ExpenseCategory convertExpenseCategory(int catNumber){
        for(ExpenseCategory e: ExpenseCategory.values()){
            if(catNumber-1 == e.ordinal()){ return e; }
        }
        return ExpenseCategory.RANDOM;
    }

    private static PurchaseMethod convertPurchaseMethodCategory(int catNumber){
        for(PurchaseMethod p: PurchaseMethod.values()){
            if(catNumber-1 == p.ordinal()){ return p; }
        }
        return PurchaseMethod.UNKNOWNN;
    }

    private static boolean  storeExpenses(List<Expense> expenses){
        File exp = new File("C:\\expense-tracker\\docs\\expense-tracker\\output.txt");
        PrintWriter pw = null;
        Scanner s = null;
        try {
            pw = new PrintWriter(exp);
            s =  new Scanner(exp);
        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        }

        for(Expense e: expenses){
            pw.println(e.getPurchaseMethod());
            pw.println(e.getTotalPrice());
            pw.println(e.getDate());
            pw.println(e.getPurchasePlace());
            pw.println(e.getPurchaseLocation());
            pw.println(e.getPurchaseTime());
            for(Item i: e.getItems()){
                pw.println(i.getName());
                pw.println(i.getDescription());
                pw.println(i.getCategory());
                pw.println(i.getPrice());
            }
        }
        pw.close();

        if(s.hasNext()) {
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
            s.close();
            return true;
        }
        return false;
    }
}