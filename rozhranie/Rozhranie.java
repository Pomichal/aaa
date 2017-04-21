package rozhranie;

import mesta.*;

import java.util.LinkedList;

import hra.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
	
	private Label startOzn = new Label("Start:");
	private Label cielOzn = new Label("Ciel:");
	private Label typTovaruOzn = new Label("TypTovaru:");	
	private Label mnozstvoTovaruOzn = new Label("MnozstvoTovaru:");
	private Label zamerOzn = new Label("Zamer vypravy: ");
	private Label bohatstvo = new Label("Peniaze:");
	private Label pocetkol = new Label("Kolo:");
	private Label vyslat = new Label("Poslat vypravu:");
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
		GridPane pane = new GridPane();
		
		pane.setMinSize(500, 400);
		pane.setPadding(new Insets(10,10,10,10));
		pane.setVgap(5);
		pane.setHgap(5);
		pane.setAlignment(Pos.TOP_CENTER);
		
		pane.add(turn,0,0);

		
		Kolo = new SledovatelKol();
		Mesta.get(0).pridajSledovatela(Kolo);
		pane.add(pocetkol,1,0);
		Kolo.setPrefWidth(50);
		pane.add(Kolo,2,0);
		pane.add(skrolVypis,0,1,9,1);
		skrolVypis.setPrefHeight(150);

		pane.add(BavlnovoTlacidlo,0,3);
		for(int i=0; i<4;i++){
			Bav[i]=new SledovatelTovaru(Mesta.get(0),i);
			Bav[i].setPrefWidth(50);
			Mesta.get(0).pridajSledovatela(Bav[i]);
			pane.add(Tovar1[i],i*2+1,3);
			pane.add(Bav[i],2+i*2,3);
		}
		
		pane.add(DrevovoTlacidlo,0,4);
		for(int i=0; i<4;i++){
			Drev[i]=new SledovatelTovaru(Mesta.get(1),i);
			Mesta.get(0).pridajSledovatela(Drev[i]);
			pane.add(Tovar2[i],i*2+1,4);
			pane.add(Drev[i],2+i*2,4);
			Drev[i].setPrefWidth(50);
		}

		pane.add(KamenovoTlacidlo,0,5);
		for(int i=0; i<4;i++){
			Kam[i]=new SledovatelTovaru(Mesta.get(2),i);
			Mesta.get(0).pridajSledovatela(Kam[i]);
			pane.add(Tovar3[i],i*2+1,5);
			pane.add(Kam[i],i*2+2,5);
			Kam[i].setPrefWidth(50);
		}
		

		pane.add(MramorovoTlacidlo,0,6);
		for(int i=0; i<4;i++){
			Mram[i]=new SledovatelTovaru(Mesta.get(3),i);
			Mesta.get(0).pridajSledovatela(Mram[i]);
			pane.add(Tovar4[i],i*2+1,6);
			pane.add(Mram[i],i*2+2,6);
			Mram[i].setPrefWidth(50);
		}
		
		pane.add(vyslat, 0, 8);
		pane.add(startOzn,0,9);
		pane.add(cbstart,1,9,3,1);
//		cbstart.setPrefWidth(50);
		pane.add(cielOzn,0,10);
		pane.add(cbciel,1,10,3,1);
//		cbciel.setPrefWidth(50);
		pane.add(typTovaruOzn,0,11);
		pane.add(cbtyp,1,11);
		pane.add(mnozstvoTovaruOzn,5,9,2,1);
		pane.add(mnozstvo,7,9);
		mnozstvo.setPrefColumnCount(1);
		pane.add(zamerOzn,5,10,2,1);
		pane.add(cbzamer,7,10);
		pane.add(vyprava,0,12);
		
		
		turn.setText("Zacat hru");
		
		turn.setOnAction(e -> { 
			vypis.clear();
			turn.setText("Nove kolo");
			Turn.zvysKolo();
			vypis.appendText(Turn.Kolo(Mesta)
							+ Turn.vypisVypravy(Mesta));
			}
		);
		
		vyprava.setOnAction(e -> {
			try{
			Mesto start=cbstart.getValue();
			Mesto ciel=cbciel.getValue();
			int typ=cbtyp.getValue();
			int mnoz =Integer.parseInt(mnozstvo.getText());
			int zamer=cbzamer.getValue();
			new OknoVyprava(start,ciel,typ,mnoz,zamer);
			} catch (NullPointerException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Chybajuci udaj");
				a.showAndWait(); 	
			}catch (NumberFormatException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Chybajuci udaj");
				a.showAndWait(); 	
			}catch (ArithmeticException ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("V tomto meste nemas stajnu");
				a.showAndWait(); 	
			}
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
		pane.add(bohatstvo,0,7);
		Peniaze.setPrefWidth(50);
		pane.add(Peniaze,1,7);
		
		
		hlavneOkno.setScene(new Scene(pane, 550, 600));
		hlavneOkno.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}