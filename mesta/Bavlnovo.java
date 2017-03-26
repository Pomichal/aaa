package mesta;



public class Bavlnovo extends Mesto {
	
	public Bavlnovo(boolean moje,int poloha, int b, int d, int k, int m){
		super(moje,poloha,b,d,k,m);
	}
	public void vyroba(){ //pre kazdy tip mesta specificke
		if(this.tovaren!=null)
			this.tovaren.vyroba(this);
	}
}
