package practica.objects;

import java.util.LinkedHashMap;

public class TreatmentPlant extends Localization{
	private LinkedHashMap<String,Pollutant> pe = new LinkedHashMap<>(); //pollution eliminate per unit time per liter
	private Double maxWater;
	
	public Double getMaxWater() {
		return maxWater;
	}

	public void setMaxWater(Double maxWater) {
		this.maxWater = maxWater;
	}

	public LinkedHashMap<String, Pollutant> getPe() {
		return pe;
	}

	public void setPe(LinkedHashMap<String, Pollutant> pe) {
		this.pe = pe;
	}

	public TreatmentPlant(String id) {
		super(id);
	}
	
	public void addType(String pt, Pollutant i){
		this.pe.put(pt, i);
		
	}
	
	public Double amountPollutant(Pollutant pt){
		
		return pe.get(pt).getAmount();		
		
	}
	public String toString() {
		return super.toString() +" "+ pe.toString();
	}
	
}
