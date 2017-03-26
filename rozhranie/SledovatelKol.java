package rozhranie;

import hra.Sledovatel;
import hra.Turn;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class SledovatelKol extends TextField implements Sledovatel{

	private int kolo;
	
	public void upozorni() {
		kolo=Turn.getKolo();
		Platform.runLater(() -> setText(Integer.toString(kolo)));
	}

}
