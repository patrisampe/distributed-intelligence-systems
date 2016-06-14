package practica;

import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.Collection;
import java.util.Collection;
import java.util.Vector;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

import practica.objects.*;
import java.util.Vector;


public class Methods {
	public WaterMass mergeWaterMasses( Vector<WaterMass> wms )
	{
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();
		double amount = 0.;
		long currTime = java.lang.System.currentTimeMillis();
		for( WaterMass wm:wms) {
			for( Pollutant p:wm.getPollutants() ){
				if(lm.get(p.getType()) == null ) lm.put(p.getType(), new Pollutant(p) );
				lm.get(p.getType()).incAmount(p.getAmount());
			}
			amount += wm.getLiters();
		}
		
		return new WaterMass(new ArrayList<Pollutant>(lm.values()),new Vector<WaterMass>(wms),amount);
	}
	

	public WaterMass generateWaterMass( ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		return new WaterMass( pollutants, originMass, liters, existanceTime,l);
	}
	

	
	public Double calculateTime(WaterMass wm, TreatmentPlant tp, RuleTable p){

		
		long time = 0;		
		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed = p.getMaxAmountPollutant( po.getType() );
		    		
		    Double unitpertime = tp.amountPollutant(po.getType());
		    
		    Double diff= (po.getAmount()/wm.getLiters()) - allowed;
		    
		    if( !p.compliant(po) ){
		    	long timeaux=Math.round(diff/unitpertime);
		    	
		    	if(timeaux>time){
		    		time=timeaux;   		
		    	}
		    }
		}
		return time*wm.getLiters();
	}
	
	
	public WaterMass depureMass(WaterMass wm, TreatmentPlant tp, RuleTable p,long existanceTime){
		
		
		Double time = calculateTime(wm,tp,p);	
		
		ArrayList<Pollutant> poNew = new ArrayList<>();

		for( Pollutant po:wm.getPollutants() ){
			
		    Double unitpertime=tp.amountPollutant(po.getType());
		    Double newAmount= po.getAmount() - unitpertime*time;
		    
		    Pollutant newPo = new Pollutant("pollutant"+UUID.randomUUID(),po.getUnit(),po.getType(),newAmount);
		    poNew.add(newPo);
		    
		}
		Vector<WaterMass> originMass = wm.getOriginMass();
		originMass.add(wm);
		return new WaterMass(poNew,originMass, wm.getLiters(),existanceTime,tp);
	}
	
	
	public boolean permited(WaterMass wm, RuleTable p){
		
		
	
		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed =p.getMaxAmountPollutant(po.getType());
		    
		    if(allowed < po.getAmount()) return false;
		    
		}
		return true;
	}
	
	public RuleTable whatpermision(WaterMass wm, Vector<RuleTable> vp){
		
		for( RuleTable p:vp){
			
		    if(permited(wm,p))return p;
		    
		}
		return null;
	}
	

	private LinkedHashMap<String,Pollutant> pollutantsCaused(WaterMass wm, RuleTable r){

		
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();

		for( Pollutant po:wm.getPollutants() ){
		    //revisar, retorno els que no compleixen?
		    if( !r.compliant( po )) lm.put(po.getType(), po);
		    
		}
		
		return lm;
		
	}
	

	public boolean breaksRegulation(WaterMass wm, RuleTable r, LinkedHashMap<String,Pollutant> whatPollutans){
		
		for(Pollutant po:wm.getPollutants()){
			if(whatPollutans.get(po.getType()) != null ){
				if(!r.compliant(po)) return true;
			}
		}
		
		return false;
	}
	
	public Vector<WaterMass> needInspeccion(WaterMass wm, RuleTable p){
		
		//wm se suposo que esta regulada per permission p pero no ho esta per algun motiu, hem de trobar quin es
		
		LinkedHashMap<String,Pollutant> whatPollutans=pollutantsCaused(wm,p);
		
		Vector<WaterMass> wpaux = new Vector<WaterMass> ();
		
		for(WaterMass aw:wm.getOriginMass()){
			if(breaksRegulation(aw,p,whatPollutans)) wpaux.add(aw);
		}
		
		return wpaux;
	}
	
	
	public WaterMass mostProbablyGuilted(WaterMass wm, RuleTable p){
		
	    LinkedHashMap<String,Pollutant> whatPollutans=pollutantsCaused(wm,p);
		
		Double amountilegal=0.0;
		WaterMass m= null;
		
		for(WaterMass aw:wm.getOriginMass()){
			
			Double auxamount = 0.0;
			for(Pollutant po:aw.getPollutants()){
				Double allowed =p.getMaxAmountPollutant(po.getType());
				
				if(whatPollutans.get(po.getType()) != null ){	
					if( p.compliant( po) ) auxamount += (po.getAmount()-allowed);
				}
			}
			
			if(auxamount>amountilegal)m=aw;
		}
		
		return m;
	}
	
	
	
}
