package budovy;

import java.util.LinkedList;
import java.util.List;

import mesta.Mesto;

public abstract class Budova {
	protected int uroven;
	
	 public Budova(int uroven){
		   setUroven(uroven);
	   }
	 
	private class Stavba{		//sluzi na evidovanie vystavby budov
		 public Stavba(int trvanie, Budova budova){
			 this.trvanie=trvanie;
			 this.budova=budova;
		 }
		 private int trvanie;
		 private Budova budova;
		 
		 public void znizTrvanie(){
			 this.trvanie--;
		 }
	 }
	 
	 private static List<Stavba> stavby = new LinkedList<>();
	 
	 public static List<Stavba> getStavby(){
		 return stavby;
	 }
	
	public String zvysUroven(Mesto mesto){
		if(uroven<3 && mesto.getSklad().getTovar(0)>=(50 + (20*uroven)) && mesto.getSklad().getTovar(1)>=(50 + (20*uroven)) && 
				mesto.getSklad().getTovar(2)>=(50 + (20*uroven)) && mesto.getSklad().getTovar(3)>=(50 + (20*uroven)) && mesto.getPeniaze()>=(300 + 300 * uroven)){
		mesto.znizPeniaze(300 + 300*uroven);
		for(int i=0; i<4;i++){
			mesto.getSklad().znizTovar(i, (50 + (20*uroven)));
		}
		 //  this.uroven++;
		stavby.add(new Stavba(5*(uroven+1),this));
		   mesto.upozorniSledovatelov();
		   return "Stavba sa zacala, trvanie:" + 5*(uroven+1) + "\n";
		}
		else if(uroven<4) return "nedostatok zdrojov\n";
		else return "budova ma maximalnu uroven\n";
	   }
	
	public String zistiVynos(Mesto mesto){  //potrebne pre tovarne
		return "";
	}
	public void zvysUroven(){
		this.uroven+=1;
	}
	public void addStavba(Budova budova){
		stavby.add(new Stavba(5*(uroven+1),budova));
	}
		public int getUroven(){
			return uroven;
		}
		public void setUroven(int uroven){
			this.uroven=uroven;
		}
		public String kontrolaStavby(){
			String sprava = "";
			for(Stavba stavba : stavby)
				stavba.znizTrvanie();
			for(int i = 0; i<stavby.size();i++){
				Stavba stavba=stavby.get(i);
				if(stavba.trvanie==0){
					stavba.budova.zvysUroven();
					stavby.remove(stavby.indexOf(stavba));
					i--;
					sprava +=  "Budova:" + stavba.budova + " postavena\n";
				} else
					sprava += "Budova:" + stavba.budova + " sa postavi za " + stavba.trvanie + "kol(o)\n";
			}
			return sprava;
		}
}
