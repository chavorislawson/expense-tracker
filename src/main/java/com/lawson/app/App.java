package com.lawson.app;

import com.lawson.expenseTracker.database.ExpenseTracker;
import com.lawson.expenseTracker.database.SqlConnector;
import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;
import com.lawson.expenseTracker.enums.ItemCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
        readExpenses(); //Type check entries
    }

    private static void readExpenses(){// Debating on if this should call more than one method or have this method return a type
        List<Expense> expenses = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String text;
        Expense expense = null;
        List<Item> items = null;
        Connection conn = SqlConnector.connect();
        ExpenseTracker.setConnection(conn);
        ExpenseTracker.createExpense();
        try {
            conn.close();
        }catch(SQLException sq){
            sq.printStackTrace();
        }

//        do{
//            try{
//                expense = enterExpenses();
//                exe
//                items = expense.getItems();
//            } catch(ParseException | NullPointerException pe){
//                pe.printStackTrace();
//            }
//
//            System.out.println("Help:\n" +
//                    "Press \"enter\" to continue\n"+
//                    "Type \"-e\" to exit.\n");
//            text = s.nextLine();
//
//            try {
//                for (Item i : items) {
//                    System.out.println(i.getName());
//                    System.out.println(i.getDescription());
//                    System.out.println(i.getCategory());
//                    System.out.println(i.getPrice());
//                }
//                System.out.println();
//                System.out.println(expense.getCategory());
//                System.out.println(expense.getPurchaseMethod());
//                System.out.println(expense.getTotalPrice());
//                System.out.println(expense.getDate());
//                System.out.println(expense.getPurchasePlace());
//                System.out.println(expense.getPurchaseLocation());
//            }catch(NullPointerException e){
//                e.printStackTrace();
//            }
//            expenses.add(expense);
//        }while(!text.equals("-e")); //Get action listener to listen for enter button press
//        s.close();
//        storeExpenses(expenses);
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