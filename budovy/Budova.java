package budovy;

import mesta.Mesto;

public abstract class Budova {
	protected int uroven;
	
	 public Budova(int uroven){
		   setUroven(uroven);
	   }   
	
	public String zvysUroven(Mesto mesto){
		if(uroven<3 && mesto.getSklad().getTovar(0)>=(50 + (20*uroven)) && mesto.getSklad().getTovar(1)>=(50 + (20*uroven)) && 
				mesto.getSklad().getTovar(2)>=(50 + (20*uroven)) && mesto.getSklad().getTovar(3)>=(50 + (20*uroven)) && mesto.getPeniaze()>=(300 + 300 * uroven)){
		mesto.znizPeniaze(300 + 300*uroven);
		for(int i=0; i<4;i++){
			mesto.getSklad().znizTovar(i, (5 + (20*uroven)));
		}
		   this.uroven++;
		   mesto.upozorniSledovatelov();
		   return "Budova vylepsena na uroven: " + uroven + "\n";
		}
		else if(uroven<4) return "nedostatok zdrojov\n";
		else return "budova ma maximalnu uroven\n";
	   }
		public int getUroven(){
			return uroven;
		}
		public void setUroven(int uroven){
			this.uroven=uroven;
		}
}
