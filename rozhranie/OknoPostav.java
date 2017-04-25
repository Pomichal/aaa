package rozhranie;

import budovy.*;
import hra.Turn;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.Mesto;

public class OknoPostav extends Stage{
	private Button Potvrdit = new Button("Potvrdit");
	private Button Zrusit = new Button("Zrusit");
	private TextArea vypis = new TextArea();
	private Label bohatstvo = new Label("Peniaze:");
	
	public OknoPostav(Mesto mesto, Budova budova, OknoMesto zdroj, Turn turn){
		
		setTitle("Postavit Budovu?");
		
		final SledovatelPenazi Peniaze = new SledovatelPenazi(mesto);
		turn.pridajSledovatela(Peniaze);
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(vypis);
		pane.getChildren().add(Potvrdit);
		pane.getChildren().add(Zrusit);
		
		turn.upozorniSledovatelov();
		
		vypis.appendText("Postavit budovu " + budova + "?\n" +
				     "Potrebny tovar: " + (50 + 20*budova.getUroven()) + "/ " + + (50 + 20*budova.getUroven()) + "/ " 
				     			+ (50 + 20*budova.getUroven()) + "/ " + (50 + 20*budova.getUroven()) + "\n" +
				     		"cena stavby: " + (300 + 300*budova.getUroven()) + budova.zistiVynos(mesto));
	
		Potvrdit.setOnAction(e -> {
			zdroj.getVypis().appendText(budova.zvysUroven(mesto));
			close();
		});
		
		Zrusit.setOnAction(e -> {
			close();
		});
	
		setScene(new Scene(pane, 500, 300));
		show();
}

}
