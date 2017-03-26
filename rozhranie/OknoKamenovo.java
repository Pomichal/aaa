package rozhranie;


import budovy.Budova;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.*;

public class OknoKamenovo extends Stage {

	private Button Vylepsit = new Button("Vylepsit");
	private Label bohatstvo = new Label("Peniaze:");
	
	public OknoKamenovo(Mesto[] Mesta) {
		super();
		setTitle("Kamenovo");
		
		final SledovatelPenazi Peniaze;
		final ComboBox<Budova> cbbudovy = new ComboBox<Budova>(FXCollections.observableArrayList(Mesta[2].getSklad(),Mesta[2].getStajna(),Mesta[2].getTovaren()));
		Mesta[0].upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(Mesta[0]);
		Mesta[0].pridajSledovatela(Peniaze);
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(cbbudovy);
		pane.getChildren().add(Vylepsit);
		
		Vylepsit.setOnAction(e -> {
			Budova budova = cbbudovy.getValue();
			budova.zvysUroven(Mesta[2]);
		});
		
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}