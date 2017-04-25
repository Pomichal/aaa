package rozhranie;

import java.io.File;
import java.io.IOException;

import hra.Turn;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HlavneOkno extends Application{
	
	private Button start = new Button("Nova hra");
	private Button uloz = new Button("Ulozit hru");
	private Button nacitaj = new Button("Nacitaj hru");
	

	public void start(Stage hlavneOkno){
		
		Turn turn = new Turn();
		Rozhranie roz = new Rozhranie(turn); 
		
		hlavneOkno.setTitle("Semitas et Civitas");
		GridPane pane = new GridPane();
		
		pane.setMinSize(500, 400);
		pane.setPadding(new Insets(10,10,10,10));
		pane.setVgap(20);
		pane.setHgap(5);
		pane.setAlignment(Pos.TOP_CENTER);
		
		pane.add(start, 1, 1);
		pane.add(nacitaj, 1, 2);
		pane.add(uloz, 1, 3);
		
		start.setOnAction(e ->{
			roz.show();
		});
		
		nacitaj.setOnAction(e -> {
  			FileChooser fc = new FileChooser();
  			fc.setTitle("Nacitaj");
  			File f = fc.showOpenDialog(roz);

			try {
				turn.nacitaj(f);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		uloz.setOnAction(e -> {
  			FileChooser fc = new FileChooser();
  			fc.setTitle("Uloz ako");
  			File f = fc.showSaveDialog(roz);

			try {
				turn.uloz(f);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		hlavneOkno.setScene(new Scene(pane, 200, 300));
		hlavneOkno.show();
	}
	public static void main(String[] args){
		launch(args);
	}
}
