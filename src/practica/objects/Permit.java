package practica.objects;

import java.util.LinkedHashMap;

public class Permit {

	Integer id;
	
	LinkedHashMap<pollutantTypes,Double> allowed = new LinkedHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkedHashMap<pollutantTypes, Double> getAllowed() {
		return allowed;
	}

	public void setAllowed(LinkedHashMap<pollutantTypes, Double> allowed) {
		this.allowed = allowed;
	}

	public Double amountPollutant(pollutantTypes pt){
		
		return allowed.get(pt);		
		
	}
	
	public Permit(Integer id, LinkedHashMap<pollutantTypes, Double> allowed) {
		this.id = id;
		this.allowed = allowed;
	} 
	
	
}
