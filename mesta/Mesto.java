package mesta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import budovy.*;
import hra.Sledovatel;

public class Mesto implements ZakladMesta{
	private boolean moje;  //ovladnutie mestskeho trhu, spustenie alebo zastavenie vyroby
	private static int peniaze=5000;  //zdroje hraca
	private int poloha;  //"poloha miesta, pre urcovanie vzdialenosti (B:0,D:1,K:2,M:3)
	protected int[] vzdialenost={0,0,0,0}; //vzdialenost do ostatnych miest (v kolach)
	protected int[] okolie = {0,0,0,0}; //okolie mesta, urcuje pruduktivitu tovarni
	private  LinkedList<Tovaren> tovarne = new LinkedList<>();  //budovy mesta
	private Stajna stajna;
	private Sklad sklad;
	private Obchod obchod;
	private List<Cesta> cesty = new LinkedList<>(); //cesty do ostatnych miest
	
	transient private static List<Sledovatel> sledovatelia = new ArrayList<>();
	
	public Mesto(boolean moje,int poloha, int b, int d, int k, int m){ //konstruktor
		int i;
		this.moje=moje;
		this.poloha=poloha;
		int[] a={b,d,k,m};
		stajna = new Stajna(0);
		sklad= new Sklad(1);
		obchod = new Obchod(1);
		tovarne.add(new Ovciar(0));
		tovarne.add(new Drevaren(0));
		tovarne.add(new Kamenolom(0));
		tovarne.add(new TovMramor(0));
		for(i=0;i<4;i++){
			this.sklad.setTovar(i,a[i]);
		}
	}
	
	//pre vzor Observer
	public void pridajSledovatela(Sledovatel sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	
	public void upozorniSledovatelov() {
		for (Sledovatel s : sledovatelia)
			s.upozorni();
	}
	
	public void postavTovaren(int typ){
		this.tovarne.get(typ).setUroven(1);
	}
	public void vylepsiTovaren(int typ){
		this.tovarne.get(typ).zvysUroven(this);
	}
	public void vylepsiBudovu(Budova budova){
		budova.zvysUroven(this);
	}
	public List<Budova> getBudovy(){
		List<Budova> budovy = new LinkedList<>();
		budovy.add(this.stajna);
		budovy.add(this.sklad);
		for(Tovaren i: this.tovarne)
			budovy.add(i);
		return budovy;
	}
	public String getBudova(){
		String sprava="";
		List<Budova> budovy = this.getBudovy();
		sprava = sprava + this.obchod.toString() + ", uroven: " + this.obchod.getUroven() + "\n";
		for(Budova b : budovy)
			if(b.getUroven()>0)
			sprava = sprava + b.toString() + ", uroven: " + b.getUroven() + "\n";
		sprava +="Cesty:\n";
		for(Cesta c : cesty)
			if(c!=null)
			sprava = sprava + c.getOpis(this);
		return sprava;
	}
	public Stajna getStajna(){
		return this.stajna;
	}
	public Sklad getSklad(){
		return this.sklad;
	}
	public LinkedList<Tovaren> getTovaren(){
		return this.tovarne;
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
	public int getOkolie(int cislo){
		return okolie[cislo];
	}
	public int getVzdialenost(Mesto ciel){
		return this.vzdialenost[ciel.getPoloha()];
	}
	public int getVzdialenost(int i){
		return this.vzdialenost[i];
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
	public void vyroba(Tovaren tovaren){  //pre kazdy typ mesta specificke

	}
	public Cesta getCesta(int index){
		return cesty.get(index);
	}
	public List<Cesta> getCesty(){
		return this.cesty;
	}
	public List<String> getCestyOpis(){
		List<String> sprava = new LinkedList<>();
		for(int i=0;i<4;i++){
			if(cesty.get(i)!=null)
			sprava.add("cesta " + cesty.get(i).getOpis(this));
		}
		return sprava;
	}
	public void nastavCesty(List<Mesto> mesta){
		for(int i = 0; i<4; i++)
			for(int j=0;j<4;j++){
				if(i==j) mesta.get(i).cesty.add(null);
				else if(i<j){
				mesta.get(i).cesty.add(new Cesta(1,mesta.get(i),mesta.get(j)));
				}
				else if(i>j){
				mesta.get(i).cesty.add(mesta.get(j).cesty.get(i));
				}
			}
	}
}