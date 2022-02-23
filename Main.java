package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


import java.util.ArrayList;

public class Main extends Application {
    ArrayList<Button> extraButton = new ArrayList<>();
    String[] nameSomeButtom = {"<","C","*","/","+","-",".","0","00","=","(",")"};
    TextField tf;
    public static void main(String[] args) {
        launch(args);
    }
        @Override
        public void start(Stage primaryStage)  {
            primaryStage.setTitle("آلة حاسبة");
            primaryStage.setWidth(300);
            GridPane gp = new GridPane();
            tf = new TextField();
            gp.setVgap(5);
            gp.setHgap(5);
            gp.setAlignment(Pos.CENTER);
            gp.add(tf, 0, 0,6,1);
            int counter = 9;
            CalculatorClick click = new CalculatorClick();
            for(int i=0;i<12;i++){
                Button b = new Button(nameSomeButtom[i]);
                b.setId(nameSomeButtom[i]);
                b.setOnAction(click);
                extraButton.add(b);
            }
            gp.add(extraButton.get(6), 0, 5);
            gp.add(extraButton.get(7), 1, 5);
            gp.add(extraButton.get(8), 2, 5);
            gp.add(extraButton.get(9), 4, 6);
            gp.add(extraButton.get(10), 4, 5);
            gp.add(extraButton.get(11), 5, 5);
            for(int row=1 ; row<4 ; row++){
                for (int col = 0; col < 3; col++) {
                    switch(counter){
                        case 7:
                            gp.add(extraButton.get(0), 4, row);
                            gp.add(extraButton.get(1), 5, row);
                            break;
                        case 4:
                            gp.add(extraButton.get(2), 4, row);
                            gp.add(extraButton.get(3), 5, row);
                            break;
                        case 1:
                            gp.add(extraButton.get(4), 4, row);
                            gp.add(extraButton.get(5), 5, row);
                            break;

                    }

                    if(counter > 0){
                        Button b = new Button(""+counter);
                        b.setOnAction(click);
                        b.setId(counter-- +"");
                        gp.add(b, col, row);
                    }
                }

            }
            Scene s = new Scene(gp);
            primaryStage.setScene(s);
            primaryStage.show();
        }
        String concat ="";
        class CalculatorClick implements EventHandler<ActionEvent> {


            @Override
            public void handle(ActionEvent event) {

                if(((Button) event.getSource()).getId().equals("=")) {
                    Expression e = new ExpressionBuilder(concat).build();
                    double result = e.evaluate();
                    concat = result+"";
                }else if(((Button) event.getSource()).getId().equals("C")){
                    concat = "";
                }else if(((Button) event.getSource()).getId().equals("<")){
                    concat = concat.substring(0,concat.length()-1);
                }else{
                    concat +=((Button) event.getSource()).getId();
                }
                tf.setText(concat);

            }

        }

    }
    /*
    Expression e = new ExpressionBuilder("3 * (sin(25) - 2 )/ 5").build();
        double result = e.evaluate();
        System.out.println(result);
    */