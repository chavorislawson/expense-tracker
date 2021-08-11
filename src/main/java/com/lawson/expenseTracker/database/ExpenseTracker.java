package com.lawson.expenseTracker.database;

import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.enums.ItemCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;
import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseTracker {

    private static Connection connection=null;
    private ExpenseTracker(){}

    public static void setConnection(Connection connection){
        ExpenseTracker.connection = connection;
    }

    public static boolean createExpense(){
        Scanner s = new Scanner(System.in);
        int counter=1;
        Expense expense;
        List<Item> eItems;
        double totalPrice = 0;
        Date date;
        Time time;
        String place;
        String location;
        String text;
        String[] items;

        System.out.println("Enter Expense Information:");
        System.out.println("Items (Enter as comma separated list): ");
        text = s.nextLine();
        items = text.split(",");
        // if contains number,  character, or excessive spaces in name
        eItems = createItems(items, s);

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
        System.out.println("Expense purchase date (yyyy-MM-dd):");
        date = Date.valueOf(s.nextLine());
        System.out.println("Expense purchase time (HH:mm):");
        time = Time.valueOf(s.nextLine());
        System.out.println("Expense purchase place:");
        place = s.nextLine();
        System.out.println("Expense purchase location:");
        location = s.nextLine();

        expense = new Expense(eItems, eCat, purchaseMethod, totalPrice, date, time,
                place, location);

        return storeExpense(expense);
    }

    private static List<Item> createItems(String[] items, Scanner s){//Idk about this one since you items should be attached to expenses
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

    private static boolean storeExpense(Expense expense){
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        if(expense == null){
            return false;
        }else{
            try {
                //stmt = connection.createStatement();//executeUpdate instead of query
                ps = connection.prepareStatement("INSERT INTO expenses (category, purchaseMethod, totalPrice, date, place, location, time) VALUES ("
                        + expense.getCategory() + ", " + expense.getPurchaseMethod() + ", " + expense.getTotalPrice() + ", " + expense.getDate() +
                        ", " + expense.getPurchasePlace() + ", " + expense.getPurchaseLocation() + ", ?)",Statement.RETURN_GENERATED_KEYS);
//                stmt.executeUpdate("INSERT INTO expenses (category, purchaseMethod, totalPrice, date, place, location, time) VALUES ("
//                        + expense.getCategory() + ", " + expense.getPurchaseMethod() + ", " + expense.getTotalPrice() + ", " + expense.getDate() +
//                        ", " + expense.getPurchasePlace() + ", " + expense.getPurchaseLocation() + ", " + expense.getPurchaseTime() + ")",Statement.RETURN_GENERATED_KEYS);// insert into expense table expenses and items into item table
                ps.setTime(8,expense.getPurchaseTime());
                ps.executeUpdate();
                //String insertExpenses;
                //for()
                return true;
            }catch (SQLException s){
                s.printStackTrace();
            }finally {
                if(rs!=null) {
                    try {
                        rs.close();
                    } catch (SQLException s) {
                    }
                }
                if(stmt!=null) {
                    try {
                        ps.close();
                    } catch (SQLException s) {
                    }
                }
            }
            return true;
        }
    }

    public static boolean getAllExpenses(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM expense");
            return true;
        }catch (SQLException s){
            s.printStackTrace();
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException s) {
                }
            }
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException s) {
                }
            }
        }
        return false;
    }

//    public static Expense getExpense(){
//
//    }
//
//    public static Item getItem(){
//
//    }

//    public static List<Expense> getExpenses(){
//
//    }
//
//    public static List<Item> getItems(){
//
//    }
//
//    public static boolean updateExpense(Expense expense){
//
//    }
//
//    public static boolean updateItem(){
//
//    }
//
//    public static boolean deleteExpense(){
//
//    }
//
//    public static boolean deleteItem(){
//
//    }

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
}
