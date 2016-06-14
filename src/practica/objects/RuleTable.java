package practica.objects;

import java.util.LinkedHashMap;

public class RuleTable {

	Integer id;
	
	LinkedHashMap<String,Double> maxAllowed = new LinkedHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkedHashMap<String, Double> getMaxAllowed() {
		return maxAllowed;
	}

	public void setMaxAllowed(LinkedHashMap<String, Double> allowed) {
		this.maxAllowed = allowed;
	}

	public Double getMaxAmountPollutant(String pt){
		
		return maxAllowed.get(pt);		
	}

	public Boolean compliant( Pollutant p ) {
		
		if( ( this.maxAllowed.containsKey(p.getType())) && (p.getAmount() < this.maxAllowed.get(p.getType())))
			return true;
		return false;
	}
	
	public RuleTable(Integer id, LinkedHashMap<String, Double> maxAllowed,
			LinkedHashMap<String, Double> minAllowed) {
		this.id = id;
		this.maxAllowed = maxAllowed;
	} 
	
	
}
