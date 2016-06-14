package practica.objects;

import java.util.LinkedHashMap;

public class RuleTable {

	String id;
	
	LinkedHashMap<String,Double> maxAllowed = new LinkedHashMap<>();


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
	
	public RuleTable(String id) {
		this.id = id;
	} 
	
	
}
