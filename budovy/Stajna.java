package budovy;

import hra.Vyprava;
import mesta.*;

public class Stajna extends Budova {
	public Stajna(int uroven){
		   super(uroven);
	   }
	
	private Vyprava[] vypravy= new Vyprava[uroven*uroven];
	
	public Vyprava[] getVypravy() {
		return vypravy;
	}

	public void setVypravy(int i, Vyprava vypravy) {
		this.vypravy[i] = vypravy;
	}
	
	public void vyslatVypravu(Mesto[] mesta, int start, int ciel, int typ, int mnozstvo, int zdroje, int uloha){
		int j=0;
		Vyprava vyp;
		if(start<3){
			 			if(mesta[start].getMoje()){
			 				int dialka= mesta[start].getVzdialenost(ciel);
			 				System.out.println("Vzdialenost (1 zlato na jednotku/kolo): "+ dialka);
			 				if(mesta[start].getTovar(typ)<mnozstvo) System.out.println("Nedostatok tovaru");
			 				else if(mnozstvo*mesta[start].getVzdialenost(ciel)>mesta[start].getPeniaze())
			 					System.out.println("Nedostatok zlatych");
			 				else{
			 					while(j<15 && vypravy[j]!=null) j++;
			 						if(j<15){
			 						mesta[start].znizTovar(typ, mnozstvo);
			 						vyp=new Vyprava(start,ciel, typ, mnozstvo,zdroje,uloha);
			 						setVypravy(j,vyp);
			 						//if(vypravy[j].overPrichod(mesta)) vypravy[j]=null;
			 						//znizPeniaze(mesta[start].getVzdialenost(ciel)*mnozstvo);   //odpocita naklady na cestu
			 						}
			 						else {
			 							System.out.println("Nemas volny karavan!");
			 							return;
			 						}
			 				}
			 			} else 	System.out.println("Toto mesto neovladas");
			 		}
			 	}
	
	
}
