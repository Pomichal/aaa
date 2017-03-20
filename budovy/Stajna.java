package budovy;

import hra.Vyprava;
import mesta.*;

public class Stajna extends Budova {
	public Stajna(int uroven){
		   super(uroven);
	   }
	
	private Vyprava[] vypravy= new Vyprava[3];  //treba zvysit, pri zvyseni urovne!!
	
	public Vyprava[] getVypravy() {
		return vypravy;
	}

	public void setVypravy(int i, Vyprava vyprava) {
		this.vypravy[i] = vyprava;
	}
	
	public String vyslatVypravu(Mesto[] mesta, int start, int ciel, int typ, int mnozstvo, int zdroje){
		int j=0;
		//Vyprava vyp;
		if(start<3){
			 			if(mesta[start].getMoje()){
			 				int dialka= mesta[start].getVzdialenost(ciel);
			 			//	System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
			 				if(mesta[start].getTovar(typ)<mnozstvo) return ("Nedostatok tovaru");
			 				else if(mnozstvo*mesta[start].getVzdialenost(ciel)>mesta[start].getPeniaze())
			 					return("Nedostatok zlatych");
			 				else{
			 					while(j<this.vypravy.length && this.vypravy[j]!=null) j++;
			 						if(j<3){  //TREBA OPRAVIT PODLA UROVNE!
			 						mesta[start].znizTovar(typ, mnozstvo);
			 						this.vypravy[j]= new Vyprava(start,ciel, typ, mnozstvo,zdroje);
			 						return "vyprava vytvorena, Trvanie cesty(1 zlato na jednotku/kolo): "+ dialka;
			 						//if(vypravy[j].overPrichod(mesta)) vypravy[j]=null;
			 						//znizPeniaze(mesta[start].getVzdialenost(ciel)*mnozstvo);   //odpocita naklady na cestu
			 						}
			 						else {
			 							return ("Nemas volny karavan!");
			 						}
			 				}
			 			} else 	return("Toto mesto neovladas");
			 		}
		return "chyba nacitania";
			 	}
	
	
}
