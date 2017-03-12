package mesta;

public interface ZakladMesta {
		
		void setTovar(int cislo, int mnozstvo); 
		void zvysTovar(int cislo, int mnozstvo);
		void znizTovar(int cislo, int mnozstvo);
		int getTovar(int cislo);
		void setCeny(int b, int d, int k, int m);
		int getCena(int cislo);
		boolean getMoje();
		void setMoje(boolean moje);
		void zmenMoje();
		int getPeniaze();
		void zvysPeniaze(int peniaze);
		void znizPeniaze(int peniaze);
		void setVzdialenost(int b, int d, int k, int m);
		int getVzdialenost(int cislo);
}
