package rozhranie;

import java.util.List;

import budovy.Budova;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.*;

public class OknoDrevovo extends Stage{
	
	private Button Vylepsit = new Button("Vylepsit");
	private Label bohatstvo = new Label("Peniaze:");
	private TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);

	
	public OknoDrevovo(List<Mesto> mesta) {
		super();
		setTitle("Drevovo");
		
		final SledovatelPenazi Peniaze;
		final ComboBox<Budova> cbbudovy = new ComboBox<Budova>(FXCollections.observableArrayList(mesta.get(1).getSklad(),mesta.get(1).getStajna(),mesta.get(1).getTovaren()));
		mesta.get(1).upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(mesta.get(1));
		mesta.get(1).pridajSledovatela(Peniaze);
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(cbbudovy);
		pane.getChildren().add(Vylepsit);
		pane.getChildren().add(vypis);
		pane.getChildren().add(skrolVypis);
		
		Vylepsit.setOnAction(e -> {
			Budova budova = cbbudovy.getValue();
			vypis.appendText(budova.zvysUroven(mesta.get(1)));
		});
		
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}
