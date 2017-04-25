package mesta;

import budovy.Tovaren;

public class Kamenovo extends Mesto {
	 
	private static final long serialVersionUID = 1L;
	public Kamenovo(boolean moje,int poloha, int b, int d, int k, int m){
		super(moje,poloha,b,d,k,m);
		okolie[0]=5;
		okolie[1]=15;
		okolie[2]=70;
		okolie[3]=10;
	}
	public void vyroba(Tovaren tovaren){  //pre kazdy typ mesta specificke
		tovaren.vyroba(this);
	}
}
