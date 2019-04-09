/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadorw;

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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Group 9 members
 */
public class DeadOrW extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button play1 = new Button();
        play1.setText("1 Player");
        play1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Home(1);
            }
        });
        
        Button play2 = new Button();
        play2.setText("2 Players");
        play2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Number 2");
            }
        });
        
        GridPane gridPane = new GridPane();    
        //Setting size for the pane 
        gridPane.setMinSize(300, 500); 
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
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Welcome to dead or W");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static int[] numberGenerator() {
        Random randy = new Random();
        int[] randArray = {10,10,10,10};

        for(int i=0;i<randArray.length;i++){
            int temp = randy.nextInt(9);
            while(temp == randArray[0] || temp == randArray[1] || temp == randArray[2] || temp == randArray[3]){
                temp=randy.nextInt(9);
            }
            randArray[i]=temp;      
        }
        return randArray;
}
    
    public void playGame(int[] arr){
        int[] random=numberGenerator();
        int maxTry=5;
        int indexMatch=0;
        int match=0;
        while(maxTry>0 && indexMatch!=4){
            int[] guess= arr;
            indexMatch=0;
            match=0;
            for(int i=0;i<guess.length;i++){
                if(guess[i]==random[i]){
                    indexMatch++;
                }
                else if(guess[i]==random[0] || guess[i]==random[1] || guess[i]==random[2] || guess[i]==random[3]){
                    match++;
                }
            }
            if(indexMatch==4){
                System.out.print("Well done! Your guess is Correct! The number is: ");
                for(int i=0;i<guess.length;i++){
                    System.out.print(guess[i]);
                }
            }
            else{
                maxTry--;
                if(maxTry>1){
                    System.out.println("You have guess "+indexMatch+" correct number in correct position,"+
                    " and "+match+" correct number in incorrect position. \n"+maxTry+" attempt remaining.");
                }
                else if(maxTry==1){
                    System.out.println("You have guess "+indexMatch+" correct number in correct position,"+
                    " and "+match+" correct number in incorrect position. \nLast attempt!. Good luck");
                }
                else{
                    System.out.println("Sorry, you failed to guess the number in 5 attempts.");
                    System.out.print("The number is: ");
                    for(int i=0;i<random.length;i++){
                        System.out.print(random[i]);
                    }
                }
            }
        }
        
    }
    
    public void Home(int type){
        Label secondLabel = new Label("Input your guesses below");
        TextField input1 = new TextField();    
        TextField input2 = new TextField();    
        TextField input3 = new TextField();    
        TextField input4 = new TextField();    
        GridPane gridPane = new GridPane();    
        //Setting size for the pane 
        gridPane.setMinSize(200, 400); 
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);  
        Button submit = new Button("Calculate");
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
        //Arranging all the nodes in the grid
        gridPane.add(secondLabel, 2, 0);
        gridPane.add(input1, 1, 1);
        gridPane.add(input2, 2, 1);
        gridPane.add(input3, 3, 1);
        gridPane.add(input4, 4, 1);
        gridPane.add(submit, 3, 2);

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
            nums[0] = val1;
            nums[1] = val2;
            nums[2] = val3;
            nums[3] = val4;
            playGame(nums);
        });
        
        newWindow.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
