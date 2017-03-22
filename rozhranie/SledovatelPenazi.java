package rozhranie;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import mesta.Mesto;
import hra.Sledovatel;

public class SledovatelPenazi extends TextField implements Sledovatel{

	private int peniaze;
	private Mesto mesto;
	
	public SledovatelPenazi(Mesto mesto){
		super();
		this.mesto=mesto;
	}
	
	@Override
	public void upozorni() {
		peniaze=mesto.getPeniaze();
		Platform.runLater(() -> setText(Integer.toString(peniaze)));
	}

}
