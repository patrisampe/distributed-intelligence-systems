package practica;

import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Collection;

import practica.objects.*;

public class Methods {
	public WaterMass mergeWaterMasses( Vector<WaterMass> wms )
	{
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();
		double amount = 0.;
		for( WaterMass wm:wms) {
			for( Pollutant p:wm.getPollutants() ){
				if( lm.get(p.getName()) == null ) lm.put(p.getName(),new Pollutant(p) );
				lm.get(p.getName()).incAmount(p.getAmount());
			}
			amount += wm.getLiters();
		}
		
		return new WaterMass(lm.values(),new Vector<WaterMass>(wms),amount);
	}
	
	public WaterMass generateWaterMass( Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		return new WaterMass( pollutants, originMass, liters, existanceTime,l);
	}
	
	
}
