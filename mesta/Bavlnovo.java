package mesta;


import budovy.Tovaren;

public class Bavlnovo extends Mesto {
	
	public Bavlnovo(boolean moje,int poloha, int b, int d, int k, int m){
		super(moje,poloha,b,d,k,m);
		okolie[0]=70;
		okolie[1]=15;
		okolie[2]=10;
		okolie[3]=5;
	}
	public void vyroba(Tovaren tovaren){  //pre kazdy typ mesta specificke
		tovaren.vyroba(this);
	}
	
}
