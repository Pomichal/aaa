package budovy;

import mesta.*;

public class Tovaren extends Budova {
	public Tovaren(int uroven){
		   super(uroven);
	   }  
		
		//Zabezpeci vyrobu
	    public void vyroba(Mesto mesto){
		if(mesto.getMoje())
			mesto.zvysTovar(3, uroven);
		}
	    public void vyroba(Bavlnovo mesto){
	    	if(mesto.getMoje())
	    		mesto.zvysTovar(0, uroven);
	    }
	    public void vyroba(Drevovo mesto){
	    	if(mesto.getMoje())
	    		mesto.zvysTovar(1, uroven);
	    }
	    public void vyroba(Kamenovo mesto){
	    	if(mesto.getMoje())
	    		mesto.zvysTovar(2, uroven);
	    }
	    public void vyroba(Mramorovo mesto){
	    	if(mesto.getMoje())
	    		mesto.zvysTovar(3, uroven);
	    }
	    
	    public void vyslatVypravu(){
	    	
	    }
}
