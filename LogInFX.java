package store;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInFX extends Application{
    
    String userName;
    String password;
    
    public LogInFX(){   
    }
    
    @Override
    public void start(Stage primaryStage){
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(15, 25, 25, 25));
        
        //where everything is located. 
        Scene scene = new Scene(root, 300, 250);
        
        //creates button. 
        Button logIn = new Button();
        
        //Labels for userName and password
        Label user = new Label("Username: ");
        Label pwd = new Label("Password: ");
        
        //where username and password for enterted. 
        TextField userField = new TextField(); 
        PasswordField pwdField = new PasswordField();
        
        //text that shows on login button. 
        logIn.setText("Login");
        
        //event handler for handle. Do something when Login is pressed. 
        logIn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                userName = userField.getText();
                password = pwdField.getText();
                
                root.getChildren().clear();
                
                if(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
                    OwnerFX o = new OwnerFX(userName, password);
                    o.start(primaryStage);
                }
                if(!userName.equalsIgnoreCase("admin")){
                    Customer cust = new Customer(userName, password, "Null", 0);
                    if(cust.checkCust(userName, password) == true){
                        CustomerFX c = new CustomerFX(userName, password);
                        c.makeCust(primaryStage, userName, password);
                    }
                    else{
                        error(primaryStage);
                    }
                }
            }
        });
        
        Text scenetitle = new Text("Welcome to the Bookstore App");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        
        root.add(scenetitle, 0, 0, 2, 1);
        root.addRow(1, user, userField);
        root.addRow(2, pwd, pwdField);
        root.addRow(3, logIn);
        Image book = new Image("Store/Icon.jpeg");
        
        primaryStage.getIcons().add(book);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void error(Stage primaryStage) {
        
        Button close = new Button("Close");
        VBox vbox = new VBox(new Text("The information you entered is not a part of our database"), close);
        
        //Verticle box. 
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        
        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}