package mesta;

import budovy.Tovaren;

public class Mramorovo extends Mesto {
	public Mramorovo(boolean moje,int poloha, int b, int d, int k, int m){
		super(moje,poloha,b,d,k,m);
		okolie[0]=5;
		okolie[1]=10;
		okolie[2]=15;
		okolie[3]=70;
	}
	public void vyroba(Tovaren tovaren){  //pre kazdy typ mesta specificke
		tovaren.vyroba(this);
	}
}
