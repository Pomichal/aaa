package rozhranie;

import hra.*;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import mesta.Mesto;

public class SledovatelTovaru extends TextField implements Sledovatel{

	private Mesto mesto;
	private int tovar;
	private int typ;
	
	public SledovatelTovaru(Mesto mesto, int typ){
		super();
		this.mesto=mesto;
		this.typ=typ;
	}

	@Override
	public void upozorni() {
		tovar=mesto.getSklad().getTovar(typ);
		Platform.runLater(() -> setText(Integer.toString(tovar)));		
	}

}
