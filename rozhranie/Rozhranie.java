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
	private Label zamerOzn = new Label("Zamer vypravy: ");
	private Label bohatstvo = new Label("Peniaze:");
	private Label pocetkol = new Label("Kolo:");
	private TextField mnozstvo = new TextField();
	

	Mesto[] Mesta= Turn.Nastav();
	
	final ComboBox<Mesto> cbstart = new ComboBox<Mesto>(FXCollections.observableArrayList(Mesta));
	final ComboBox<Mesto> cbciel = new ComboBox<Mesto>(FXCollections.observableArrayList(Mesta));
	final ComboBox<Integer> cbtyp = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3));
	final ComboBox<Integer> cbzamer = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2));
	
	private SledovatelPenazi Peniaze;
	private SledovatelKol Kolo;


	@Override
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);
		pane.getChildren().add(BavlnovoTlacidlo);
		pane.getChildren().add(DrevovoTlacidlo);
		pane.getChildren().add(KamenovoTlacidlo);
		pane.getChildren().add(MramorovoTlacidlo);

		
		Kolo = new SledovatelKol();
		Mesta[0].pridajSledovatela(Kolo);;
		pane.getChildren().add(pocetkol);
		Kolo.setPrefWidth(100);
		pane.getChildren().add(Kolo);
		
		pane.getChildren().add(skrolVypis);
		pane.getChildren().add(startOzn);
		pane.getChildren().add(cbstart);
		pane.getChildren().add(cielOzn);
		pane.getChildren().add(cbciel);
		pane.getChildren().add(typTovaruOzn);
		pane.getChildren().add(cbtyp);
		pane.getChildren().add(mnozstvoTovaruOzn);
		pane.getChildren().add(mnozstvo);
		pane.getChildren().add(zamerOzn);
		pane.getChildren().add(cbzamer);
		pane.getChildren().add(vyprava);
		
		
		turn.setText("Zacat hru");
		
		turn.setOnAction(e -> { // lambda výraz s odvodením typu z kontextu
			vypis.clear();
			turn.setText("Nove kolo");
			Turn.zvysKolo();
			vypis.appendText(Turn.Kolo(Mesta)
							+ Turn.vypis(Mesta));
			}
		);
		
		vyprava.setOnAction(e -> {
			Mesto start=cbstart.getValue();
			Mesto ciel=cbciel.getValue();
			int typ=cbtyp.getValue();
			int mnoz =Integer.parseInt(mnozstvo.getText());
			int zamer=cbzamer.getValue();
			vypis.clear();
			if(zamer==2)vypis.appendText(start.getObchod().vyslatVypravu(start, ciel, typ, mnoz) + "\n");
			else
			vypis.appendText(start.getStajna().vyslatVypravu(start, ciel, typ, mnoz, zamer) + "\n");
			vypis.appendText(Turn.vypis(Mesta));
		});
		
		BavlnovoTlacidlo.setOnAction(e -> {
		new OknoBavlnovo(Mesta);
		Mesta[0].upozorniSledovatelov();
		});
		
		DrevovoTlacidlo.setOnAction(e -> {
		new OknoDrevovo(Mesta);
		Mesta[0].upozorniSledovatelov();
		});
		
		KamenovoTlacidlo.setOnAction(e -> {
		new OknoKamenovo(Mesta);Mesta[0].upozorniSledovatelov();
		});
		
		MramorovoTlacidlo.setOnAction(e -> {
		new OknoMramorovo(Mesta);
		Mesta[0].upozorniSledovatelov();
		});
		
		Peniaze = new SledovatelPenazi(Mesta[0]);
		Mesta[0].pridajSledovatela(Peniaze);;
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		
		hlavneOkno.setScene(new Scene(pane, 500, 300));
		hlavneOkno.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}