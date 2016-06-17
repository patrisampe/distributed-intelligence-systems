package practica;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collection;
import java.util.Vector;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

import practica.objects.*;
import java.util.Vector;


public class Methods {
	public static WaterMass proofmergeWaterMasses( Vector<WaterMass> wms )
	{
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();
		double amount = 0.;
	    Long currTime = java.lang.System.currentTimeMillis();
		for( WaterMass wm:wms) {
			for( Pollutant p:wm.getPollutants() ){
				if(lm.get(p.getType()) == null ) lm.put(p.getType(), new Pollutant(p) );
				else lm.get(p.getType()).incAmount(p.getAmount());
			}
			amount += wm.getLiters();
			if(wm.getSonMass() == null){
				 System.out.println("La massa d'aigua amb id "+ wm.getIdentificador() + " ja te fills ");
					
			}
		}
		
		
			return new WaterMass(new ArrayList<Pollutant>(lm.values()),new Vector<WaterMass>(wms),amount,currTime);
	
		}
	
	
	public static WaterMass mergeWaterMasses( Vector<WaterMass> wms ) throws Exception
	{

	     return mergeWaterMasses(wms,java.lang.System.currentTimeMillis());
	}
	
	
	
	public static WaterMass mergeWaterMasses( Vector<WaterMass> wms, Long currTime ) throws Exception
	{
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();
		double amount = 0.;
		for( WaterMass wm:wms) {
			for( Pollutant p:wm.getPollutants() ){
				if(lm.get(p.getType()) == null ) lm.put(p.getType(), new Pollutant(p) );
				else lm.get(p.getType()).incAmount(p.getAmount());
			}
			amount += wm.getLiters();
			if(wm.getSonMass() == null){
				 throw new Exception("La massa d'aigua amb id "+ wm.getIdentificador() + " ja te fills ");
					
			}
			wm.setExistanceTimeEnd(currTime);
		}
		
		
		return new WaterMass(new ArrayList<Pollutant>(lm.values()),new Vector<WaterMass>(wms),amount,currTime);
	}
	

	public static WaterMass generateWaterMass( ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) throws Exception {
		if(l.getClass().equals(TreatmentPlant.class)){
			throw new Exception("si vols generara una massa d'aigua d'una depuradora utilitzar la funcio de depure");
			
		}
		
		return new WaterMass( pollutants, originMass, liters, existanceTime,l);
	}
	

	public static WaterMass generateWaterMass( long existanceTime, Factory f,double liters )  {

		return new WaterMass(new ArrayList<Pollutant>(f.getP().getMaxAllowed().values()),new Vector<WaterMass>(),liters,existanceTime, f );
	}
	
	
	
