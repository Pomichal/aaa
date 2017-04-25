package budovy;

import java.util.LinkedList;
import java.util.List;

import hra.Vyprava;
import mesta.Mesto;
import vynimky.*;

public class Obchod extends Budova {

	private static final long serialVersionUID = 1L;
	private List<Vyprava> vypravy = new LinkedList<>();

	public Obchod(int uroven) {
		super(uroven);
	}
	
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
	
	public String vyslatVypravu(Mesto start, Mesto ciel, int typ, int mnozstvo) throws MojException{
			 				int dialka= start.getVzdialenost(ciel);
			 				if(start.getSklad().getTovar(typ)<mnozstvo) throw new MojException("Nedostatok tovaru");
			 				else if(mnozstvo*start.getVzdialenost(ciel)*start.getSklad().getCena(typ)>start.getPeniaze())
			 					throw new MojException("Nedostatok zlatych");
			 				else if(start==ciel) throw new MojException("Rovnaky start aj ciel");
			 				else{
			 						if(vypravy.size()<5){
			 						start.getSklad().znizTovar(typ, mnozstvo);
			 						Vyprava vyp = new Vyprava(start,ciel, typ, mnozstvo,0,0);
			 						vypravy.add(vyp);
			 						vyp.setPrichod(dialka);
			 						start.znizPeniaze(mnozstvo*dialka*start.getSklad().getCena(typ));
			 						return "obchodna vyprava vytvorena, Cena(zlato na jednotku/kolo): "+ mnozstvo*dialka*start.getSklad().getCena(typ);
			 						}
			 						else {
			 							throw new MojException("Nemas volny obchodny karavan!");
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
		return sprava;
	}
	
	

}
