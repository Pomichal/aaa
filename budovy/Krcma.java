package budovy;

import java.util.LinkedList;

import hra.Vyprava;

public class Krcma extends Budova{
	
	private static final long serialVersionUID = 1L;
	private int pocetKapitanov;

	public Krcma(int uroven) {
		super(uroven);
		pocetKapitanov=uroven;
	}
	
	public class Kapitan{
		private int mRiziko;
		private int mPrichod;
		private int pCeny;
		
		public Kapitan(){
			mRiziko=(int) Math.random()*4;
			if(mRiziko<3)
				mPrichod=(int) Math.random()*4;
			if(mPrichod<3)
				pCeny =(int) Math.random()*4;
		}
		
		public int getmRiziko(){
			return mRiziko;
		}
		public int getmPrichod(){
			return mPrichod;
		}
		public int getpCeny(){
			return pCeny;
		}
	}
	
	public void zvysUroven(){ //prekonanie metody z triedy Budova
		super.zvysUroven();
		this.pocetKapitanov += uroven;
	}
	
/*	public void pridajKapitana(Vyprava vyprava){
		if(pocetKapitanov>0) {
			Kapitan kap = new Kapitan();
			vyprava.setKapitan(kap);
			pocetKapitanov--;
			kapitani.add(kap);
		}
	}*/

	public LinkedList<Kapitan> generujKapitanov(){
		LinkedList<Kapitan> kapitani = new LinkedList<>();
		while(pocetKapitanov>0)
			kapitani.add(new Kapitan());
		return kapitani;
	}
	public void priradKapitana(Vyprava vyprava, Kapitan kap){
		vyprava.setKapitan(kap);
	}
}
