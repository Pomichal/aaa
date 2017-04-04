package rozhranie;

import mesta.*;

import java.util.LinkedList;

import hra.*;
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
	private Button turn = new Button("Turn");
	private Button vyprava = new Button("Poslat vypravu");
	
	private static TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);
	
	public static TextArea getVypis(){
		return vypis;
	}
	
	private Label[] Tovar1 ={new Label("Bavlna:"),new Label("Drevo:"),new Label("Kamen:"),new Label("Mramor:")};	
	private Label[] Tovar2 ={new Label("Bavlna:"),new Label("Drevo:"),new Label("Kamen:"),new Label("Mramor:")};	
	private Label[] Tovar3 ={new Label("Bavlna:"),new Label("Drevo:"),new Label("Kamen:"),new Label("Mramor:")};	
	private Label[] Tovar4 ={new Label("Bavlna:"),new Label("Drevo:"),new Label("Kamen:"),new Label("Mramor:")};	
	
	private Label startOzn = new Label("Start");
	private Label cielOzn = new Label("Ciel");
	private Label typTovaruOzn = new Label("TypTovaru");	
	private Label mnozstvoTovaruOzn = new Label("MnozstvoTovaru");
	private Label zamerOzn = new Label("Zamer vypravy: ");
	private Label bohatstvo = new Label("Peniaze:");
	private Label pocetkol = new Label("Kolo:");
	private TextField mnozstvo = new TextField();
	

	
	transient private LinkedList<Mesto> Mesta = new LinkedList<>(Turn.Nastav());
	
	final ComboBox<Mesto> cbstart = new ComboBox<Mesto>(FXCollections.observableArrayList(Mesta));
	final ComboBox<Mesto> cbciel = new ComboBox<Mesto>(FXCollections.observableArrayList(Mesta));
	final ComboBox<Integer> cbtyp = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3));
	final ComboBox<Integer> cbzamer = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2));
	
	private SledovatelPenazi Peniaze;
	private SledovatelKol Kolo;
	private SledovatelTovaru[] Bav = new SledovatelTovaru[4];
	private SledovatelTovaru[] Drev = new SledovatelTovaru[4];
	private SledovatelTovaru[] Kam = new SledovatelTovaru[4];
	private SledovatelTovaru[] Mram = new SledovatelTovaru[4];


	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);

		
		Kolo = new SledovatelKol();
		Mesta.get(0).pridajSledovatela(Kolo);
		pane.getChildren().add(pocetkol);
		Kolo.setPrefWidth(100);
		pane.getChildren().add(Kolo);
		pane.getChildren().add(skrolVypis);
		skrolVypis.setPrefHeight(100);

		pane.getChildren().add(BavlnovoTlacidlo);
		for(int i=0; i<4;i++){
			Bav[i]=new SledovatelTovaru(Mesta.get(0),i);
			Mesta.get(0).pridajSledovatela(Bav[i]);
			pane.getChildren().add(Tovar1[i]);
			pane.getChildren().add(Bav[i]);
			Bav[i].setPrefWidth(55);
		}
		
		pane.getChildren().add(DrevovoTlacidlo);
		for(int i=0; i<4;i++){
			Drev[i]=new SledovatelTovaru(Mesta.get(1),i);
			Mesta.get(0).pridajSledovatela(Drev[i]);
			pane.getChildren().add(Tovar2[i]);
			pane.getChildren().add(Drev[i]);
			Drev[i].setPrefWidth(55);
		}

		pane.getChildren().add(KamenovoTlacidlo);
		for(int i=0; i<4;i++){
			Kam[i]=new SledovatelTovaru(Mesta.get(2),i);
			Mesta.get(0).pridajSledovatela(Kam[i]);
			pane.getChildren().add(Tovar3[i]);
			pane.getChildren().add(Kam[i]);
			Kam[i].setPrefWidth(55);
		}
		

		pane.getChildren().add(MramorovoTlacidlo);
		for(int i=0; i<4;i++){
			Mram[i]=new SledovatelTovaru(Mesta.get(3),i);
			Mesta.get(0).pridajSledovatela(Mram[i]);
			pane.getChildren().add(Tovar4[i]);
			pane.getChildren().add(Mram[i]);
			Mram[i].setPrefWidth(60);
		}
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
							+ Turn.vypisVypravy(Mesta));
			}
		);
		
		vyprava.setOnAction(e -> {
			Mesto start=cbstart.getValue();
			Mesto ciel=cbciel.getValue();
			int typ=cbtyp.getValue();
			int mnoz =Integer.parseInt(mnozstvo.getText());
			int zamer=cbzamer.getValue();
			//if(zamer==2)vypis.appendText(start.getObchod().vyslatVypravu(start, ciel, typ, mnoz) + "\n");
			//else
			//vypis.appendText(start.getStajna().vyslatVypravu(start, ciel, typ, mnoz, zamer) + "\n");
			new OknoVyprava(start,ciel,typ,mnoz,zamer);
		});
		
		BavlnovoTlacidlo.setOnAction(e -> {
		new OknoMesto(Mesta,0);
		Mesta.get(0).upozorniSledovatelov();
		});
		
		DrevovoTlacidlo.setOnAction(e -> {
		new OknoMesto(Mesta,1);
		Mesta.get(0).upozorniSledovatelov();
		});
		
		KamenovoTlacidlo.setOnAction(e -> {
		new OknoMesto(Mesta,2);
		Mesta.get(0).upozorniSledovatelov();
		});
		
		MramorovoTlacidlo.setOnAction(e -> {
		new OknoMesto(Mesta,3);
		Mesta.get(0).upozorniSledovatelov();
		});
		
		
		Peniaze = new SledovatelPenazi(Mesta.get(0));
		Mesta.get(0).pridajSledovatela(Peniaze);;
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		
		hlavneOkno.setScene(new Scene(pane, 500, 400));
		hlavneOkno.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}