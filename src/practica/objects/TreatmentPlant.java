package practica.objects;

import java.util.LinkedHashMap;

public class TreatmentPlant {
	LinkedHashMap<pollutantTypes,Integer> pe = new LinkedHashMap<>(); //pollution eliminate per unit time

	public LinkedHashMap<pollutantTypes, Integer> getPe() {
		return pe;
	}

	public void setPe(LinkedHashMap<pollutantTypes, Integer> pe) {
		this.pe = pe;
	}

	public TreatmentPlant(LinkedHashMap<pollutantTypes, Integer> pe) {
		this.pe = pe;
	}
	
	public void addType(pollutantTypes pt, Integer i){
		this.pe.put(pt, i);
		
	}
	
	
}
