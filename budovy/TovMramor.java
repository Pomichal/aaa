package budovy;

import mesta.Bavlnovo;
import mesta.Drevovo;
import mesta.Kamenovo;
import mesta.Mesto;
import mesta.Mramorovo;

public class TovMramor extends Tovaren{

	public TovMramor(int uroven) {
		super(uroven);
	}
	public int vyrobneCislo(Mesto mesto){
		return mesto.getOkolie(3);
	}
	public void vyroba(Bavlnovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>60){
		mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
		mesto.znizPeniaze(60);
		}
		else mesto.getSklad().vyhoditTovar();
	}

	public void vyroba(Drevovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>50){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(50);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Kamenovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>45){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(45);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Mramorovo mesto) {
		if(mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>30){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(30);
			}
			else mesto.getSklad().vyhoditTovar();
		}


}
