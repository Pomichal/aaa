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
	
	private Button BavlnovoTlacidlo = new Button("Bavlnovo");
	private Button DrevovoTlacidlo = new Button("Drevovo");
	private Button KamenovoTlacidlo = new Button("Kamenovo");
	private Button MramorovoTlacidlo = new Button("Mramorovo");
	
	/*public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(BavlnovoTlacidlo);
		
		BavlnovoTlacidlo.setOnAction(e -> new OknoBavlnovo());

		hlavneOkno.setScene(new Scene(pane, 400, 250));
		hlavneOkno.show();
	}*/
	
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
	
	private SledovatelPenazi Peniaze;


	@Override
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		Mesto[] Mesta= Turn.Nastav();
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);
		pane.getChildren().add(BavlnovoTlacidlo);
		pane.getChildren().add(DrevovoTlacidlo);
		pane.getChildren().add(KamenovoTlacidlo);
		pane.getChildren().add(MramorovoTlacidlo);
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
			vypis.appendText(Turn.Kolo(Mesta)
							+ Turn.vypis(Mesta));
			}
		);
		
		vyprava.setOnAction(e -> {
			int start=cbstart.getValue();
			int ciel=cbciel.getValue();
			int typ=cbtyp.getValue();
			int mnoz =Integer.parseInt(mnozstvo.getText());
			vypis.appendText(Mesta[start].getStajna().vyslatVypravu(Mesta, start, ciel, typ, mnoz, 0) + "\n");
		});
		
		BavlnovoTlacidlo.setOnAction(e -> new OknoBavlnovo(Mesta));
		DrevovoTlacidlo.setOnAction(e -> new OknoDrevovo(Mesta));
		KamenovoTlacidlo.setOnAction(e -> new OknoKamenovo(Mesta));
		MramorovoTlacidlo.setOnAction(e -> new OknoMramorovo(Mesta));
		
		Peniaze = new SledovatelPenazi(Mesta[0]);
		Mesta[0].pridajSledovatela(Peniaze);;
		pane.getChildren().add(Peniaze);
		
		hlavneOkno.setScene(new Scene(pane, 560, 300));
		hlavneOkno.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}