package com.lawson.app;

import com.lawson.expenseTracker.database.SqlConnector;
import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;
import com.lawson.expenseTracker.enums.ItemCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Chavoris Lawson
 *
 * Application Class for Expense Tracker
 *
 */
public class App {

    public static void main(String[] args) {
        Math.abs(10);
        SqlConnector.connect();
        //readExpenses(); //Type check entries
    }

    private static void readExpenses(){// Debating on if this should call more than one method or have this method return a type
        List<Expense> expenses = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String text;
        Expense expense = null;
        List<Item> items = null;

        do{
            try{
                expense = enterExpenses();
                items = expense.getItems();
            } catch(ParseException | NullPointerException pe){
                pe.printStackTrace();
            }

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

    private static Expense enterExpenses() throws ParseException {
        Scanner s = new Scanner(System.in);
        SimpleDateFormat sdfd = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm");
        int counter=1;
        List<Item> eItems;
        double totalPrice = 0;
        String date;
        String time;
        String place;
        String location;
        String text;
        String[] items;

        System.out.println("Enter Expense Information:");
        System.out.println("Items (Enter as comma separated list): ");
        text = s.nextLine();
        items = text.split(",");
        // if contains number,  character, or excessive spaces in name
        eItems = enterItems(items, s);

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
        s.nextLine();

        for (Item eItem : eItems) {
            totalPrice += eItem.getPrice();
        }
        System.out.println("Expense purchase date (MM-dd-yyyy):");
        date = sdfd.parse(s.nextLine());
        System.out.println("Expense purchase time (HH:mm):");
        time = sdft.parse(s.nextLine());
        System.out.println("Expense purchase place:");
        place = s.nextLine();
        System.out.println("Expense purchase location:");
        location = s.nextLine();

        return new Expense(eItems, eCat, purchaseMethod, totalPrice, date, time,
                place, location);
    }

    private static List<Item> enterItems(String[] items, Scanner s) {
        List<Item> eItems = new ArrayList<>();
        String description;
        ItemCategory category;
        double price;
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

            eItems.add(new Item(i, description, category, price));
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
        try {
            FileWriter out = new FileWriter("C:\\expense-tracker\\docs\\expense-tracker\\output.txt", true);
            FileWriter exp = new FileWriter("C:\\expense-tracker\\docs\\expense-tracker\\expenses.txt", true);
            FileWriter it = new FileWriter("C:\\expense-tracker\\docs\\expense-tracker\\items.txt", true);
            BufferedWriter bwOut = new BufferedWriter(out);
            BufferedWriter bwExp = new BufferedWriter(exp);
            BufferedWriter bwIt = new BufferedWriter(it);
            PrintWriter outPw;
            PrintWriter expPw;
            PrintWriter itPw;
            Scanner s = null;
            Long counter = 1L;
            outPw = new PrintWriter(bwOut);
            expPw = new PrintWriter(bwExp);
            itPw = new PrintWriter(bwIt);
            //s = new Scanner(""); //does this work?
            try {
                for (Expense e : expenses) {
                    e.setId(counter);
                    expPw.print(e.getId() + " ");
                    expPw.print(e.getCategory());
                    expPw.print(e.getPurchaseMethod() + " ");
                    expPw.print(e.getTotalPrice() + " ");
                    expPw.print(e.getDate() + " ");
                    expPw.print(e.getPurchasePlace() + " ");
                    expPw.print(e.getPurchaseLocation() + " ");
                    expPw.print(e.getPurchaseTime());
                    expPw.println();
                    for (Item i : e.getItems()) {
                        i.setExpenseId(e.getId());
                        itPw.print(i.getExpenseId() + " ");
                        itPw.print(i.getName() + " ");
                        itPw.print(i.getDescription() + " ");
                        itPw.print(i.getCategory() + " ");
                        itPw.print(i.getPrice() + " ");
                        itPw.println();
                    }
                    counter++;
                }
                expPw.close();
                itPw.close();
            } catch (NullPointerException np) {
                np.printStackTrace();
            }

//            try {
//                if (s.hasNext()) {
//                    while (s.hasNext()) {
//                        System.out.println(s.nextLine());
//                    }
//                    s.close();
//                    return true;
//                }
//            } catch (NullPointerException np) {
//                np.printStackTrace();
//            }
        }catch(IOException io){
            io.printStackTrace();
        }
        return false;
    }
}