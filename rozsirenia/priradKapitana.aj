package rozsirenia;


import budovy.*;
import budovy.Krcma.Kapitan;
import hra.Vyprava;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import mesta.Mesto;
import rozhranie.*;
import vynimky.MojException;

public privileged aspect priradKapitana {
	OknoVyprava okno;
	Mesto mesto;
	Kapitan kap;
	
	private ComboBox<Kapitan> OknoVyprava.kapitani = new ComboBox<>();
	private TextArea OknoVyprava.vypkapitani = new TextArea();
	
	after() returning(OknoVyprava o): call(OknoVyprava.new(..)) {
		okno = o;
		mesto= o.getStart();
				((FlowPane) o.getScene().getRoot()).getChildren().add(o.kapitani);
				((FlowPane) o.getScene().getRoot()).getChildren().add(o.vypkapitani);
				if(mesto.getKrcma()!=null){
					o.kapitani.setItems(FXCollections.observableList(mesto.getKrcma().getKapitani()));
					}
				o.kapitani.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Kapitan>(){
					public void changed(ObservableValue<? extends Kapitan> arg0, Kapitan arg1, Kapitan arg2) {
						kap = arg2;
						o.vypkapitani.setText("Meno: " + arg2.getMeno() 
												+ "\nUcinok na riziko: -" + arg2.getmRiziko() + "%\n"
												+ "Ucinok na prichod: -" + arg2.getmPrichod());
					}
				});
	}
	
	after() returning(Vyprava v): call(Vyprava.new(..)){
		if(mesto.getPeniaze()>=100 && kap!=null){
		v.znizPrichod(kap.getmPrichod());
		v.setKapitan(kap);
		mesto.znizPeniaze(100);
		kap.zmenObsadene();
		kap=null;
		}
	}
	after(int i, Vyprava v):call(void Vyprava.setPrichod(int)) && args(i) && target(v){
		if(v.getKapitan()!=null)
			v.znizPrichod(v.getKapitan().getmPrichod());
	}
	before(Stajna s, int index):call (void Stajna.odoberVypravu(..)) && args(index) && target(s){
		if(s.getVypravy().get(index).getKapitan()!=null) s.getVypravy().get(index).getKapitan().zmenObsadene();
	}
	before(Obchod ob, int index):call (void Obchod.odoberVypravu(..)) && args(index) && target(ob){
		if(ob.getVypravy().get(index).getKapitan()!=null) ob.getVypravy().get(index).getKapitan().zmenObsadene();
	}
	before(Vyprava v, int c):call(MojException.new(Vyprava, int)) && args(v,c){
		if(v.getKapitan()!=null)
			c+=v.getKapitan().getmRiziko();
	}
}
