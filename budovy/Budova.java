package budovy;

import mesta.Mesto;

public class Budova {
	protected int uroven;
	
	 public Budova(int uroven){
		   setUroven(uroven);
	   }   
	
	public void zvysUroven(Mesto mesto){
		if(mesto.getSklad().getTovar(0)>=20 && mesto.getSklad().getTovar(1)>=20 && mesto.getSklad().getTovar(2)>=20 && mesto.getSklad().getTovar(3)>=20){
		mesto.znizPeniaze(100);
		for(int i=0; i<4;i++){
			mesto.getSklad().znizTovar(i, 20);
		}
		   this.uroven++;
		   mesto.upozorniSledovatelov();
		}
	   }
		public int getUroven(){
			return uroven;
		}
		public void setUroven(int uroven){
			this.uroven=uroven;
		}
}
