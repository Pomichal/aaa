package vynimky;

public class NedostatokException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chyba;

	public NedostatokException(String string) {
		this.chyba = string;
	}
	
	public String getChyba(){
		return this.chyba;
	}

}
