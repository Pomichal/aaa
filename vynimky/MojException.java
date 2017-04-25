package vynimky;

import hra.Vyprava;

public class MojException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chyba;

	public MojException(String string) {
		this.chyba = string;
	}
	
	public MojException(Vyprava vyprava, int nahoda){
		switch(nahoda){
		case 0 : this.chyba="Vypravu do mesta " + vyprava.getCiel() + " napadli a vykradli banditi\n"
				+ "vyprava sa vrati domov bez zisku";
				vyprava.setMnozstvo(0);
				vyprava.setZdroje(0);
				break;
		case 1: this.chyba = "Vyprava do mesta " + vyprava.getCiel() + " sa dostala do burky a stratila polovicu";
				if(vyprava.getMnozstvo()>0) {
					this.chyba += " zasob";
					vyprava.setMnozstvo(vyprava.getMnozstvo()/2);
				} else if(vyprava.getZdroje()>0){
					this.chyba += " zisku";
					vyprava.setZdroje(vyprava.getZdroje());
				}
				break;
		default: this.chyba = "Veduci vypravy do mesta " + vyprava.getCiel() + " utrpel nehodu, prichod sa oddialil";
				vyprava.setPrichod(vyprava.getPrichod()+ 5);
		}
	}
	
	public String getChyba(){
		return this.chyba;
	}

}
