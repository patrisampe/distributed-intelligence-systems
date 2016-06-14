package practica.objects;

import java.util.LinkedHashMap;

public class RuleTable {

	Integer id;
	
	LinkedHashMap<pollutantTypes,Double> maxAllowed = new LinkedHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkedHashMap<pollutantTypes, Double> getMaxAllowed() {
		return maxAllowed;
	}

	public void setMaxAllowed(LinkedHashMap<pollutantTypes, Double> allowed) {
		this.maxAllowed = allowed;
	}

	public Double getMaxAmountPollutant(pollutantTypes pt){
		
		return maxAllowed.get(pt);		
	}

	public Boolean compliant( Pollutant p ) {
		
		if( ( this.maxAllowed.containsKey(p.getType())) && (p.getAmount() < this.maxAllowed.get(p.getType())))
			return true;
		return false;
	}
	
	public RuleTable(Integer id, LinkedHashMap<pollutantTypes, Double> maxAllowed,
			LinkedHashMap<pollutantTypes, Double> minAllowed) {
		this.id = id;
		this.maxAllowed = maxAllowed;
	} 
	
	
}
