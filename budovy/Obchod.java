package budovy;

import java.util.LinkedList;
import java.util.List;

import hra.Vyprava;
import mesta.Mesto;

public class Obchod extends Budova {

	public Obchod(int uroven) {
		super(uroven);
	}
	
transient private List<Vyprava> vypravy = new LinkedList<>();
	
	public int getUroven(){
		return uroven;
	}
	
	public List<Vyprava> getVypravy(Mesto mesto){
		return mesto.getObchod().vypravy;
	}
	public void pridajVypravu(Vyprava vyp) {
		vypravy.add(vyp);
	}
	public void odoberVypravu(int index){
		vypravy.remove(index);
	}
	
	public String vyslatVypravu(Mesto start, Mesto ciel, int typ, int mnozstvo){
			 				int dialka= start.getVzdialenost(ciel);
			 				if(start.getSklad().getTovar(typ)<mnozstvo) return ("Nedostatok tovaru");
			 				else if(mnozstvo*start.getVzdialenost(ciel)*start.getSklad().getCena(typ)>start.getPeniaze())
			 					return("Nedostatok zlatych");
			 				else if(start==ciel) return "Rovnaky start aj ciel";
			 				else{
			 						if(vypravy.size()<5){
			 						start.getSklad().znizTovar(typ, mnozstvo);
			 						Vyprava vyp = new Vyprava(start,ciel, typ, mnozstvo,0,0);
			 						vypravy.add(vyp);
			 						vyp.setPrichod(dialka);
			 						start.znizPeniaze(mnozstvo*dialka*start.getSklad().getCena(typ));
			 						start.upozorniSledovatelov();
			 						return "obchodna vyprava vytvorena, Cena(zlato na jednotku/kolo): "+ mnozstvo*dialka*start.getSklad().getCena(typ);
			 						}
			 						else {
			 						 return ("Nemas volny obchodny karavan!");
			 						}
			 				}
	}
	
	public String overPrichod(Mesto mesto){
		int i;
		String sprava=""; 
		for(Vyprava vyprava : vypravy)
			vyprava.znizPrichod();
		for(i=0;i<vypravy.size();i++){
			Vyprava vyprava=vypravy.get(i);
	    	if(vyprava.getPrichod()==0){
				sprava = sprava + "obchodna vyprava dorazila, ziskany tovar: " + vyprava.getMnozstvo() + " kus(ov)\n";
				vyprava.getCiel().getSklad().zvysTovar(vyprava.getTyp(), vyprava.getMnozstvo());
				odoberVypravu(vypravy.indexOf(vyprava));
				i--;
				}
		}
		mesto.upozorniSledovatelov();
		return sprava;
	}
	
	

}
