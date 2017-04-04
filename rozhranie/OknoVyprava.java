package rozhranie;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.Mesto;

public class OknoVyprava extends Stage{

	private Button Poslat = new Button("Poslat");
	private Button Zrusit = new Button("Zrusit");
	private TextArea vypis = new TextArea();
	private Label bohatstvo = new Label("Peniaze:");
	
	public OknoVyprava(Mesto start, Mesto ciel, int typ, int mnoz, int zamer){
		
		setTitle("Poslat vypravu?");

		final SledovatelPenazi Peniaze = new SledovatelPenazi(start);
		start.pridajSledovatela(Peniaze);
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(vypis);
		pane.getChildren().add(Poslat);
		pane.getChildren().add(Zrusit);
		
		start.upozorniSledovatelov();
		
		vypis.appendText("Poslat vypravu?\nz: " +
						 start.toString() + "\ndo: " +
						 ciel.toString() + "\ntyp tovaru: " +
						 typ + "\nmnozstvo: " + mnoz + 
						 "\nocakavany zisk:" + ciel.getSklad().getCena(typ)*mnoz + "\n");
		switch(zamer){
		case 0 : vypis.appendText("trvanie cesty: " + (start.getVzdialenost(ciel)/start.getStajna().getUroven()) +
									"\nzamer: predaj tovaru\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*(4-start.getStajna().getUroven()));
				break;
		case 1 : vypis.appendText("trvanie cesty: " + (start.getVzdialenost(ciel)/start.getStajna().getUroven()) +
									"\nzamer: presun tovaru\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*(4-start.getStajna().getUroven()));
				break;
		case 2 : vypis.appendText("trvanie cesty: " + start.getVzdialenost(ciel) + 
									"\nzamer: najat obchodnu vypravu\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*start.getSklad().getCena(typ));
				break;
		}
		
		Poslat.setOnAction(e -> {
			if(zamer==2) Rozhranie.getVypis().appendText(start.getObchod().vyslatVypravu(start, ciel, typ, mnoz) + "\n");
			else
			 Rozhranie.getVypis().appendText(start.getStajna().vyslatVypravu(start, ciel, typ, mnoz, zamer) + "\n");
			close();
		});
		
		Zrusit.setOnAction(e -> {
			close();
		});
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}
