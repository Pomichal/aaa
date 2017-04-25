package rozhranie;

import hra.Sledovatel;
import hra.Turn;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class SledovatelKol extends TextField implements Sledovatel{

	private int kolo;
	private Turn turn;
	
	public SledovatelKol(Turn turn){
		kolo=turn.getKolo();
		this.turn = turn;
	}
	
	public void upozorni() {
		kolo=turn.getKolo();
		Platform.runLater(() -> setText(Integer.toString(kolo)));
	}

}
