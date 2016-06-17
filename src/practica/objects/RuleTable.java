package practica.objects;

import java.util.LinkedHashMap;

public class RuleTable {

	String id;
	
	LinkedHashMap<String,Pollutant> maxAllowed = new LinkedHashMap<>();

	public String getId()
	{
		return id;
	}

	public LinkedHashMap<String, Pollutant> getMaxAllowed() {
		return maxAllowed;
	}

	public void setMaxAllowed(LinkedHashMap<String, Pollutant> allowed) {
		this.maxAllowed = allowed;
	}

	public Double getMaxAmountPollutant(String pt){
		
		return maxAllowed.get(pt).getAmount();		
	}

	public Boolean compliant( Pollutant p ) {
		
		if( ( this.maxAllowed.containsKey(p.getType())) && (p.getAmount() < this.getMaxAmountPollutant(p.getType())))
			return true;
		return false;
	}
	
	public RuleTable(String id) {
		this.id = id;
	} 
	
	public String toString()
	{
		String r = id+":";
		for(String s:this.maxAllowed.keySet()) {
			r+="{"+s+","+maxAllowed.get(s)+"},";
		}
		return r+"\n";
	}
	
}
