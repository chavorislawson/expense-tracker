package com.lawson.expenseTracker.model;

import com.lawson.expenseTracker.enums.ExpenseCategory;
import com.lawson.expenseTracker.enums.PurchaseMethod;

import java.util.Date;
import java.util.List;

public class Expense {

    private List<Item> items;

    /**
     * Where this purchase took place. Ex. Chic-Fil-A
     */

    /**
     * Where this purchase took place. Ex. Atlanta, GAs
     */
    private ExpenseCategory category;
    private PurchaseMethod purchaseMethod;
    private double totalPrice;
    private Date date;
    private Date purchaseTime;
    private String purchasePlace;
    private String purchaseLocation;

    public Expense(){}

    public Expense(List<Item> items, ExpenseCategory category, PurchaseMethod purchaseMethod, double totalPrice, Date date, Date purchaseTime, String purchasePlace, String purchaseLocation) {
        this.items = items;
        this.category = category;
        this.purchaseMethod = purchaseMethod;
        this.totalPrice = totalPrice;
        this.date = date;
        this.purchaseTime = purchaseTime;
        this.purchasePlace = purchasePlace;
        this.purchaseLocation = purchaseLocation;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public PurchaseMethod getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public void setPurchaseMethod(PurchaseMethod purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    public void setPurchaseLocation(String purchaseLocation) {
        this.purchaseLocation = purchaseLocation;
    }

//    @Override
//    public String toString() {
//        return "Expense{" +
//                "items=" + items +
//                ", category=" + category +
//                ", purchaseMethod=" + purchaseMethod +
//                ", totalPrice=" + totalPrice +
//                ", date=" + date +
//                ", purchaseTime=" + purchaseTime +
//                ", purchasePlace='" + purchasePlace + '\'' +
//                ", purchaseLocation='" + purchaseLocation + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Expense expense = (Expense) o;
//        return Double.compare(expense.getTotalPrice(), getTotalPrice()) == 0 && getItems().equals(expense.getItems()) && getCategory() == expense.getCategory() && getPurchaseMethod() == expense.getPurchaseMethod() && Objects.equals(getDate(), expense.getDate()) && Objects.equals(getPurchaseTime(), expense.getPurchaseTime()) && Objects.equals(getPurchasePlace(), expense.getPurchasePlace()) && Objects.equals(getPurchaseLocation(), expense.getPurchaseLocation());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getItems(), getCategory(), getPurchaseMethod(), getTotalPrice(), getDate(), getPurchaseTime(), getPurchasePlace(), getPurchaseLocation());
//    }
}
