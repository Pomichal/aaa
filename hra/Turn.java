package hra;

import java.util.LinkedList;
import java.util.List;

import mesta.*;

public class Turn {
	
private static int kolo=0;
	
	/*public static String vypis(Mesto[] Mesta){ //vypis tovaru pre kontrolu
		int i,j;
		String[] mena={"Bavlnovo", "Drevovo", "Kamenovo", "Mramorovo"};
		String sprava="";
		for(i=0;i<4;i++){  
			sprava= sprava + mena[i] +": ";
			for(j=0;j<4;j++)
				sprava= sprava + Mesta[i].getSklad().getTovar(j) + ", ";
				sprava= sprava + "\n";
		}
		return sprava;
	}*/
	
	public static String vypisVypravy(List<Mesto> mesta){
		int i;
		String sprava="";
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta.get(i).getStajna().getVypravy(mesta.get(i)))
				sprava=sprava + "Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ", mnozstvo: " + vyprava.getMnozstvo() 
						+ "prichod o: " + vyprava.getPrichod() + "kol(o)\n";
		}
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta.get(i).getObchod().getVypravy(mesta.get(i)))
				sprava=sprava + "(Obchodny karavan) Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ", mnozstvo: " + vyprava.getMnozstvo() 
						+ "prichod o: " + vyprava.getPrichod() + "kol(o)\n";
		}
		return sprava;
	}
	
	public static int getKolo(){
		return kolo;
	}
	public static void zvysKolo(){
		kolo++;
	}
	
	public static String Kolo(List<Mesto> mesta){
		int i;
		String sprava="";
		for(i=0;i<4; i++){
			sprava= sprava + mesta.get(i).getObchod().overPrichod(mesta.get(i));
			if(mesta.get(i).getMoje()){
			mesta.get(i).getTovaren().vyroba();
			sprava= sprava + mesta.get(i).getStajna().overPrichod(mesta.get(i));
			}
		}
		mesta.get(0).upozorniSledovatelov();
		return sprava;
	}
	
	public static LinkedList<Mesto> Nastav(){  //nastavenie hodnot na zaciatok hry, TREBA OPRAVIT
		LinkedList<Mesto> Mesta = new LinkedList<Mesto>();         //pocet miest
		Mesta.add(new Bavlnovo(true,0,20,20,20,200));
		Mesta.add(new Drevovo(true,1,20,20,20,20));
		Mesta.add(new Kamenovo(false,2,20,20,20,20));
		Mesta.add(new Mramorovo(false,3,20,20,20,20));
		Mesta.get(0).postavBudovu(0);
		Mesta.get(0).postavBudovu(1);
		Mesta.get(1).postavBudovu(0);
		Mesta.get(0).getSklad().setCeny(3,5,10,15);
		Mesta.get(1).getSklad().setCeny(5, 3, 10, 15);
		Mesta.get(2).getSklad().setCeny(15,5,3,10);
		Mesta.get(3).getSklad().setCeny(10,15,5,3);
		Mesta.get(0).setVzdialenost(0,2,3,3);				//nastavenie zaciatocnych hodnot
		Mesta.get(1).setVzdialenost(2, 0, 2, 4);
		Mesta.get(2).setVzdialenost(3,2,0,4);
		Mesta.get(3).setVzdialenost(3,4,4,0);
		return Mesta;
	}
}