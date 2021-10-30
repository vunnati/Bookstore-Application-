package store;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Book {
    
    //instance variables. 
    private SimpleStringProperty bookName; 
    private SimpleDoubleProperty price; 
    private CheckBox select;
    private static boolean checked;
    private static double total;
    private static ArrayList<String> checkedBooks = new ArrayList<String>();
    private static ArrayList<Double> checkedPrice = new ArrayList<Double>();

    private static ObservableList<Book> data2 = FXCollections.observableArrayList();

    //constructor for Book. 
    public Book(String bookName, double price){
        this.bookName = new SimpleStringProperty(bookName);
        this.price = new SimpleDoubleProperty(price);
        this.select = new CheckBox();
                   
        select.selectedProperty().addListener(
        (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            checked = select.isSelected();
            if (checked == true){
               checkedBooks.add(bookName);
               checkedPrice.add(price);
               total += price;
            } else {
               total -= price;
               checkedBooks.remove(bookName);
               checkedPrice.remove(price);
            }  
        });
    }

    public ObservableList<Book> getData(){
        return data2;
    }
    
    //method to get total price. 
    public double getTotal(){
        return total;
    }
    
    public ArrayList getCheckedBooks() {
        return checkedBooks;
    }
    
    public ArrayList getCheckedPrice() {
        return checkedPrice;
    }
    
    //method to set the bookName.  
    public void setName(String bookName){
        this.bookName.set(bookName);
    }
    
    //method to return the bookName. 
    public String getName(){
        return bookName.get();
    }
    
    //method to set the book price. 
    public void setPrice(double price){
        this.price.set(price);
    }
    
    //method to return the book price. 
    public double getPrice(){
        return price.get();
    }
    
    public CheckBox getSelect() {
        return select;
    }
    
    public void setSelect (CheckBox select) {
        this.select = select;
    }
    
    public String toString(){
        return bookName+"\t"+price;
    }
}