package practica;

import java.util.LinkedHashMap;
import java.util.Vector;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

import practica.objects.Localization;
import practica.objects.Pollutant;
import practica.objects.WaterMass;

public class OntologyAux {

	
	private String file;
	private OntModel ont;
	private String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
	
	LinkedHashMap<String,Pollutant> pollutants = new LinkedHashMap<String,Pollutant>();
	LinkedHashMap<String,WaterMass> waterMasses = new LinkedHashMap<String,WaterMass>();
	LinkedHashMap<String,Localization> places = new LinkedHashMap<String,Localization>();

	
	public void addWaterMass(String id) {
		OntClass waterMass = ont.getOntClass(PREF + "WaterMass");
		Individual i = ont.createIndividual(PREF+id,waterMass);
		
		//OntClass oc = this.getClassFromLabel("WaterMass");
		
	}
	
	public void ontoMergeWater(Vector<WaterMass> wms ){
		WaterMass mw=Methods.mergeWaterMasses(wms);
		
		
		
	}
	
	
	
}
