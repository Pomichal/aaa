package budovy;

import java.util.*;
import hra.*;
import mesta.*;
import vynimky.*;

public class Stajna extends Budova {
	public Stajna(int uroven){
		   super(uroven);
	   }
	
	transient private List<Vyprava> vypravy = new LinkedList<>();
	
	public int getUroven(){
		return uroven;
	}
	
	public List<Vyprava> getVypravy(Mesto mesto){
		return mesto.getStajna().vypravy;
	}
	
	public void pridajVypravu(Vyprava vyp) {
		vypravy.add(vyp);
	}
	public void odoberVypravu(int index){
		vypravy.remove(index);
	}
	
	public String vyslatVypravu(Mesto start, Mesto ciel, int typ, int mnozstvo, int zamer) throws NedostatokException{
		if(this.getUroven()>0){
			 				int dialka= start.getCesta(ciel.getPoloha()).getDlzka();
			 				if(start.getSklad().getTovar(typ)<mnozstvo)
			 					throw new NedostatokException("nedostatok tovaru");
			 				else if(mnozstvo>this.uroven*100) 
			 					throw new NedostatokException("prekrocena nosnost (max :" + this.uroven*100 + ")");
			 				else if(mnozstvo*(4-this.uroven)*start.getVzdialenost(ciel)>start.getPeniaze())
			 					throw new NedostatokException("nedostatok penazi");
			 				else if(start==ciel) throw new NedostatokException("Rovnaky start aj ciel");
			 				else{
			 						if(vypravy.size()<2*this.uroven){
			 						start.getSklad().znizTovar(typ, mnozstvo);
			 						Vyprava vyp = new Vyprava(start,ciel, typ, mnozstvo,0, zamer);
			 						vypravy.add(vyp);
			 						vyp.setPrichod(dialka/start.getCesta(ciel.getPoloha()).getUroven());
			 						start.znizPeniaze(mnozstvo*dialka*(4-this.uroven));
			 						start.upozorniSledovatelov();
			 						return "vyprava vytvorena, Cena(" + (4-this.uroven) + " zlato na jednotku/kolo): "+ (4-this.uroven)*dialka;
			 						}
			 						else {
			 						  throw new NedostatokException("Nemas volny karavan!");
			 						}
			 				}
		}else
		throw new NedostatokException("V tomto meste nemas stajnu");
	}
	public String overPrichod(Mesto mesto){
		int i;
		String sprava=""; 
		for(Vyprava vyprava : vypravy)
			vyprava.znizPrichod();
		for(i=0;i<vypravy.size();i++){
			Vyprava vyprava=vypravy.get(i);
	    	if(vyprava.getPrichod()==0 && vyprava.getMnozstvo()!=0){
				sprava = sprava + "vyprava (" + vypravy.indexOf(vyprava) + ") dorazila, predany tovar: " + vyprava.getMnozstvo() + " kus(ov)\n";
				if(vyprava.getZamer()==0){
					vyprava.getCiel().getSklad().prehodnotCeny(vyprava);
				vyprava.setZdroje(vyprava.getCiel().getSklad().getCena(vyprava.getTyp())*vyprava.getMnozstvo());
				vyprava.setMnozstvo(0);
				vyprava.getCiel().getSklad().prehodnotCeny(vyprava);
				} else{
					vyprava.getCiel().getSklad().zvysTovar(vyprava.getTyp(), vyprava.getMnozstvo());
					vyprava.setMnozstvo(0);
				}
				vyprava.setPrichod((vyprava.getCiel().getVzdialenost(vyprava.getStart()))/(this.uroven));
	    	} else if(vyprava.getPrichod()==0 && vyprava.getMnozstvo()==0){
				sprava=sprava + "vyprava sa vratila, prijem: " + vyprava.getZdroje() + " zlatych\n";
				mesto.zvysPeniaze(vyprava.getZdroje());
				odoberVypravu(vypravy.indexOf(vyprava));
				i--;
			}
		}
		mesto.upozorniSledovatelov();
		return sprava;
	}
	
	
}
