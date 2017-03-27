package budovy;

import mesta.Mesto;

public class Budova {
	protected int uroven;
	
	 public Budova(int uroven){
		   setUroven(uroven);
	   }   
	
	public String zvysUroven(Mesto mesto){
		if(uroven<4 && mesto.getSklad().getTovar(0)>=(5 + (20*uroven)) && mesto.getSklad().getTovar(1)>=(5 + (20*uroven)) && 
				mesto.getSklad().getTovar(2)>=(5 + (20*uroven)) && mesto.getSklad().getTovar(3)>=(5 + (20*uroven))){
		mesto.znizPeniaze(100);
		for(int i=0; i<4;i++){
			mesto.getSklad().znizTovar(i, (5 + (20*uroven)));
		}
		   this.uroven++;
		   mesto.upozorniSledovatelov();
		   return "Budova vylepsena na uroven: " + uroven;
		}
		else if(uroven<4) return "nedostatok tovaru";
		else return "budova ma maximalnu uroven";
	   }
		public int getUroven(){
			return uroven;
		}
		public void setUroven(int uroven){
			this.uroven=uroven;
		}
}
