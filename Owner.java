package store;

import java.io.*;
import javafx.stage.Stage;

public class Owner extends User{
    
    //instance variables for points and status. 
    private int Point; 
    private String status; 
    private String userName; 
    private String password;
    
    //String of data to initilize books. 
    private String data = "Name Of The Wind,50\n"
            + "The Way Of Kings,45\n"
            + "The Wise Man's Fear,60";    
    
    //String of data to initilize customer. 
    private String data2 = "Unnati,pass,Gold,10000";
    
    //Used for "Book.txt".
    File file;
    FileReader out; 
    FileWriter in; 
    BufferedWriter write;
    BufferedReader read; 
    
    //Used for "Customer.txt". 
    File file2;
    FileReader out2; 
    FileWriter in2; 
    BufferedWriter write2;
    BufferedReader read2; 
    
    //constructor for Owner. 
    public Owner(String userName, String password){
        this.userName = userName;
        this.password = password; 
    }
    
    //method to set the username. 
    public void setUserName(String userName){
         this.userName = userName;
    }
    
    //method to return the username. 
    public String getUserName(){
        return userName;
    }
    
    //method to set password. 
    public void setPassword(String password){
        this.password = password;
    }
    
    //method to return the password. 
    public String getPassword(){
        return password;
    }
    
    //method to set points. 
    public void setPoint(int Point){
        this.Point = Point;
    }
    
    //method to return the points. 
    public int getPoint(){
        return Point;
    }
    
    //method to remove books. 
    public void removeBooks(String name, double price) throws IOException{         
        //FileReader reads directly from the file. BufferedReader reads from the FileReader. FileReader is slower. 
        out = new FileReader("Book.txt");
        read = new BufferedReader(out);
 
        String thisLine;
        String data = "";
        String newLine;
        while((thisLine = read.readLine()) != null){
            data += thisLine + "\r\n";
        }
        read.close();
        newLine = data.replaceAll(name, "DELETED");
        in = new FileWriter("Book.txt");
        in.write(newLine);
        in.close();   
    }
    
    //Method to initilize books by default. 
    public void initializeBookStore() throws IOException{
        //Creates "Book.txt" text file.
        file = new File("Book.txt");
        //Adds/writes to the file. 
        in = new FileWriter("Book.txt", true);
        write = new BufferedWriter(in);
        
        if(file.length() == 0){
            //if file is empty, writes data string into file. 
            write.write(data);
            write.newLine();
            write.close();
        }
    }
    
    //method to initializeCust. When file is empty, writes the initialied customer info into it. 
    public void initializeCust() throws IOException{
        //Creates "Customer.txt" file. 
        file = new File("Customer.txt");
        
        //Adds to the "Customer.txt" file. 
        in = new FileWriter("Customer.txt", true);
        write = new BufferedWriter(in);
        //If file is empty, writes data2 string into the file. 
        if(file.length() == 0){
 
            write.write(data2);
            write.newLine();
            write.close();
        }
    }
    
    //method to add books. Adds onto the already created "Book.txt" file. 
    public void addBooks(String name, double price) throws IOException{   
        //Adds onto the already created "Book.txt" file. 
        in = new FileWriter("Book.txt", true);
        out = new FileReader("Book.txt");
        write = new BufferedWriter(in);
        
        //creates object for Book. 
        Book b = new Book(name, price);   
        //adds object "Book" and its parameters "name" and "price" into the file. 
        write.write(""+b.getName()+","+b.getPrice());  
        write.newLine();
        write.close();
    }
    
    //method to add customers. 
    public void addCust(String userName, String password, String status, double Point) throws IOException{
        //Adds on to the "Customers.txt" file. 
        in2 = new FileWriter("Customer.txt", true);
        out2 = new FileReader("Customer.txt");
        write2 = new BufferedWriter(in2);
        
        //Creates object for Customer. 
        Customer c = new Customer(userName, password, status, Point); 
        //adds object "Customer" and its parameters "userName", "password", "status", "Point" into the file. 
        write2.write(""+c.getUserName()+","+c.getPassword()+","+c.getStatus()+","+c.getPoint());  
        write2.newLine(); 
        write2.close();
    }
    
    //method to remove customers. Removes customers from the already created "Customer.txt" file. 
    public void removeCust(String userName, String password, String status, double Point) throws IOException{
        out2 = new FileReader("Customer.txt");
        read2 = new BufferedReader(out2);
        
        String thisLine;
        String data = "";
        String newLine;
        while((thisLine = read2.readLine()) != null){
            data += thisLine + "\r\n";
        }
        read2.close();
        
        //Line of data is replced with "deleted". 
        newLine = data.replaceAll(userName, "DELETED");
        in2 = new FileWriter("Customer.txt");
        in2.write(newLine);
        in2.close();
    }
    
    //method change status. 
    public String changeStatus(double Point){
        if(Point < 1000){
            status = "Silver";
        }
        if(Point >= 1000){
            status = "Gold";
        }
        return status; 
    }

    @Override
    public void state_change(Stage primaryStage){
        LogInFX l = new LogInFX();
        l.start(primaryStage);
    }
}
