package hra;

import java.util.*;
import budovy.*;
import mesta.*;

public class Turn{

private static int kolo=0;
	
	public static String vypis(List<Mesto> Mesta){ //vypis ceny pre kontrolu
		int i,j;
		String[] mena={"Bavlnovo", "Drevovo", "Kamenovo", "Mramorovo"};
		String sprava="";
		for(i=0;i<4;i++){  
			sprava= sprava + mena[i] +": ";
			for(j=0;j<4;j++){
				sprava= sprava + Mesta.get(i).getSklad().getCena(j) + ", ";
			/*if(Mesta.get(i).getCesta(j)!=null)							vypis ciest pre kontrolu
				sprava =  sprava + Mesta.get(i).getCesta(j).toString() + "\n";*/
			}
				sprava= sprava + "\n";
		}
		return sprava;
	}
	
	public static String vypisVypravy(List<Mesto> mesta){
		int i;
		int j =1;
		String sprava="";
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta.get(i).getStajna().getVypravy(mesta.get(i))){
				sprava=sprava +j + ". Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ",\n\tmnozstvo: " + vyprava.getMnozstvo() 
						+ " prichod o: " + vyprava.getPrichod() + " kol(o)\n";
			j++;
			}
		}
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta.get(i).getObchod().getVypravy(mesta.get(i))){
				sprava=sprava + "(Obchodny karavan)" + j + "Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ",\n\tmnozstvo: " + vyprava.getMnozstvo() 
						+ " prichod o: " + vyprava.getPrichod() + " kol(o)\n";
			j++;
			}
		}
		return vypis(mesta) + sprava;
	}
	
	public static int getKolo(){
		return kolo;
	}
	public static void zvysKolo(){
		kolo++;
	}
	
	public static String Kolo(List<Mesto> mesta){  //prebehnu stavby a posunutie vyprav
		int i;
		String sprava="";
		for(i=0;i<4; i++){
			sprava= sprava + mesta.get(i).getObchod().overPrichod(mesta.get(i));
			sprava= sprava + mesta.get(i).getStajna().overPrichod(mesta.get(i));
			mesta.get(i).getSklad().prehodnotCeny(kolo,i);
			if(mesta.get(i).getMoje()){
				for(Tovaren tov: mesta.get(i).getTovaren())
					mesta.get(i).vyroba(tov);
			}
		}
		sprava += mesta.get(0).getObchod().kontrolaStavby();
		mesta.get(0).upozorniSledovatelov();
		return sprava;
	}
	
	public static List<Mesto> Nastav(){  //nastavenie hodnot na zaciatok hry
		List<Mesto> Mesta=new LinkedList<Mesto>();     
		Mesta.add(new Bavlnovo(true,0,100,100,100,100));
		Mesta.add(new Drevovo(true,1,100,100,100,100));
		Mesta.add(new Kamenovo(false,2,100,100,100,100));
		Mesta.add(new Mramorovo(false,3,100,100,100,100));
		Mesta.get(0).postavTovaren(0);	
		Mesta.get(0).getStajna().zvysUroven();
		Mesta.get(1).postavTovaren(1);
		Mesta.get(0).getSklad().setCeny(3,5,10,15);
		Mesta.get(1).getSklad().setCeny(5, 3, 10, 15);
		Mesta.get(2).getSklad().setCeny(15,5,3,10);
		Mesta.get(3).getSklad().setCeny(10,15,5,3);
		Mesta.get(0).setVzdialenost(0,10,15,15);				//nastavenie zaciatocnych hodnot
		Mesta.get(1).setVzdialenost(10, 0, 10, 20);
		Mesta.get(2).setVzdialenost(15,10,0,20);
		Mesta.get(3).setVzdialenost(15,20,20,0);
		Mesta.get(0).nastavCesty(Mesta);
		return Mesta;
	}
}