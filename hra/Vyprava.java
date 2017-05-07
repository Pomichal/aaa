package hra;

import java.io.Serializable;

import budovy.Krcma.Kapitan;
import mesta.*;

public class Vyprava implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int typ, mnozstvo, zdroje, prichod,zamer;
	private Mesto start,ciel;
	private Kapitan kapitan;
	
	public Vyprava(Mesto start, Mesto ciel, int typ, int mnozstvo, int zdroje, int zamer){
		this.start=start;
		this.ciel=ciel;
		this.typ=typ;
		this.mnozstvo=mnozstvo;
		this.zdroje=zdroje;
		this.zamer=zamer;
		this.prichod=start.getCesta(ciel.getPoloha()).getDlzka();
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
	public void znizPrichod(int prichod){
		this.prichod=this.prichod-prichod;
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
	public void setKapitan(Kapitan kap){
		this.kapitan = kap;
	}
	public Kapitan getKapitan(){
		return this.kapitan;
	}
}
