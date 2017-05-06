package budovy;


import java.util.LinkedList;

public class Krcma extends Budova{
	
	private static final long serialVersionUID = 1L;
	private int pocetKapitanov;
	private LinkedList<Kapitan> kapitani = new LinkedList<Kapitan>();

	public Krcma(int uroven) {
		super(uroven);
		pocetKapitanov=uroven;
		for(int i=0;i<pocetKapitanov;i++)
		kapitani.add(new Kapitan());
	}
	
	public class Kapitan{
		private int mRiziko;
		private int mPrichod;
		private boolean obsadene;
		private String meno = "";
		private char[] samoh = {'A','E','I','O','U'};
		private char[] spoluh = {'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Z'};
		
		public Kapitan(){
			for(int i=0;i<5;i++){
				if(i%2==0){
			     meno+=spoluh[(int)(Math.random()*(spoluh.length-1))];
				}
				else 
					meno+=samoh[(int)(Math.random()*(samoh.length-1))];
			}
			mRiziko=(int) (Math.random()*4);
			if(mRiziko<3)
				mPrichod=(int) (Math.random()*4);
		//	kapitani.add(this);
		}
		
		public int getmRiziko(){
			return mRiziko;
		}
		public int getmPrichod(){
			return mPrichod;
		}
		public String getMeno(){
			return meno;
		}
		public void zmenObsadene(){
			this.obsadene=!this.obsadene;
		}
	}
	
	public void zvysUroven(){ //prekonanie metody z triedy Budova
		super.zvysUroven();
		this.pocetKapitanov++;
		kapitani.add(new Kapitan());
	}
	public LinkedList<Kapitan> getKapitani(){
		LinkedList<Kapitan> kap2 = new LinkedList<Kapitan>();
		for(Kapitan kap:kapitani)
			if(kap.obsadene==false) kap2.add(kap);
		return kap2;
	}
	public LinkedList<Kapitan> getVolneKapitani(){
		return null;
	}
}
