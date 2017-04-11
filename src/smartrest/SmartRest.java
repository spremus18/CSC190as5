package smartrest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author csc190
 */
public class SmartRest extends Application 
{
    int x = 0;
    public static String[] kiosk ;
    @Override
    
    /*public void readFile(String filepath)
    {
        private static final String FILENAME = "E:\\test\\filename.txt";
    }*/
            
    public void start(Stage primaryStage)
    {
        List <String> tempArray= new ArrayList<String>();
        Scanner scan = new Scanner(getClass().getResourceAsStream("config.txt"));
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            String sort_colon = line.substring(line.indexOf(":")+1);
            String sort_again = sort_colon.trim();
            
            tempArray.add(sort_again);
        }
        String[] kiosk = tempArray.toArray(new String[0]);
        
        primaryStage.setTitle("Smart Kiosk App");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5,5,5,5));
        
        Text title = new Text(kiosk[x]);
        title.setFont(Font.font("Times", FontWeight.NORMAL, 20));
        title.setFill(Color.BLUE);
        grid.add(title, 0, 2, 2, 1);
        
        Label info = new Label(kiosk[x+1]);
        
        info.setFont(Font.font("Times", FontWeight.NORMAL, 20));
    
    //    info.setFill(Color.BLUE);
        info.setPrefWidth(200);
        info.setWrapText(true);
        grid.add(info, 0, 3, 2, 1);
        
        Label price = new Label("Price:");
        grid.add(price, 1, 2);
        price.setFont(Font.font("Times", FontWeight.NORMAL, 20));
       

        
        Label Itemprice = new Label(kiosk[x+2]);
        grid.add(Itemprice, 2, 2);
        Itemprice.setFont(Font.font("Times", FontWeight.NORMAL, 20));
        
        final Image image = new Image(SmartRest.class.getResourceAsStream(kiosk[x+3])) {};
        final ImageView image_view = new ImageView();
        image_view.setFitWidth(500);
        image_view.setFitHeight(300);
        image_view.setImage(image);

        Button button = new Button("Next -->");
        HBox hit_button = new HBox(3);
        hit_button.setAlignment(Pos.BOTTOM_RIGHT);
        hit_button.getChildren().add(button);
        grid.add(hit_button, 2, 0);
        
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e1){
                if(x+4 == kiosk.length){ x=x; }
                else { 
                   x=x+4;
                   title.setText(kiosk[x]);
                   info.setText(kiosk[x+1]);
                   Itemprice.setText(kiosk[x+2]);
                   image_view.setImage(new Image(SmartRest.class.getResourceAsStream(kiosk[x+3])));
                }

            }
        });
        
        Button button2 = new Button("<-- Prev");
        HBox hit_button2 = new HBox(3);
        hit_button2.setAlignment(Pos.BASELINE_LEFT);
        hit_button2.getChildren().add(button2);
        grid.add(hit_button2, 0, 0);
        
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e2){
            
                
                if (x==0){x=x;}
                else {
                    x=x-4;
                    title.setText(kiosk[x]);
                    info.setText(kiosk[x+1]);
                    Itemprice.setText(kiosk[x+2]);
                    image_view.setImage(new Image(SmartRest.class.getResourceAsStream(kiosk[x+3])));        
            } 
                
            }
        });

        
        final HBox pic_area = new HBox();
        pic_area.getChildren().add(image_view);
        grid.add(pic_area, 0, 1);
        //grid.setGridLinesVisible(true);
        
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void getconfigfile() throws FileNotFoundException, IOException {
        int n=0;
        try (BufferedReader br = new BufferedReader(new FileReader("file:smartrest/config.txt"))){
            String Current_Line;
            while ((Current_Line = br.readLine()) != null) {
                String outcome = Current_Line.substring(Current_Line.indexOf(": ")+1);
                kiosk[n] = outcome;
                n++;
            }
        } catch (IOException e) {
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
   
}