package budovy;

public class Sklad extends Budova {

	public Sklad(int uroven) {
		super(uroven);
	}
	
	private int[] mnozstvo = {0,0,0,0}; //tipy poloziek:0:bavlna, 1:drevo, 2:kamen, 3:mramor(?)
	private int[] cena = {0,0,0,0}; //cena tovarov
	
	public void setTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]=mnozstvo;
	}
	public void zvysTovar(int cislo, int mnozstvo){
		this.mnozstvo[cislo]+=mnozstvo;
	}
	public void znizTovar(int typ, int mnozstvo){
		this.mnozstvo[typ]-=mnozstvo;
	}
	public int getTovar(int cislo){
		return mnozstvo[cislo];
	}
	public int[] getTovar(){
		return mnozstvo;
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
		return 1000*this.uroven-obsadenost;		
	}
	public void vyhoditTovar(){
		int typ=(int) Math.random()*4;
		while(this.getVolneMiesto()<0){
			if(this.getTovar(typ)>5)
				this.znizTovar(typ,5);
				else {
					if(typ>=3) typ--;
					else typ++;
					}
		}
	}
	public int getKapacita(){
		return 1000*uroven;
	}
}
