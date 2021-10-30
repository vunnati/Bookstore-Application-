package store;

//Everything in User extends off of LogIn.

import javafx.stage.Stage;

abstract class User{
    
    //Method to change state. 
    public abstract void state_change(Stage primaryStage);
}