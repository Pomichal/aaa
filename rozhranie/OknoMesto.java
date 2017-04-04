package rozhranie;

import java.util.List;

import budovy.Budova;
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
	private Button Vylepsit = new Button("Postavit/Vylepsit");
	private Label bohatstvo = new Label("Peniaze:");
	private static TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);
	
	public TextArea getVypis(){
		return vypis;
	}

	
	public OknoMesto(List<Mesto> mesta,int index) {
		super();
		setTitle(mesta.get(index).toString());
		
		final SledovatelPenazi Peniaze;
		final ComboBox<Budova> cbbudovy = new ComboBox<Budova>(FXCollections.observableArrayList(mesta.get(index).getBudovy()));
		mesta.get(index).upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(mesta.get(index));
		mesta.get(index).pridajSledovatela(Peniaze);
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(cbbudovy);
		pane.getChildren().add(Vylepsit);
		pane.getChildren().add(vypis);
		pane.getChildren().add(skrolVypis);
		
		Vylepsit.setOnAction(e -> {
			Budova budova = cbbudovy.getValue();
			if(budova.getUroven()>=1)
			new OknoVylepsenie(mesta.get(index),budova,this);
			else
				new OknoPostav(mesta.get(index),budova,this);
		});
		
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}
