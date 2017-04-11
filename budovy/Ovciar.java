package budovy;

import mesta.*;

//import mesta.*;

public class Ovciar extends Tovaren {

	public Ovciar(int uroven) {
		super(uroven);
	}
	public void vyroba(Bavlnovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(0);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>cena){
		mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
		mesto.znizPeniaze(cena);
		}
		else mesto.getSklad().vyhoditTovar();
	}

	public void vyroba(Drevovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(0);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Kamenovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(0);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}

	public void vyroba(Mramorovo mesto) {
		int cena = 30 + 2*mesto.getVzdialenost(0);
		if(this.uroven>0 && mesto.getSklad().getVolneMiesto()>mesto.getOkolie(0) && mesto.getPeniaze()>cena){
			mesto.getSklad().zvysTovar(0, mesto.getOkolie(0)*this.uroven);
			mesto.znizPeniaze(cena);
			}
			else mesto.getSklad().vyhoditTovar();
		}
	
	public String zistiVynos(Mesto mesto){
		return "\ncena za kolo: " + (30 + 2*mesto.getVzdialenost(0)) +
				"\nVyroba za kolo: " + mesto.getOkolie(0)*(this.uroven+1);
	}

}
