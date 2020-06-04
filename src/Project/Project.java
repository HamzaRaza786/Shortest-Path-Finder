/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author HP
 */
public class Project extends Application {
    public static  graph gr = new graph();
    public void Draw(Stage primaryStage,String[] arr,int b){
        gr.fileReading();
        gr.addingEdges();
         Circle c ;
         Text text;
         Line L;
        Group root = new Group();
        Scene s = new Scene(root,500,500,Color.WHITE);
        int src = b;
        while(arr[src]!=null){
        c = new Circle(src,src,20,Color.BLUE);
        root.getChildren().add(c);
        text =  new Text();
        text.setX(src);
        text.setY(src - 20);
        text.setFont(Font.font(20.0));
        text.setText(arr[src]);
        root.getChildren().add(text);
        int temp = gr.getLocation(arr[src]);
        if(arr[temp]!= null){
            L = new Line(src+20,src,temp-20,temp);
            root.getChildren().add(L);
        } 
        src = temp;}
        primaryStage.setScene(s);
        primaryStage.show();
    }
    @Override
    public void start(Stage primaryStage) {
        Start a = new Start();
         a.setVisible(true);   
        a.setLocationRelativeTo(null);
        Finder f = new Finder();
        if(true){
       primaryStage.setTitle("SHORTEST PATH FINDER");
      gr.fileReading();
      gr.addingEdges();
       Circle c ;
       Text text;
       Line L;
         String[] arr = gr.shortestPathBFS("Discovery Park","Woodland Park Zoo");
        Group root = new Group();
      Scene s = new Scene(root,500,500,Color.WHITE);
        int src = gr.getLocation("Woodland Park Zoo");
         c = new Circle(src ,src,20,Color.BLUE);
        root.getChildren().add(c);
        text =  new Text();
        text.setX(src);
        text.setY(src);
        text.setFont(Font.font(15.0));
        text.setText(gr.adjList.get(src).name);
        root.getChildren().add(text);
       int temp = gr.getLocation(arr[src]);
       if(arr[temp]!= null){
           L = new Line(src,src,temp,temp);
            root.getChildren().add(L);
        } 
    while(arr[src]!=null){
        temp = gr.getLocation(arr[src]);
        if(arr[src]!= null){
            L = new Line(src ,src - 20,temp,temp );           root.getChildren().add(L);
       }         src = temp;
        c = new Circle(src,src,20,Color.BLUE);
       root.getChildren().add(c);
       text =  new Text();
       text.setX(src);
        text.setY(src);
        text.setFont(Font.font(15.0));
            text.setText(gr.adjList.get(src).name);
        root.getChildren().add(text);
       
       }    primaryStage.setScene(s);
       primaryStage.show();
       
//     
//            btn.setOnAction((ActionEvent event) -> {
//            System.out.println("Hello World!");
//        });
        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
       
    }}

    /**
     * @param args the command line %arguments
     */
    public static void main(String[] args) {
        launch();
            }
    
    
}

