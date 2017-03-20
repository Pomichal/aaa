package budovy;

import mesta.*;

public class Budova {
	protected int uroven;
	
	 public Budova(int uroven){
		   setUroven(uroven);
	   }   
	
	public void zvysUroven(int uroven){
		   this.uroven+=uroven;
	   }
		public int getUroven(){
			return uroven;
		}
		public void setUroven(int uroven){
			this.uroven=uroven;
		}
		public void vyroba(Mesto mesto, int druh){
			
		}
		public String vyslatVypravu(Mesto[] mesta, int start, int ciel, int typ, int mnozstvo, int zdroje){
			return "";
		}

}
