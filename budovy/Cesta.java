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
	public Mesto getStart(){
		return this.start;
	}
	public Mesto getCiel(){
		return this.ciel;
	}
	public String zvysUroven(Mesto mesto){  //treba tovar z oboch miest na vylepsenie cesty
		if(uroven<3 && start.getSklad().getTovar(0)>=(50 + (20*uroven)) && start.getSklad().getTovar(1)>=(50 + (20*uroven)) && 
				start.getSklad().getTovar(2)>=(50 + (20*uroven)) && start.getSklad().getTovar(3)>=(50 + (20*uroven)) && start.getPeniaze()>=(300 + 300 * uroven)
				&& ciel.getSklad().getTovar(0)>=(50 + (20*uroven)) && ciel.getSklad().getTovar(1)>=(50 + (20*uroven)) && 
						ciel.getSklad().getTovar(2)>=(50 + (20*uroven)) && ciel.getSklad().getTovar(3)>=(50 + (20*uroven))
						&& ciel.getPeniaze()>=(300 + 300 * uroven)){
			start.znizPeniaze(300 + 300*uroven);
			ciel.znizPeniaze(300 + 300*uroven);
		for(int i=0; i<4;i++){
			start.getSklad().znizTovar(i, (50 + (20*uroven)));
			ciel.getSklad().znizTovar(i, (50 + (20*uroven)));
		}
		addStavba(this);
		start.upozorniSledovatelov();
		   return "Stavba cesty sa zacala, trvanie:" + 5*(uroven+1) + "\n";
		}
		else if(uroven<4) return "nedostatok zdrojov\n";
		else return "Cesta ma maximalnu uroven\n";
	}
	public String getOpis(Mesto mesto){
		String sprava = "";
		if(mesto==start)
		sprava = "do: " + ciel + ", uroven: " +this.getUroven() + "\n";
		else sprava = "Do: " + start + ", uroven: " +this.getUroven() + "\n";
		return sprava;
	}
}