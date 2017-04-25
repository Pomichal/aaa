package budovy;

import hra.Vyprava;
import mesta.*;
import vynimky.MojException;

public class Cesta extends Budova {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Mesto start,ciel;
	int dlzka;
	int riziko;
	public Cesta(int uroven){
		super(uroven);
		riziko = 95 + uroven;
	}
	public Cesta(int uroven, Mesto start, Mesto ciel){
		super(uroven);
		this.start=start;
		this.ciel=ciel;
		dlzka=start.getVzdialenost(ciel.getPoloha()) - 2*(uroven-1);
		riziko = 95 + uroven;
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
//		start.upozorniSledovatelov();
		   return "Stavba cesty sa zacala, trvanie:" + 5*(uroven+1) + "\n";
		}
		else if(uroven<4) return "nedostatok zdrojov\n";
		else return "Cesta ma maximalnu uroven\n";
	}
	
	public void zvysUroven(){
		super.zvysUroven();
		this.riziko = 65 + this.uroven*10;
		this.dlzka=start.getVzdialenost(ciel.getPoloha()) - 2*(uroven-1);
	}
	
	
	public void overRiziko(Vyprava vyprava) throws MojException{
		int nahoda1 = (int) (Math.random()*(100));
		if(nahoda1<(100-this.riziko) && vyprava.getPrichod()>3 && vyprava.getPrichod()<8)
			throw new MojException(vyprava,nahoda1);
	}
	
	public String getOpis(Mesto mesto){
		String sprava = "";
		if(mesto==start)
		sprava = "do: " + ciel + ", uroven: " +this.getUroven() + ", trvanie cesty: " + this.dlzka + ", rizikovost: " + (100-this.riziko) + "%\n";
		else sprava = "Do: " + start + ", uroven: " +this.getUroven() + ", trvanie cesty: " + this.dlzka + ", rizikovost: " + (100-this.riziko) + "%\n";
		return sprava;
	}
}