package practica.objects;

import java.util.LinkedHashMap;

public class TreatmentPlant extends Localization{
	LinkedHashMap<pollutantTypes,Double> pe = new LinkedHashMap<>(); //pollution eliminate per unit time
	
	public LinkedHashMap<pollutantTypes, Double> getPe() {
		return pe;
	}

	public void setPe(LinkedHashMap<pollutantTypes, Double> pe) {
		this.pe = pe;
	}

	public TreatmentPlant(LinkedHashMap<pollutantTypes, Double> pe) {
		this.pe = pe;
	}
	
	public void addType(pollutantTypes pt, Double i){
		this.pe.put(pt, i);
		
	}
	
	public Double amountPollutant(pollutantTypes pt){
		
		return pe.get(pt);		
		
	}
	
	
}
