package rozhranie;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import mesta.*;

public class OknoBavlnovo extends Stage {
	
	private SledovatelPenazi Peniaze;


	public OknoBavlnovo(Mesto[] Mesta) {
		setTitle("Bavlnovo");
		Mesta[0].upozorniSledovatelov();
		FlowPane pane = new FlowPane();
		
		Peniaze = new SledovatelPenazi(Mesta[0]);
		Mesta[0].pridajSledovatela(Peniaze);;
		pane.getChildren().add(Peniaze);
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}
