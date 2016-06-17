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

	public Boolean compliant( Pollutant p, double liters ) {
		
		if( ( this.maxAllowed.containsKey(p.getType())) && ((p.getAmount()/liters) < this.getMaxAmountPollutant(p.getType())))
			return true;
		return false;
	}
	
	public Boolean compliant( WaterMass wm ) {
		Double liters = wm.getLiters();
		for( Pollutant p:wm.getPollutants()) {
			if( !this.compliant(p,liters)) return false;
		}
		return true;
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
