package store;

import java.io.*; 
import javafx.stage.Stage;

public class Customer extends User {
    
    //Instance variables.
    private double point;
    private String userName;
    private String password;
    private String status;
    
    private String data = "Name Of The Wind,50\n"
            + "The Way Of Kings,45\n"
            + "The Wise Man's Fear,60";    
    
    File file;
    FileReader out; 
    FileWriter in; 
    BufferedWriter write;
    BufferedReader read; 
    
    //Constructor for Customer class.
    public Customer(String userName, String password, String status, double point){
        this.userName = userName;
        this.password = password; 
        this.status = status;
        this.point = point;
    }
    
    //method to buy book. 
    public void Buy (String bookName) throws IOException{   
        
        //Reads the "Book.txt" file. 
        out = new FileReader("Book.txt");
        read = new BufferedReader(out);
        
        String thisLine;
        String data = "";
        String newLine;
        while((thisLine = read.readLine()) != null){
            data += thisLine + "\r\n";
        }
        read.close();
        newLine = data.replaceAll(bookName, "DELETED");
        in = new FileWriter("Book.txt");
        in.write(newLine);
        in.close();
    }
    
    //method to deduct points. 
    public void deductPoint (String user, double point) throws IOException {       
        this.point = this.point - point; 
        Owner o  = new Owner(user, password);
        String lineSplit[];
        out = new FileReader("Customer.txt");
        BufferedReader read  = new BufferedReader(out);
            
        String line;
        String data = "";
            
        while((line = read.readLine()) != null){
            lineSplit = null;
            lineSplit = line.split(",");
            userName = lineSplit[0];
            password = lineSplit[1];
            status = o.changeStatus(this.point);
            
            if(user.equals(userName)){
                data+=userName+","+password+","+status+","+this.point+"\r\n";
                System.out.println(data);
            }
            else{
                data+=line+"\r\n";
            }
        }
        read.close();
        in = new FileWriter("Customer.txt");
        in.write(data);
        in.close();
    }
    
    //Method to add points. 
    public void addPoint (String user, double point) throws IOException {       
        this.point = this.point + point; 
        Owner o  = new Owner(user, password);
        String lineSplit[];
        out = new FileReader("Customer.txt");
        BufferedReader read  = new BufferedReader(out);
            
        String line;
        String data = "";
            
        while((line  = read.readLine()) != null){
            lineSplit = null;
            lineSplit = line.split(",");
            userName = lineSplit[0];
            password = lineSplit[1];
            status = o.changeStatus(this.point);
            
            if(user.equals(userName)){
                data+=userName+","+password+","+status+","+this.point+"\r\n";
                System.out.println(data);
            }
            else{
                data+=line+"\r\n";
            }
        }
        read.close();
        in = new FileWriter("Customer.txt");
        in.write(data);
        in.close();
    }
    
    //method to get userName. 
    public String getUserName() {
        return userName;
    }
    
    //method to set userName.
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    //method to get password.
    public String getPassword() {
        return password;
    }
    
    //method to set password. 
    public void setPassword(String password){
        this.password = password;
    }
    
    //method to set points. 
    public void setPoint(double point){
        this.point = point;
    }
    
    //method to get point. 
    public double getPoint() {
        return point;
    }
    
    //method to get status. 
    public String getStatus () {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    //method to initialize bookStore. 
    public void initializeBookStore() throws IOException{
        //creates new file called "Book.txt". 
        file = new File("Book.txt");
        //adds/writes into the file created. 
        in = new FileWriter("Book.txt", true);
        write = new BufferedWriter(in);
       
        if(file.length() == 0){
            write.write(data);
            write.newLine();
            write.close();
        }
    }
    //method to check if password is correct. 
    boolean checkCust(String user, String pass){
        String [] lineSplit;
                
        try{
            out = new FileReader("Customer.txt");
            BufferedReader read  = new BufferedReader(out);
            
            String line;
            
            while((line  = read.readLine()) != null){
                lineSplit = null;
                lineSplit = line.split(",");
                userName = lineSplit[0];
                password = lineSplit[1];
                
                if(user.equals(userName) && pass.equals(password)){
                    return true; 
                }
            }
        } catch (FileNotFoundException ex) {
                    
        } catch (IOException ex) {                    
        }  
        return false; 
    }

    @Override
    public void state_change(Stage primaryStage) {
        LogInFX l = new LogInFX();
        l.start(primaryStage);
    }
}