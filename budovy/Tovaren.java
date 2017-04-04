package budovy;

import mesta.*;

public abstract class Tovaren extends Budova {
	public Tovaren(int uroven){
		   super(uroven);
	   }  
		
	public String zvysUroven(Mesto mesto){
		mesto.setMoje(true);
		return super.zvysUroven(mesto);
	}
		//Zabezpeci vyrobu v mestach podla typu
	    public abstract void vyroba(Bavlnovo mesto);
	    public abstract void vyroba(Drevovo mesto);
	    public abstract void vyroba(Kamenovo mesto);
	    public abstract void vyroba(Mramorovo mesto);
}
