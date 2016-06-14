package practica.objects;

import java.util.LinkedHashMap;

public class TreatmentPlant extends Localization{
	private LinkedHashMap<String,Double> pe = new LinkedHashMap<>(); //pollution eliminate per unit time per liter
	private Double maxWater;
	
	public Double getMaxWater() {
		return maxWater;
	}

	public void setMaxWater(Double maxWater) {
		this.maxWater = maxWater;
	}

	public LinkedHashMap<String, Double> getPe() {
		return pe;
	}

	public void setPe(LinkedHashMap<String, Double> pe) {
		this.pe = pe;
	}

	public TreatmentPlant(String id) {
		super(id);
	}
	
	public void addType(String pt, Double i){
		this.pe.put(pt, i);
		
	}
	
	public Double amountPollutant(String pt){
		
		return pe.get(pt);		
		
	}
	
	
}
