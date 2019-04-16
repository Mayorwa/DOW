/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadorw;

import java.lang.Runnable;
import javafx.application.Application;
import java.util.Random;
import java.util.Scanner;

import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Group 9 members
 */
public class DeadOrW extends Application {
    int p;
    int q;
    final Label message = new Label("");
    @Override
    public void start(Stage primaryStage) {
        Button play1 = new Button();
        play1.setText("1 Player");
        play1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//                Home(1);
            }
        });
        
        Button play2 = new Button();
        play2.setText("2 Players");
        play2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                People x = new People(1, 2);
                People y = new People(2, 1);
                
                x.guess();
                y.guess();
                
//                Thread thread1 = new Thread(x);
//                Thread thread2 = new Thread(y);
//                
//                thread1.start();
//                thread2.start();
            }
        });
        Label label = new Label("Password");
        final PasswordField pb = new PasswordField();
        
        
        GridPane gridPane = new GridPane();    
        //Setting size for the pane 
        gridPane.setMinSize(600, 700); 
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //Arranging all the nodes in the grid 
        gridPane.add(play1, 0, 2); 
        gridPane.add(play2, 1, 2); 
//         gridPane.add(label, 1, 1); 
//        gridPane.add(pb, 2, 1); 
        Scene scene = new Scene(gridPane,300, 500);
        primaryStage.setTitle("Welcome to dead or W");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
}

class People{
    private int p;
    private int q;
    int maxTry=4;
    int[] random;
    public People(int n1, int n2){
      p = n1;
      q = n2;
    }
//    public void run(){
//        guess();
//    }
    
    public static int[] numberGenerator() {
        Random randy = new Random();
        int[] randArray = {10,10,10,10};

        for(int i=0;i<randArray.length;i++){
            int temp = randy.nextInt(9);  
            randArray[i]=temp;      
        }
        return randArray;
}
    
    public void playGame(int[] arr){
        int indexMatch=0;
        int match=0;
        int[] guess= arr;
        
       
        for(int i=0;i<guess.length;i++){
            if(guess[i]==random[i]){
                indexMatch++;
            }
            else if(guess[i]==random[0] || guess[i]==random[1] || guess[i]==random[2] || guess[i]==random[3]){
                match++;
            }
        }
        maxTry--;
        if(indexMatch==4){
            Result(indexMatch +" dead "+ match + " wounded."+maxTry+" more attempts",1);
        }
        else{
            Result(indexMatch +" dead "+ match + " wounded."+maxTry+" more attempts",0);
        }     
    }
    
    public void guess(){
        Label secondLabel = new Label("Player "+p+" input your numbers below");
        PasswordField input1 = new PasswordField();
        PasswordField input2 = new PasswordField();    
        PasswordField input3 = new PasswordField();    
        PasswordField input4 = new PasswordField();    
        GridPane gridPane = new GridPane();    
        //Setting size for the pane 
        gridPane.setMinSize(600, 700);  
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);  
        Button submit = new Button("Submit");
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //Arranging all the nodes in the grid
        gridPane.add(input1, 1, 1);
        gridPane.add(input2, 2, 1);
        gridPane.add(input3, 1, 2);
        gridPane.add(input4, 2, 2);
        gridPane.add(submit, 3, 3);
        gridPane.add(secondLabel,2,0);

//        secondLabel.setStyle("-fx-label-padding: 20;");
        Scene secondScene = new Scene(gridPane, 300, 500);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("First Stage");
        newWindow.setScene(secondScene);
        int[] nums = new int[4];
        submit.setOnAction((ActionEvent e) -> {
            int val1 = Integer.parseInt(input1.getText());
            int val2 = Integer.parseInt(input2.getText());
            int val3 = Integer.parseInt(input3.getText());
            int val4 = Integer.parseInt(input4.getText());
            newWindow.close();
            nums[0] = val1;
            nums[1] = val2;
            nums[2] = val3;
            nums[3] = val4;
           random = nums;
           Home(q);
        });

        newWindow.show();

    }
    
    public void Result(String res,int res2){
        Label resultLabel = new Label(res);
        Button okay = new Button("Okay");
        Button trya = new Button("Try Again");
        GridPane gridPane = new GridPane(); 
        //Setting size for the pane 
        gridPane.setMinSize(800, 700); 
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //Arranging all the nodes in the grid
        gridPane.add(resultLabel, 2, 0);
        gridPane.add(okay, 3, 2);
        
        if(res2 == 0){
            if(maxTry > 0){
                gridPane.add(trya, 2, 2);
            }
        }
        Scene resultScene = new Scene(gridPane, 300, 500);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Result");
        newWindow.setScene(resultScene);
        okay.setOnAction((ActionEvent e) -> {
            newWindow.close();
        });
        
        trya.setOnAction((ActionEvent e) -> {
            newWindow.close();
            Home(q);
        });
        newWindow.show();
    }
    
    public void Home(int s){
        Label secondLabel = new Label("Player "+q+" your guess below");
        TextField input1 = new TextField();
        TextField input2 = new TextField();    
        TextField input3 = new TextField();    
        TextField input4 = new TextField();    
        GridPane gridPane = new GridPane();    
        //Setting size for the pane 
        gridPane.setMinSize(600, 700);  
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);  
        Button submit = new Button("Submit");
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //Arranging all the nodes in the grid
        gridPane.add(input1, 1, 2);
        gridPane.add(input2, 2, 2);
        gridPane.add(input3, 3, 2);
        gridPane.add(input4, 4, 2);
        gridPane.add(submit, 3, 3);
        gridPane.add(secondLabel,1,0);
         
//        secondLabel.setStyle("-fx-label-padding: 20;");
        Scene secondScene = new Scene(gridPane, 300, 500);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);
        int[] nums = new int[4];
        submit.setOnAction((ActionEvent e) -> {
            int val1 = Integer.parseInt(input1.getText());
            int val2 = Integer.parseInt(input2.getText());
            int val3 = Integer.parseInt(input3.getText());
            int val4 = Integer.parseInt(input4.getText());
            newWindow.close();
            nums[0] = val1;
            nums[1] = val2;
            nums[2] = val3;
            nums[3] = val4;
            playGame(nums);
        });
        
        newWindow.show();
        
    }
}