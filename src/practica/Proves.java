package practica;
import practica.objects.*;
import java.util.UUID;
import java.util.Vector;


public class Proves{
	public static void main( String[] args ) throws Exception {
		try {
			Ontology o = new Ontology();
			o.read("onto/rius.owl");
			String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
			o.setPREF(PREF);
			//Modificacions a la ontologia
			Pollutant p1 = new Pollutant("gr","Lead",5000.);
			Pollutant p2 = new Pollutant("gr","Lead",20000.);
			WaterMass wm1 = new WaterMass(UUID.randomUUID().toString());
			wm1.getPollutants().add(p1);
			wm1.setLiters(10000);
			wm1.setExistanceTimeStart(new Long(0));
			wm1.setPlace(o.places.get("Fact1"));
			WaterMass wm2 = new WaterMass(UUID.randomUUID().toString());
			wm2.getPollutants().add(p2);
			wm2.setLiters(5000);
			wm2.setExistanceTimeStart(new Long(0));
			wm2.setPlace(o.places.get("Fact2"));
			Vector<WaterMass> vw = new Vector<WaterMass>();
			vw.add(wm1);vw.add(wm2);
			Localization loc = o.places.get("Riu1");
			o.addWaterMass(wm1);
			o.addWaterMass(wm2);
			o.ontoMergeWater(vw, loc);
			WaterMass wm3 = null;
			for( WaterMass wmi:o.waterMasses.values()) {
				for(WaterMass wmj:wmi.getOriginMass()){
					if( wmj == wm1 ) wm3 = wmi;
				}
			}
			
			o.ClassifyWaterMasses();
			TreatmentPlant tp = (TreatmentPlant)o.places.get("TreatPlant1");
			//System.out.println(o.places.values());
			RuleTable rt = o.rules.get("Regulation1");
			o.ontodepureMass(wm3, tp, rt,10000);
			
			//System.out.println(Methods.calculateTime(wm3, tp, rt));
			
			o.ontogenerateWaterMass(10, (Factory) o.places.get("Fact1"), 6666.);
			
			o.write("onto/prova.owl");
		} catch (Exception e) {
			throw e;
		}
	}
}
