package budovy;

import mesta.*;

//import mesta.*;

public class Ovciar extends Tovaren {
	
	int udrzba; //cena za kolo

	public Ovciar(int uroven) {
		super(uroven);
	}
	public void vyroba(Bavlnovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>30){
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
		this.udrzba=30;
		mesto.znizPeniaze(udrzba);
		}
		else mesto.getSklad().vyhoditTovar();
	}

	public void vyroba(Drevovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>50){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			this.udrzba=50;
			mesto.znizPeniaze(udrzba);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Kamenovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>50){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			this.udrzba=50;
			mesto.znizPeniaze(udrzba);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Mramorovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>60){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			udrzba=60;
			mesto.znizPeniaze(udrzba);
			}
			else mesto.getSklad().vyhoditTovar();
		}

}
