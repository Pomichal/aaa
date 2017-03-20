package rozhranie;

import mesta.*;

import hra.Turn;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Rozhranie extends Application{
	private Button turn = new Button("Turn");
	private Button vyprava = new Button("Poslat vypravu");
	private TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);
	private Label startOzn = new Label("Start");
	private Label cielOzn = new Label("Ciel");
	private Label typTovaruOzn = new Label("TypTovaru");	
	private Label mnozstvoTovaruOzn = new Label("MnozstvoTovaru");
	private TextField mnozstvo = new TextField();
	final ComboBox<Integer> cbstart = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3));
	final ComboBox<Integer> cbciel = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3));
	final ComboBox<Integer> cbtyp = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3));


	@Override
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		Mesto[] Mesta= Turn.Nastav();
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);
		pane.getChildren().add(skrolVypis);
		pane.getChildren().add(startOzn);
		pane.getChildren().add(cbstart);
		pane.getChildren().add(cielOzn);
		pane.getChildren().add(cbciel);
		pane.getChildren().add(typTovaruOzn);
		pane.getChildren().add(cbtyp);
		pane.getChildren().add(mnozstvoTovaruOzn);
		pane.getChildren().add(mnozstvo);
		pane.getChildren().add(vyprava);

		
		turn.setText("Zacat hru");
		
		turn.setOnAction(e -> { // lambda výraz s odvodením typu z kontextu
			vypis.clear();
			turn.setText("Nove kolo");
			vypis.appendText(Turn.Kolo(Mesta) + ". Kolo\n"
							+ Mesta[0].getPeniaze() + " zlatych\n" 
							+ Turn.vypis(Mesta));
			}
		);
		
		vyprava.setOnAction(e -> {
			int start=cbstart.getValue();
			int ciel=cbciel.getValue();
			int typ=cbtyp.getValue();
			int mnoz =Integer.parseInt(mnozstvo.getText());
			vypis.appendText(Mesta[0].getStajna().vyslatVypravu(Mesta, start, ciel, typ, mnoz, 0) + "\n");
		});
		
		hlavneOkno.setScene(new Scene(pane, 560, 300));
		hlavneOkno.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}