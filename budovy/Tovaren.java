package budovy;

import mesta.*;

public class Tovaren extends Budova {
	public Tovaren(int uroven){
		   super(uroven);
	   }  
		
	public void zvysUroven(Mesto mesto){
		super.zvysUroven(mesto);
		mesto.setMoje(true);
	}
	
		//Zabezpeci vyrobu v mestach podla typu
	    public void vyroba(Mesto mesto){
		if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven){
			mesto.getSklad().zvysTovar(3, uroven);
		}
		}
	    public void vyroba(Bavlnovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(0, uroven);
	    }
	    public void vyroba(Drevovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(1, uroven);
	    }
	    public void vyroba(Kamenovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(2, uroven);
	    }
	    public void vyroba(Mramorovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(3, uroven);
	    }
}
