package budovy;

import mesta.Bavlnovo;
import mesta.Drevovo;
import mesta.Kamenovo;
import mesta.Mesto;
import mesta.Mramorovo;

public class TovMramor extends Tovaren{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TovMramor(int uroven) {
		super(uroven);
	}
	public int vyrobneCislo(Mesto mesto){
		return mesto.getOkolie(3);
	}
	public void vyroba(Bavlnovo mesto) {
		int cena=30 + 2*mesto.getVzdialenost(3);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>cena){
		mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
		mesto.znizPeniaze(cena);
		}
		else mesto.getSklad().vyhoditTovar();
	}

	public void vyroba(Drevovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(3);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Kamenovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(3);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Mramorovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(3);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(3) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(3, mesto.getOkolie(3)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}
	public String zistiVynos(Mesto mesto){
		return "\ncena za kolo: " + (30 + 2*mesto.getVzdialenost(3)) +
				"\nVyroba za kolo: " + mesto.getOkolie(3)*(uroven+1);
	}

}
