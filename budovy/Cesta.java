package budovy;

import mesta.*;

public class Cesta extends Budova {
	Mesto start,ciel;
	int dlzka;
	public Cesta(int uroven){
		super(uroven);
	}
	public Cesta(int uroven, Mesto start, Mesto ciel){
		super(uroven);
		this.start=start;
		this.ciel=ciel;
		dlzka=start.getVzdialenost(ciel.getPoloha()) - 2*(uroven-1);
	}
	
	public int getDlzka(){
		return this.dlzka;
	}
	public String getOpis(Mesto mesto){
		String sprava = "";
		if(mesto==start)
		sprava = "Do: " + ciel + ", uroven: " +this.getUroven() + "\n";
		else sprava = "Do: " + start + ", uroven: " +this.getUroven() + "\n";
		return sprava;
	}
}