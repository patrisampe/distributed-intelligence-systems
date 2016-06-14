package practica.objects;

import java.util.LinkedHashMap;

public class Permit {

	Integer id;
	
	LinkedHashMap<String,Double> allowed = new LinkedHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkedHashMap<String, Double> getAllowed() {
		return allowed;
	}

	public void setAllowed(LinkedHashMap<String, Double> allowed) {
		this.allowed = allowed;
	}

	public Double amountPollutant(String pt){
		
		return allowed.get(pt);		
		
	}
	
	public Permit(Integer id, LinkedHashMap<String, Double> allowed) {
		this.id = id;
		this.allowed = allowed;
	} 
	
	
}
