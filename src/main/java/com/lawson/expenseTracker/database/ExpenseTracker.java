package com.lawson.expenseTracker.database;

import com.lawson.expenseTracker.model.Expense;
import com.lawson.expenseTracker.model.Item;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExpenseTracker {

    private static Connection connection=null;
    private ExpenseTracker(){}

    public static void setConnection(Connection connection){
        ExpenseTracker.connection = connection;
    }

    public static boolean createExpense(){
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

//    public static boolean createItem(){//Idk about this one since you items should be attached to expenses
//
//    }

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

    public static Expense getExpense(){

    }

    public static Item getItem(){

    }

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
}