	public static Double calculateTime(WaterMass wm, TreatmentPlant tp, RuleTable p){

		
		long time = 0;		
		for( Pollutant po:wm.getPollutants() ){
			
		    Double allowed = p.getMaxAmountPollutant( po.getType() );
		    		
		    Double unitpertime = tp.amountPollutant(po);
		    
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
	
	
	public static WaterMass proofdepureMass(LinkedHashMap<String,WaterMass> waterMasses,WaterMass wm, TreatmentPlant tp, RuleTable p,long existanceTime){
		
		if(wm.getSonMass() == null){
			System.out.println("USUARI VOLEM QUE SAPIGA QUE AQUESTA MASSA D'AIGUA JA ES PARE D'ALGUNA MASSA D'AIGUA");
			
		}
		Double hml = howmanyliters(waterMasses,existanceTime,tp);
		if(hml+wm.getLiters()>tp.getMaxWater()){
			System.out.println("NO PODEM AFEGIR AQUESTA NOVA MASSA D'AIGUA PERQUE LA DEPURADORA ESTA AL SEU NIVELL MAXIM");
			
		}
		Double time = calculateTime(wm,tp,p);	
		
		ArrayList<Pollutant> poNew = new ArrayList<>();

		for( Pollutant po:wm.getPollutants() ){
			
		    Double unitpertime=tp.amountPollutant(po);
		    Double newAmount= po.getAmount() - unitpertime*time;
		    
		    Pollutant newPo = new Pollutant("pollutant"+UUID.randomUUID(),po.getUnit(),po.getType(),newAmount);
		    poNew.add(newPo);
		    
		}
		Vector<WaterMass> originMass = wm.getOriginMass();
		originMass.add(wm);
		
		
		long end = (long) (existanceTime+time);
		
		return new WaterMass(poNew,originMass, wm.getLiters(),existanceTime,end,tp);
	}
	
	
	public static Double howmanyliters(LinkedHashMap<String,WaterMass> waterMasses, Long time, Localization t){
		
		Double amout=0.0;
		for(WaterMass w: waterMasses.values()){
			
			if(w.getPlace().equals(t)){
				if(w.getExistanceTimeStart() != null){
					if(w.getExistanceTimeStart().compareTo(time)<=0){
						
						if(w.getExistanceTimeStart() != null){
							if(w.getExistanceTimeStart().compareTo(time)>=0){
								amout+=w.getLiters();
							}
						}
						else amout+=w.getLiters();
					}
					
				}
				
			}
		}
		
		return amout;
		
	}
	
	public static void validTreatmentPlant(LinkedHashMap<String,WaterMass> waterMasses, TreatmentPlant tp) throws Exception{
		for(WaterMass w: waterMasses.values()){

			if(w.getExistanceTimeEnd() != null){
				if(howmanyliters(waterMasses,w.getExistanceTimeStart(),tp)>tp.getMaxWater()){throw new Exception("La planta de tractament en el moment "+ w.getExistanceTimeStart() + " hi ha m�s aigua a la depuradora de la permesa ");};

			}
		}
		
		
		
	}
		
	
	public static WaterMass depureMass(LinkedHashMap<String,WaterMass> waterMasses,WaterMass wm, TreatmentPlant tp, RuleTable p,long existanceTime) throws Exception{
		
		if(wm.getSonMass() == null){
			 throw new Exception("HEU DE POSAR UNA MASSA QUE NO SIGUI PARE DE CAP MASSA");
			
		}
		Double hml = howmanyliters(waterMasses,existanceTime,tp);
		if(hml+wm.getLiters()>tp.getMaxWater()){
			throw new Exception("NO PODEM AFEGIR AQUESTA NOVA MASSA D'AIGUA PERQUE LA DEPURADORA ESTA AL SEU NIVELL MAXIM");
			
		}
		
		
		Double time = calculateTime(wm,tp,p);	
		
		ArrayList<Pollutant> poNew = new ArrayList<>();

		for( Pollutant po:wm.getPollutants() ){
			
		    Double unitpertime=tp.amountPollutant(po);
		    Double newAmount= po.getAmount() - unitpertime*time;
		    
		    Pollutant newPo = new Pollutant("pollutant"+UUID.randomUUID(),po.getUnit(),po.getType(),newAmount);
		    poNew.add(newPo);
		    
		}
		
		Vector<WaterMass> originMass = wm.getOriginMass();
		originMass.add(wm);
		long end = (long) (existanceTime+time);
		
		
		wm.setExistanceTimeEnd(end);
		return new WaterMass(poNew,originMass, wm.getLiters(),existanceTime,end,tp);
	}
	

	


	private static LinkedHashMap<String,Pollutant> pollutantsCaused(WaterMass wm, RuleTable r){

		
		LinkedHashMap<String,Pollutant> lm = new LinkedHashMap<>();

		for( Pollutant po:wm.getPollutants() ){
		    //revisar, retorno els que no compleixen?
		    if( !r.compliant( po )) lm.put(po.getType(), po);
		    
		}
		
		return lm;
		
	}
	

	public static boolean breaksRegulation(WaterMass wm, RuleTable r, LinkedHashMap<String,Pollutant> whatPollutans){
		
		for(Pollutant po:wm.getPollutants()){
			if(whatPollutans.get(po.getType()) != null ){
				if(!r.compliant(po)) return true;
			}
		}
		
		return false;
	}
	

	//Retorna true si ell esta net, fals en cas contrari
	public static boolean needInspection(WaterMass wm, HashSet<WaterMass> swm, Regulation r){
		boolean net = true;
		for( Pollutant p: wm.getPollutants()) {
			if(!r.compliant(p)) {
				net = false;
				break;
			}
		}
		
		if(!net) {
			boolean guilty = true;
			for(WaterMass fm:wm.getOriginMass()) {
				if(!needInspection(fm,swm,r)) {
					guilty = false;
				}
			}
			if(guilty) swm.add(wm);
		}
		return net;
	}
	
	
	public static WaterMass mostProbablyGuiltedaux(WaterMass wm, Regulation p){
		
	    LinkedHashMap<String,Pollutant> whatPollutans=p.getMaxAllowed();
		
	    HashSet<WaterMass> swm= new HashSet<>();
	    
	    if(needInspection(wm,swm,p)){

			Double amountilegal=0.0;
			WaterMass m= null;
			
		
			
			for(WaterMass aw:swm){
				
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
	    return null;
	    
	    
	    
	}
	
	
	
	
	
	public static Double efficiency(TreatmentPlant tp){
		
		Double mean=0.0;
		Integer howmany=0;
		for (Pollutant p: tp.getPe().values() ) {
		    // entry.getValue() is of type User now
			mean+=p.getAmount();
			++howmany;
		}
		return mean/howmany;
		
     }
	
	
	
	
	
}
