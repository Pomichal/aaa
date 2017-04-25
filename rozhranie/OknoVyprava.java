package rozhranie;

import hra.Turn;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mesta.Mesto;
import vynimky.*;

public class OknoVyprava extends Stage{

	private Button Poslat = new Button("Poslat");
	private Button Zrusit = new Button("Zrusit");
	private TextArea vypis = new TextArea();
	private Label bohatstvo = new Label("Peniaze:");
	
	public OknoVyprava(Mesto start, Mesto ciel, int typ, int mnoz, int zamer, Turn turn){
		
		setTitle("Poslat vypravu?");

		final SledovatelPenazi Peniaze = new SledovatelPenazi(start);
		turn.pridajSledovatela(Peniaze);
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(bohatstvo);
		pane.getChildren().add(Peniaze);
		pane.getChildren().add(vypis);
		pane.getChildren().add(Poslat);
		pane.getChildren().add(Zrusit);
		
		turn.upozorniSledovatelov();
		
		vypis.appendText("Poslat vypravu?\nz: " +
						 start.toString() + "\ndo: " +
						 ciel.toString() + "\ntyp tovaru: " +
						 typ + "\nmnozstvo: " + mnoz + 
						 "\nocakavany zisk:" + ciel.getSklad().getCena(typ)*mnoz + "\nriziko:"
						 + (100 - start.getCesta(ciel.getPoloha()).getRiziko()) + "%\n");
		switch(zamer){
		case 0 : vypis.appendText("trvanie cesty: " + (start.getCesta(ciel.getPoloha()).getDlzka()) +
									"\nzamer: predaj tovaru\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*(4-start.getStajna().getUroven()));
				break;
		case 1 : vypis.appendText("trvanie cesty: " + (start.getCesta(ciel.getPoloha()).getDlzka()) +
									"\nzamer: presun tovaru\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*(4-start.getStajna().getUroven()));
				break;
		case 2 : vypis.appendText("trvanie cesty: " + start.getVzdialenost(ciel) + 
									"\nzamer: najat obchodnu vypravu\n" +
									"cena: " + mnoz*start.getVzdialenost(ciel)*start.getSklad().getCena(typ));
				break;
		}
		turn.upozorniSledovatelov();
		
		Poslat.setOnAction(e -> {
			try{
			if(zamer==2) Rozhranie.getVypis().appendText(start.getObchod().vyslatVypravu(start, ciel, typ, mnoz) + "\n");
			else
			 Rozhranie.getVypis().appendText(start.getStajna().vyslatVypravu(start, ciel, typ, mnoz, zamer) + "\n");
				} catch (MojException ex){
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Chyba");
					a.setContentText(ex.getChyba());	
					a.showAndWait();
				}		
			turn.upozorniSledovatelov();
			close();
		});
		
		Zrusit.setOnAction(e -> {
			close();
		});
		
		setScene(new Scene(pane, 500, 300));
		show();
	}
}
