package rozhranie;

import java.util.List;

import budovy.Budova;
import hra.Vyprava;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.Mesto;

public class OknoMesto extends Stage{
	private Button ZastavVyrobu = new Button("Zastav vyrobu");
	private Button Vylepsit = new Button("Postavit/Vylepsit");
	private Label bohatstvo = new Label("Peniaze:");
	private Label budovy = new Label("budovy:");
	private TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);
	private TextArea vypisbud = new TextArea();
	private ScrollPane skrolVypisbud = new ScrollPane(vypisbud);
	private Label vypravy = new Label("vypravy:");
	private TextArea vypisvyp = new TextArea();
	private ScrollPane skrolVypisvyp = new ScrollPane(vypisvyp);
	
	public TextArea getVypis(){
		return vypis;
	}

	
	public OknoMesto(List<Mesto> mesta,int index) {
		super();
		setTitle(mesta.get(index).toString());
		
		vypis.clear();
		final SledovatelPenazi Peniaze;
		final ComboBox<Budova> cbbudovy = new ComboBox<Budova>(FXCollections.observableArrayList(mesta.get(index).getBudovy()));
		mesta.get(index).upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(mesta.get(index));
		mesta.get(index).pridajSledovatela(Peniaze);
		Peniaze.setPrefWidth(45);
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(cbbudovy);
		pane.getChildren().add(Vylepsit);
		pane.getChildren().add(vypis);
		pane.getChildren().add(skrolVypis);
		pane.getChildren().add(budovy);
		pane.getChildren().add(vypisbud);
		pane.getChildren().add(skrolVypisbud);
		if(mesta.get(index).getStajna().getUroven()>0){
			pane.getChildren().add(vypravy);
			pane.getChildren().add(vypisvyp);
			vypisvyp.setPrefHeight(100);
			pane.getChildren().add(skrolVypisvyp);
			String sprava = "";
			for(Vyprava vyprava:mesta.get(index).getStajna().getVypravy(mesta.get(index))){
				sprava=sprava + ". Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ",\n\tmnozstvo: " + vyprava.getMnozstvo() 
						+ " prichod o: " + vyprava.getPrichod() + " kol(o)\n";
			}
			vypisvyp.appendText(sprava);
		}
		pane.getChildren().add(ZastavVyrobu);
		
		vypis.setPrefHeight(50);
		vypisbud.setPrefHeight(100);
		vypisbud.appendText(mesta.get(index).getBudova());
		
		if(mesta.get(index).getMoje()) ZastavVyrobu.setText("Zastav vyrobu");
		else ZastavVyrobu.setText("Spusti vyrobu");
		
		Vylepsit.setOnAction(e -> {
			Budova budova = cbbudovy.getValue();
			if(budova.getUroven()>=1)
			new OknoVylepsenie(mesta.get(index),budova,this);
			else
				new OknoPostav(mesta.get(index),budova,this);
		});
		
		ZastavVyrobu.setOnAction(e -> {
			mesta.get(index).setMoje(!mesta.get(index).getMoje());
			if(mesta.get(index).getMoje()) ZastavVyrobu.setText("Zastav vyrobu");
			else ZastavVyrobu.setText("Spusti vyrobu");
		});
		
		
		setScene(new Scene(pane, 500, 370));
		show();
	}
}

