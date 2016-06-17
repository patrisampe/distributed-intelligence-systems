package practica.objects;

import java.util.LinkedHashMap;

public class TreatmentPlant extends Localization{
	private LinkedHashMap<String,Pollutant> pe = new LinkedHashMap<>(); //pollution eliminate per unit time per liter
	private Double maxWater = 1000000.;//Per defecte
	
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
		String type = pt.getType();
		for( Pollutant p:pe.values()) {
			if(type.equals(pt.getType())) return p.getAmount();
		}
		return 0.;
		
	}
	public String toString() {
		return super.toString() +" "+ pe.toString();
	}
	
}
