package budovy;

import mesta.*;

public class Tovaren extends Budova {
	public Tovaren(int uroven){
		   super(uroven);
	   }  
		
	public String zvysUroven(Mesto mesto){
		mesto.setMoje(true);
		return super.zvysUroven(mesto);
	}
	
		//Zabezpeci vyrobu v mestach podla typu
	    public void vyroba(Mesto mesto){
		if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven){
			mesto.getSklad().zvysTovar(3, uroven);
		}
		else if(mesto.getSklad().getVolneMiesto()<0) mesto.getSklad().znizTovar((int) (Math.random()*4), 1);
		}
	    public void vyroba(Bavlnovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(0, uroven);
			else if(mesto.getSklad().getVolneMiesto()<0) mesto.getSklad().znizTovar((int) (Math.random()*3), 1);
	    }
	    public void vyroba(Drevovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(1, uroven);
			else if(mesto.getSklad().getVolneMiesto()<0) mesto.getSklad().znizTovar((int) (Math.random()*3), 1);
	    }
	    public void vyroba(Kamenovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(2, uroven);
			else if(mesto.getSklad().getVolneMiesto()<0) mesto.getSklad().znizTovar((int) (Math.random()*3), 1);
	    }
	    public void vyroba(Mramorovo mesto){
	    	if(mesto.getMoje() && mesto.getSklad().getVolneMiesto()>=uroven)
	    		mesto.getSklad().zvysTovar(3, uroven);
			else if(mesto.getSklad().getVolneMiesto()<0) mesto.getSklad().znizTovar((int) (Math.random()*3), 1);
	    }
}
