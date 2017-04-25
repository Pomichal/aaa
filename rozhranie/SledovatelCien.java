package rozhranie;

import hra.Sledovatel;
import javafx.scene.control.Tooltip;
import mesta.Mesto;

public class SledovatelCien implements Sledovatel{

	private Mesto mesto;
	private Tooltip tip;
	
	public SledovatelCien(Tooltip tip, Mesto mesto){
		this.mesto=mesto;
		this.tip=tip;
	}
	@Override
	public void upozorni() {
		tip.setText(mesto.getSklad().getCeny());
		
	}

}
