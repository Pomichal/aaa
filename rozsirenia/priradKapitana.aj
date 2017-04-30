package rozsirenia;


import budovy.Krcma.Kapitan;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import rozhranie.*;

public privileged aspect priradKapitana {
	
	OknoVyprava okno;
	
	private ComboBox<Kapitan> OknoVyprava.kapitani = new ComboBox<>();
	
	after() returning(OknoVyprava o): call(OknoVyprava.new(..)) {
		okno = o;
		Platform.runLater(new Runnable(){
			public void run() {
				((FlowPane) o.getScene().getRoot()).getChildren().add(o.kapitani);
			}
		});
	}
}
