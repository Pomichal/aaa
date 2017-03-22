package mesta;

public class Kamenovo extends Mesto {
	public Kamenovo(boolean moje, int b, int d, int k, int m){
		super(moje,b,d,k,m);
	}
	public void vyroba(){ //pre kazdy tip mesta specificke
		if(this.tovaren!=null)
			this.tovaren.vyroba(this);
	}

}
