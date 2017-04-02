package budovy;

import mesta.*;

//import mesta.*;

public class Ovciar extends Tovaren {
	
	int udrzba; //cena za kolo

	public Ovciar(int uroven) {
		super(uroven);
	}
	
	public void vyroba(Bavlnovo mesto) {
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0));
		udrzba=30;
	}

	public void vyroba(Drevovo mesto) {
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0));	
		udrzba=50;
	}

	@Override
	public void vyroba(Kamenovo mesto) {
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0));
		udrzba=50;
	}

	@Override
	public void vyroba(Mramorovo mesto) {
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0));
		udrzba=60;
	}

}
