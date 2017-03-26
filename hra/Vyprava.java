package hra;

import mesta.*;

public class Vyprava {
	
	private int typ, mnozstvo, zdroje, prichod,zamer;
	private Mesto start,ciel;
	
	public Vyprava(Mesto start, Mesto ciel, int typ, int mnozstvo, int zdroje, int zamer){
		this.start=start;
		this.ciel=ciel;
		this.typ=typ;
		this.mnozstvo=mnozstvo;
		this.zdroje=zdroje;
		this.zamer=zamer;
	}
	public void setStart(Mesto start){
		this.start=start;
	}
	public Mesto getStart(){
		return start;
	}
	public Mesto getCiel(){
		return ciel;
	}
	public int getTyp(){
		return typ;
	}
	public int getMnozstvo(){
		return mnozstvo;
	}
	public void setMnozstvo(int mnozstvo){
		this.mnozstvo=mnozstvo;
	}
	public void setZdroje(int zdroje){
		this.zdroje=zdroje;
	}
	public int getZdroje(){
		return zdroje;
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
	public int getZamer() {
		return zamer;
	}
	public void setZamer(int zamer) {
		this.zamer = zamer;
	}
}
