package rozhranie;


import javafx.scene.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.*;

public class OknoKamenovo extends Stage {

	private SledovatelPenazi Peniaze;


	public OknoKamenovo(Mesto[] Mesta) {
		setTitle("Drevovo");
		Mesta[0].upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(Mesta[0]);
		Mesta[0].pridajSledovatela(Peniaze);;
		pane.getChildren().add(Peniaze);
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}