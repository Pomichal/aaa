package budovy;

import hra.*;
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
			 				else if(mnozstvo*(4-this.uroven)*mesta[start].getVzdialenost(ciel)>mesta[start].getPeniaze())
			 					return("Nedostatok zlatych");
			 				else{
			 					while(j<this.vypravy.length && this.vypravy[j]!=null) j++;
			 						if(j<3){  //TREBA OPRAVIT PODLA UROVNE!
			 						mesta[start].znizTovar(typ, mnozstvo);
			 						this.vypravy[j]= new Vyprava(start,ciel, typ, mnozstvo,zdroje);
			 						this.vypravy[j].setPrichod(dialka/(this.uroven));
			 						mesta[start].znizPeniaze(mnozstvo*dialka*(4-this.uroven));  //odpocita naklady na cestu
			 						return "vyprava vytvorena, Cena(" + (4-this.uroven) + " zlato na jednotku/kolo): "+ (4-this.uroven)*dialka;
			 						//if(vypravy[j].overPrichod(mesta)) vypravy[j]=null;
			 						}
			 						else {
			 							return ("Nemas volny karavan!");
			 						}
			 				}
			 			} else 	return("Toto mesto neovladas");
			 		}
		return "chyba nacitania";
			 	}
	
	public String overPrichod(Mesto mesto, Mesto[] mesta){
		Vyprava[] vypravy = mesto.getStajna().getVypravy();
		int i,j;
		String sprava=""; 
		for(i=0;vypravy[i]!=null;i++)
			vypravy[i].znizPrichod();
		for(i=0;vypravy[i]!=null;i++){
	    	if(vypravy[i].getPrichod()==0){
				mesto.zvysPeniaze(mesta[vypravy[i].getCiel()].getCena(vypravy[i].getTyp()));
				sprava=sprava + "vyprava dorazila, prijem:" + mesta[vypravy[i].getCiel()].getCena(vypravy[i].getTyp()) + "zlatych\n";
				vypravy[i]=null;
				for(j=i+1;vypravy[j]!=null;j++)
					vypravy[j-1]=vypravy[j];
				vypravy[j-1]=null;
				i--;
			}else sprava=sprava+ "vyprava dorazi o " + vypravy[i].getPrichod() + "kol(o)\n";
		}
		return sprava;
	}
	
	
}
