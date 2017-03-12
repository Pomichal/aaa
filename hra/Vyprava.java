package hra;

public class Vyprava {
	
	private int start, ciel, typ, mnozstvo, nosnost, zdroje, uloha, prichod;
	
	public Vyprava(int start, int ciel, int typ, int mnozstvo, int zdroje, int uloha){
		this.start=start;
		this.ciel=ciel;
		this.typ=typ;
		this.mnozstvo=mnozstvo;
		this.zdroje=zdroje;
		this.uloha=uloha;
	}
	public void setStart(int start){
		this.start=start;
	}
	public int getStart(){
		return start;
	}
	public int getCiel(){
		return ciel;
	}
	public int getTyp(){
		return typ;
	}
	public int getMnozstvo(){
		return mnozstvo;
	}
	public int getNosnost(){
		return nosnost;
	}
	public void setZdroje(int zdroje){
		this.zdroje=zdroje;
	}
	public int getZdroje(){
		return zdroje;
	}
	public int getUloha(){
		return uloha;
	}
	public void setPrichod(int prichod){
		this.prichod=prichod;
	}
	public void znizPrichod(){
		this.prichod--;
	}
	public int getPrichod(){
		return prichod;
	}

}
