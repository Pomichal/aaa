package hra;

import mesta.*;

public class Turn {
	
private static int kolo=0;
	
	public static String vypis(Mesto[] Mesta){ //vypis tovaru pre kontrolu
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
	}
	
	public static String Kolo(Mesto[] Mesta){
		int i;
		String sprava="";
		for(i=0;i<4; i++){
			if(Mesta[i].getMoje()){
			Mesta[i].vyroba();
			sprava= sprava + Mesta[i].getStajna().overPrichod(Mesta[i], Mesta);
			}
		}
		++kolo;
		return kolo +". kolo\n" + sprava;
	}
	
	public static Mesto[] Nastav(){  //nastavenie hodnot na zaciatok hry, TREBA OPRAVIT
		Mesto[] Mesta = new Mesto[4];         //pocet miest
		Mesta[0]=new Bavlnovo(true,20,20,20,20);
		Mesta[1]=new Drevovo(true,20,20,20,20);
		Mesta[2]=new Kamenovo(false,20,20,20,20);
		Mesta[3]=new Mramorovo(false,20,20,20,20);
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