package mesta;

import budovy.Tovaren;

public class Drevovo extends Mesto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Drevovo(boolean moje,int poloha, int b, int d, int k, int m){
		super(moje,poloha,b,d,k,m);
		okolie[0]=20;
		okolie[1]=70;
		okolie[2]=7;
		okolie[3]=3;
	}
	public void vyroba(Tovaren tovaren){  //pre kazdy typ mesta specificke
		tovaren.vyroba(this);
	}
}
