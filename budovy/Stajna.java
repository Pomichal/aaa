package budovy;

import hra.*;
import mesta.*;

public class Stajna extends Budova {
	public Stajna(int uroven){
		   super(uroven);
	   }
	
	public int getUroven(){
		return uroven;
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
		if(this.getUroven()>0 && start<3){
			 			if(mesta[start].getMoje()){
			 				int dialka= mesta[start].getVzdialenost(ciel);
			 			//	System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
			 				if(mesta[start].getSklad().getTovar(typ)<mnozstvo) return ("Nedostatok tovaru");
			 				else if(mnozstvo*(4-this.uroven)*mesta[start].getVzdialenost(ciel)>mesta[start].getPeniaze())
			 					return("Nedostatok zlatych");
			 				else if(start==ciel) return "Rovnaky start aj ciel";
			 				else{
			 					while(j<this.vypravy.length && this.vypravy[j]!=null) j++;
			 						if(j<3){  //TREBA OPRAVIT PODLA UROVNE!
			 						mesta[start].getSklad().znizTovar(typ, mnozstvo);
			 						this.vypravy[j]= new Vyprava(start,ciel, typ, mnozstvo,zdroje);
			 						this.vypravy[j].setPrichod(dialka/(this.uroven));
			 						mesta[start].znizPeniaze(mnozstvo*dialka*(4-this.uroven));  //odpocita naklady na cestu
			 						mesta[0].upozorniSledovatelov();
			 						return "vyprava vytvorena, Cena(" + (4-this.uroven) + " zlato na jednotku/kolo): "+ (4-this.uroven)*dialka;
			 						//if(vypravy[j].overPrichod(mesta)) vypravy[j]=null;
			 						}
			 						else {
			 							return ("Nemas volny karavan!");
			 						}
			 				}
			 			} else 	return("Toto mesto neovladas");
			 		}
		return "V tomto meste nemas stajnu";
			 	}
	
	public String overPrichod(Mesto mesto, Mesto[] mesta){
		Vyprava[] vypravy = mesto.getStajna().getVypravy();
		int i,j;
		String sprava=""; 
		for(i=0;i<3 && vypravy[i]!=null;i++)
			vypravy[i].znizPrichod();
		for(i=0;i<3 && vypravy[i]!=null;i++){
	    	if(vypravy[i].getPrichod()==0 && vypravy[i].getMnozstvo()!=0){
				//mesto.zvysPeniaze(mesta[vypravy[i].getCiel()].getCena(vypravy[i].getTyp())*vypravy[i].getMnozstvo());
				sprava = sprava + "vyprava dorazila, predany tovar: " + vypravy[i].getMnozstvo() + "kus(ov)\n";
				vypravy[i].setZdroje(mesta[vypravy[i].getCiel()].getSklad().getCena(vypravy[i].getTyp())*vypravy[i].getMnozstvo());
				vypravy[i].setMnozstvo(0);
				vypravy[i].setPrichod((mesta[vypravy[i].getCiel()].getVzdialenost(vypravy[i].getStart()))/(this.uroven));
	    	} else if(vypravy[i].getPrichod()==0 && vypravy[i].getZdroje()!=0){
				sprava=sprava + "vyprava dorazila, prijem:" + mesta[vypravy[i].getCiel()].getSklad().getCena(vypravy[i].getTyp()*vypravy[i].getMnozstvo()) + "zlatych\n";
				mesto.zvysPeniaze(vypravy[i].getZdroje());
				vypravy[i]=null;
				for(j=i+1;j<3 && vypravy[j]!=null;j++)
					vypravy[j-1]=vypravy[j];
				vypravy[j-1]=null;
				i--;
			}else if(vypravy[i].getMnozstvo()!=0) sprava=sprava+ "vyprava dorazi o " + vypravy[i].getPrichod() + "kol(o)\n";
			else sprava= sprava + "vyprava sa vrati o " + vypravy[i].getPrichod() + "kol(o)\n";
		}
		mesta[0].upozorniSledovatelov();
		return sprava;
	}
	
	
}
