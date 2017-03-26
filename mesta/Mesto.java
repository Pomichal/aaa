package mesta;

import java.util.ArrayList;
import java.util.List;

import budovy.*;
import hra.Sledovatel;

public class Mesto implements ZakladMesta{
	private boolean moje;  //ovladnutie mestskeho trhu
	private static int peniaze=500;  //zdroje hraca
	private int poloha;  //"poloha miesta, pre urcovanie vzdialenosti (B:0,D:1,K:2,M:3)
	protected int[] vzdialenost={0,0,0,0}; //vzdialenost do ostatnych miest (v kolach)
	protected Tovaren tovaren = new Tovaren(1);
	protected Stajna stajna = new Stajna(0);
	protected Sklad sklad = new Sklad(1);
	protected Obchod obchod = new Obchod(1);
	
	transient private List<Sledovatel> sledovatelia = new ArrayList<>();

	public void pridajSledovatela(Sledovatel sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	
	public void upozorniSledovatelov() {
		for (Sledovatel s : sledovatelia)
			s.upozorni();
	}

	
	
	public Mesto(boolean moje,int poloha, int b, int d, int k, int m){ //konstruktor
		int i;
		this.moje=moje;
		this.poloha=poloha;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.sklad.setTovar(i,a[i]);
		}
	}
	public void postavBudovu(int typ){
		switch(typ){
		case 0: this.tovaren.setUroven(1);;
				break;
		case 1: this.stajna.setUroven(1);
				break;
		case 2: this.sklad.setUroven(1);
				break;
		case 3: this.obchod.setUroven(1);
				break;
		}
	}
	public void vylepsiBudovu(Budova budova){
		budova.zvysUroven(this);
	}
	public Stajna getStajna(){
		return this.stajna;
	}
	public Sklad getSklad(){
		return this.sklad;
	}
	public Tovaren getTovaren(){
		return this.tovaren;
	}
	public Obchod getObchod(){
		return this.obchod;
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
	public int getPoloha(){
		return poloha;
	}
	public void setVzdialenost(int b, int d, int k, int m){
		int i;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.vzdialenost[i]=a[i];
		}
	}
	public int getVzdialenost(Mesto ciel){
		return this.vzdialenost[ciel.getPoloha()];
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