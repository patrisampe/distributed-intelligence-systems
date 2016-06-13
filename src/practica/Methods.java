package practica;

import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.Collection;

import practica.objects.*;

public class Methods {
	public WaterMass mergeWaterMasses( Vector<WaterMass> wms )
	{
		LinkedHashMap<pollutantTypes,Pollutant> lm = new LinkedHashMap<>();
		double amount = 0.;
		for( WaterMass wm:wms) {
			for( Pollutant p:wm.getPollutants() ){
				if(lm.get(p.getType()) == null ) lm.put(p.getType(), new Pollutant(p) );
				lm.get(p.getType()).incAmount(p.getAmount());
			}
			amount += wm.getLiters();
		}
		
		return new WaterMass(lm.values(),new Vector<WaterMass>(wms),amount);
	}
	
	public WaterMass generateWaterMass( Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		return new WaterMass( pollutants, originMass, liters, existanceTime,l);
	}
	
	
	public long calculateTime(WaterMass wm, TreatmentPlant tp, Permisions p){
		
		long time = 0;		
		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed =p.amountPollutant(po.getType());
		    		
		    Double unitpertime=tp.amountPollutant(po.getType());
		    
		    Double diff= po.getAmount() - allowed;
		    
		    if(diff>0){
		    	long timeaux=Math.round(diff/unitpertime);
		    	
		    	if(timeaux>time){
		    		time=timeaux;   		
		    	}
		    }
		}
		return time;
	}
	
	
	public WaterMass depureMass(WaterMass wm, TreatmentPlant tp, Permisions p,long existanceTime){
		
		
		long time = calculateTime(wm,tp,p);	
		
		Collection<Pollutant> poNew = null;
		
		for( Pollutant po:wm.getPollutants() ){
			
		    		
		    Double unitpertime=tp.amountPollutant(po.getType());
		    Double newAmount= po.getAmount() - unitpertime*time;
		    
		    Pollutant newPo = new Pollutant(po.getUnit(),po.getType(),newAmount);
		    
		    poNew.add(newPo);
		    
		}
		Vector<WaterMass> originMass = wm.getOriginMass();
		originMass.add(wm);
		return new WaterMass(poNew,originMass, wm.getLiters(),existanceTime,tp);
	}
	
	
	public boolean permited(WaterMass wm, Permisions p){
		
		
	
		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed =p.amountPollutant(po.getType());
		    
		    if(allowed < po.getAmount())return false;
		    
		}
		return true;
	}
	
	public Permisions whatpermision(WaterMass wm, Vector<Permisions> vp){
		
		for( Permisions p:vp){
			
		    if(permited(wm,p))return p;
		    
		}
		return null;
	}
	
	private LinkedHashMap<pollutantTypes,Pollutant> pollutantsCaused(WaterMass wm, Permisions p){
		
		LinkedHashMap<pollutantTypes,Pollutant> lm = new LinkedHashMap<>();

		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed =p.amountPollutant(po.getType());
		    
		    if(allowed < po.getAmount())lm.put(po.getType(), po);
		    
		}
		
		return lm;
		
	}
	
	public boolean existsIrregulation(WaterMass aw, Permisions p, LinkedHashMap<pollutantTypes,Pollutant> whatPollutans){
		
		for(Pollutant po:aw.getPollutants()){
			
			if(whatPollutans.get(po.getType()) != null ){
				Double allowed =p.amountPollutant(po.getType());	
				if(allowed < po.getAmount())return true;
			}
		}
		
		return false;
	}
	
	public Vector<WaterMass> needInspeccion(WaterMass wm, Permisions p){
		
		//wm se suposo que esta regulada per permission p pero no ho esta per algun motiu, hem de trobar quin es
		
		LinkedHashMap<pollutantTypes,Pollutant> whatPollutans=pollutantsCaused(wm,p);
		
		Vector<WaterMass> wpaux = new Vector<WaterMass> ();
		
		for(WaterMass aw:wm.getOriginMass()){
			if(existsIrregulation(aw,p,whatPollutans))wpaux.add(aw);
		}
		
		return wpaux;
	}
	
	
	public WaterMass mostProbablyGuilted(WaterMass wm, Permisions p){
		
		//wm se suposo que esta regulada per permission p pero no ho esta per algun motiu, hem de trobar quin es
		
		LinkedHashMap<pollutantTypes,Pollutant> whatPollutans=pollutantsCaused(wm,p);
		
		Double amountilegal=0.0;
		WaterMass m= null;
		
		for(WaterMass aw:wm.getOriginMass()){
			
			Double auxamount = 0.0;
			for(Pollutant po:aw.getPollutants()){
				
				if(whatPollutans.get(po.getType()) != null ){
					Double allowed =p.amountPollutant(po.getType());	
					if(allowed < po.getAmount())auxamount += (po.getAmount()-allowed);
				}
			}
			
			if(auxamount>amountilegal)m=aw;
		}
		
		return m;
	}
	
	
	
}
