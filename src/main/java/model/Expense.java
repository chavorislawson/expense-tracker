package model;

import java.util.List;
import java.util.Objects;

public class Expense {

    private List<Item> items;
    private double totalPrice;
    private String purchasePlace;
    private Category category;
    private String description;
    private PurchaseMethod purchaseMethod;

    public Expense(){

    }

    public Expense(List<Item> items, double totalPrice, String purchasePlace, Category category, String description, PurchaseMethod purchaseMethod) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.purchasePlace = purchasePlace;
        this.category = category;
        this.description = description;
        this.purchaseMethod = purchaseMethod;
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

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPurchaseMethod(PurchaseMethod purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.getTotalPrice(), getTotalPrice()) == 0 && getItems().equals(expense.getItems()) && Objects.equals(getPurchasePlace(), expense.getPurchasePlace()) && Objects.equals(getCategory(), expense.getCategory()) && Objects.equals(getDescription(), expense.getDescription()) && Objects.equals(getPurchaseMethod(), expense.getPurchaseMethod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems(), getTotalPrice(), getPurchasePlace(), getCategory(), getDescription(), getPurchaseMethod());
    }

    @Override
    public String toString() {
        return "Expense{" +
                "items=" + items +
                ", totalPrice=" + totalPrice +
                ", purchasePlace='" + purchasePlace + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", purchaseMethod=" + purchaseMethod +
                '}';
    }
}
