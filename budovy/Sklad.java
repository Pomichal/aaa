package budovy;

public class Sklad extends Budova {

	public Sklad(int uroven) {
		super(uroven);
	}
	
	private int[] mnozstvo = {0,0,0,0}; //tipy poloziek:0:bavlna, 1:drevo, 2:kamen, 3:mramor(?)
	private int[] cena = {0,0,0,0}; //cena tovarov
	int kapacita=100*uroven;
	
	public void zvysUroven(int uroven){
		   super.zvysUroven(uroven);
		   kapacita+=50;
	   }
	public void setTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]=mnozstvo;
	}
	public void zvysTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]+=mnozstvo;
	}
	public void znizTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]-=mnozstvo;
	}
	public int getTovar(int cislo){
		return mnozstvo[cislo];
	}
	public int getCena(int cislo){
		return cena[cislo];
	}
	public void setCeny(int b, int d, int k, int m){
		int i;
		int[] a={b,d,k,m};
		for(i=0;i<4;i++){
			this.cena[i]=a[i];
		}
	}
	public int getVolneMiesto(){
		int obsadenost=0;
		for(int mnoz:mnozstvo)
			obsadenost+=mnoz;
		return this.kapacita-obsadenost;		
	}
	public int getKapacita(){
		return kapacita;
	}
}
