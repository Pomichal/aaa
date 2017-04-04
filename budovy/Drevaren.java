package budovy;

import mesta.Bavlnovo;
import mesta.Drevovo;
import mesta.Kamenovo;
import mesta.Mesto;
import mesta.Mramorovo;

public class Drevaren extends Tovaren {

	public Drevaren(int uroven) {
		super(uroven);
	}
	public int vyrobneCislo(Mesto mesto){
		return mesto.getOkolie(1);
	}

	public void vyroba(Bavlnovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(1) && mesto.getPeniaze()>50){
		mesto.getSklad().zvysTovar(1, mesto.getOkolie(0)*this.uroven);
		mesto.znizPeniaze(50);
		}
		else mesto.getSklad().vyhoditTovar();
	}

	public void vyroba(Drevovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(1) && mesto.getPeniaze()>30){
			mesto.getSklad().zvysTovar(1, mesto.getOkolie(1)*this.uroven);
			mesto.znizPeniaze(30);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Kamenovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(1) && mesto.getPeniaze()>60){
			mesto.getSklad().zvysTovar(1, mesto.getOkolie(1)*this.uroven);
			mesto.znizPeniaze(60);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Mramorovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(1) && mesto.getPeniaze()>40){
			mesto.getSklad().zvysTovar(1, mesto.getOkolie(1)*this.uroven);
			mesto.znizPeniaze(40);
			}
			else mesto.getSklad().vyhoditTovar();
		}


}
