package budovy;

import mesta.*;

public class Tovaren extends Budova {
	public Tovaren(int uroven){
		   super(uroven);
	   }  
		
		//Zabezpeci vyrobu
	    public void vyroba(Mesto mesto, int druh){
		if(mesto.getMoje())
			mesto.zvysTovar(druh, uroven);
		}
	    
	    public void vyslatVypravu(){
	    	
	    }
}
