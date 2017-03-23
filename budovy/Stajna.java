package budovy;

import java.util.*;
import hra.*;
import mesta.*;

public class Stajna extends Budova {
	public Stajna(int uroven){
		   super(uroven);
	   }
	
	transient private List<Vyprava> vypravy = new LinkedList<>();
	
	public int getUroven(){
		return uroven;
	}
	
	//private Vyprava[] vypravy= new Vyprava[3];  //treba zvysit, pri zvyseni urovne!!
	
	public void pridajVypravu(Vyprava vyp) {
		vypravy.add(vyp);
	}
	public void odoberVypravu(int index){
		vypravy.remove(index);
	}
	
	public String vyslatVypravu(Mesto[] mesta, int start, int ciel, int typ, int mnozstvo, int zdroje){
		//int j=0;
		if(this.getUroven()>0 && start<3){
			 			if(mesta[start].getMoje()){
			 				int dialka= mesta[start].getVzdialenost(ciel);
			 			//	System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
			 				if(mesta[start].getSklad().getTovar(typ)<mnozstvo) return ("Nedostatok tovaru");
			 				else if(mnozstvo*(4-this.uroven)*mesta[start].getVzdialenost(ciel)>mesta[start].getPeniaze())
			 					return("Nedostatok zlatych");
			 				else if(start==ciel) return "Rovnaky start aj ciel";
			 				else{
			 					//while(j<this.vypravy.length && this.vypravy[j]!=null) j++;
			 						if(vypravy.size()<2*this.uroven){  //TREBA OPRAVIT PODLA UROVNE!
			 						mesta[start].getSklad().znizTovar(typ, mnozstvo);
			 						Vyprava vyp = new Vyprava(start,ciel, typ, mnozstvo,zdroje);
			 						vypravy.add(vyp);
			 						vyp.setPrichod(dialka/(this.uroven));
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
		int i;
		String sprava=""; 
		for(Vyprava vyprava : vypravy)
			vyprava.znizPrichod();
		for(i=0;i<vypravy.size();i++){
			Vyprava vyprava=vypravy.get(i);
	    	if(vyprava.getPrichod()==0 && vyprava.getMnozstvo()!=0){
				//mesto.zvysPeniaze(mesta[vypravy[i].getCiel()].getCena(vypravy[i].getTyp())*vypravy[i].getMnozstvo());
				sprava = sprava + "vyprava dorazila, predany tovar: " + vyprava.getMnozstvo() + "kus(ov)\n";
				vyprava.setZdroje(mesta[vyprava.getCiel()].getSklad().getCena(vyprava.getTyp())*vyprava.getMnozstvo());
				vyprava.setMnozstvo(0);
				vyprava.setPrichod((mesta[vyprava.getCiel()].getVzdialenost(vyprava.getStart()))/(this.uroven));
	    	} else if(vyprava.getPrichod()==0 && vyprava.getZdroje()!=0){
				sprava=sprava + "vyprava dorazila, prijem:" + mesta[vyprava.getCiel()].getSklad().getCena(vyprava.getTyp()*vyprava.getMnozstvo()) + "zlatych\n";
				mesto.zvysPeniaze(vyprava.getZdroje());
				odoberVypravu(vypravy.indexOf(vyprava));
				i--;
			}else if(vyprava.getMnozstvo()!=0) sprava=sprava+ "vyprava dorazi o " + vyprava.getPrichod() + "kol(o)\n";
			else sprava= sprava + "vyprava sa vrati o " + vyprava.getPrichod() + "kol(o)\n";
		}
		mesta[0].upozorniSledovatelov();
		return sprava;
	}
	
	
}
