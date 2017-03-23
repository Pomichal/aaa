package mesta;

import java.util.ArrayList;
import java.util.List;

import budovy.*;
import hra.Sledovatel;

public class Mesto implements ZakladMesta{
	private boolean moje;  //ovladnutie mestskeho trhu
	private static int peniaze=500;  //zdroje hraca
	private int[] vzdialenost={0,0,0,0}; //vzdialenost do ostatnych miest (v kolach)
	protected Tovaren tovaren = new Tovaren(0);
	protected Stajna stajna = new Stajna(0);
	protected Sklad sklad = new Sklad(1);
	
	transient private List<Sledovatel> sledovatelia = new ArrayList<>();

	public void pridajSledovatela(Sledovatel sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	
	public void upozorniSledovatelov() {
		for (Sledovatel s : sledovatelia)
			s.upozorni();
	}

	
	
	public Mesto(boolean moje, int b, int d, int k, int m){ //konstruktor
		int i;
		setMoje(moje);
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.sklad.setTovar(i,a[i]);
		}
	}
	public void postavBudovu(int typ){
		switch(typ){
		case 0: this.tovaren.zvysUroven(1);;
				break;
		case 1: this.stajna.zvysUroven(1);
				break;
		case 2: this.sklad.zvysUroven(1);
				break;
		}
	}
	public Stajna getStajna(){
		return stajna;
	}
	public Sklad getSklad(){
		return this.sklad;
	}
	public void setMoje(boolean moje){
		this.moje=moje;
	}
	public void zmenMoje(){
		this.moje=!this.moje;
	}
	public boolean getMoje(){
		return moje;
	}
	public void setVzdialenost(int b, int d, int k, int m){
		int i;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.vzdialenost[i]=a[i];
		}
	}
	public int getVzdialenost(int cislo){
		return vzdialenost[cislo];
	}
	public int getPeniaze(){
		return peniaze;
	}
	public void zvysPeniaze(int peniaze){
		Mesto.peniaze+=peniaze;
	}
	public void znizPeniaze(int peniaze){
		Mesto.peniaze-=peniaze;
	}

	
	public void vyroba(){ //pre kazdy tip mesta specificke
		
	}
	

}