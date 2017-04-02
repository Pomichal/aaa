package hra;

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
	
	public static String vypisVypravy(Mesto[] mesta){
		int i;
		String sprava="";
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta[i].getStajna().getVypravy(mesta[i]))
				sprava=sprava + "Start: " + vyprava.getStart() + ", Ciel: " + vyprava.getCiel()
						+ ", typ tovaru: " + vyprava.getTyp() + ", mnozstvo: " + vyprava.getMnozstvo() 
						+ "prichod o: " + vyprava.getPrichod() + "kol(o)\n";
		}
		for(i=0;i<4;i++){
			for(Vyprava vyprava:mesta[i].getObchod().getVypravy(mesta[i]))
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
	
	public static String Kolo(Mesto[] Mesta){
		int i;
		String sprava="";
		for(i=0;i<4; i++){
			sprava= sprava + Mesta[i].getObchod().overPrichod(Mesta[i]);
			if(Mesta[i].getMoje()){
			Mesta[i].vyroba();
			sprava= sprava + Mesta[i].getStajna().overPrichod(Mesta[i]);
			}
		}
		Mesta[0].upozorniSledovatelov();
		return sprava;
	}
	
	public static Mesto[] Nastav(){  //nastavenie hodnot na zaciatok hry, TREBA OPRAVIT
		Mesto[] Mesta = new Mesto[4];         //pocet miest
		Mesta[0]=new Bavlnovo(true,0,20,20,20,200);
		Mesta[1]=new Drevovo(true,1,20,20,20,20);
		Mesta[2]=new Kamenovo(false,2,20,20,20,20);
		Mesta[3]=new Mramorovo(false,3,20,20,20,20);
		Mesta[0].postavBudovu(0);
		Mesta[0].postavBudovu(1);
		Mesta[1].postavBudovu(0);
		Mesta[0].getSklad().setCeny(3,5,10,15);
		Mesta[1].getSklad().setCeny(5, 3, 10, 15);
		Mesta[2].getSklad().setCeny(15,5,3,10);
		Mesta[3].getSklad().setCeny(10,15,5,3);
		Mesta[0].setVzdialenost(0,2,3,3);				//nastavenie zaciatocnych hodnot
		Mesta[1].setVzdialenost(2, 0, 2, 4);
		Mesta[2].setVzdialenost(3,2,0,4);
		Mesta[3].setVzdialenost(3,4,4,0);
		return Mesta;
	}
}