package practica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.Vector;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Property;

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


	public static void main(String[] args) throws Exception {
		try {
			Ontology ont = new Ontology();
			ont.read("onto/rius.owl");
			
			//ont.addWaterMass(null);
			ont.loadWaterMasses();
			
			
			
			ont.write("onto/prova.owl");
			//ont.loadPollutants();
		}catch(Exception e) {System.out.println("Err");throw e;}
	}
	
	/*
	public String identificador;
	private Localization place;
    private ArrayList<Pollutant> pollutants;
	private Vector<WaterMass> originMass;
	private Vector<WaterMass> sonMass;//Pot ser null nomes creada per els raonadors, no en fem res
	private Long existanceTimeStart;
	private Long existanceTimeEnd;
	private double liters;
	*/
	public void addWaterMass(String id,WaterMass mw) {
		OntClass waterMass = ont.getOntClass(PREF + "WaterMass");
		OntClass pr = ont.getOntClass(PREF + "PollutantRelation");
		Individual in = ont.createIndividual(PREF+id,waterMass);
		Property  L= ont.getProperty(PREF+"hasLocalization");
		in.addLiteral(L, mw.getPlace().toString());
		Property ok = ont.getProperty(PREF+"hasPollutant");
		for(Pollutant p:mw.getPollutants()){
			Individual ipr = ont.createIndividual(PREF+UUID.randomUUID(),pr);
			
			Individual rr = ont.getIndividual(PREF+p.getType());
			
			Property po = ont.getProperty(PREF+"pollutantType");
			ipr.addLiteral(po, rr);			
			po = ont.getProperty(PREF+"pollutionAmount");
			ipr.addLiteral(po, p.getAmount());

			ok.addLiteral(ok, ipr);
			
			
		//	in.addLiteral(ok, );
			
		}
		
		//Property o = ont.getProperty(PREF+Property);
		//in.addLiteral(ok, updated);
		
		
		//OntClass oc = this.getClassFromLabel("WaterMass");
		
	}
	
	public void updateWaterMass(String id, String Property, long updated){
		
		Individual in = ont.getIndividual(PREF+id);

		Property pr = ont.getProperty(PREF+Property);
		in.addLiteral(pr, updated);
		
		
		
	//	ont.get
	}
	
	public void ontoMergeWater(Vector<WaterMass> wms ){
		WaterMass mw=Methods.mergeWaterMasses(wms);
		
		for(WaterMass w: wms){
			updateWaterMass(w.getIdentificador(),"existanceTimeEnd",w.getExistanceTimeEnd());
		}
		
		
	/*	
	    OntClass watermassClass = model.getOntClass(NamingContext+"Water_mass");
		        Individual particularWatermass = watermassClass.createIndividual(NamingContext+"water_mass_"+ UUID.randomUUID());
		        Property volume = model.getProperty(NamingContext+"hasVolume");
		        Property dbo = model.getProperty(NamingContext+"hasDBO");
		        Literal vol = model.createTypedLiteral(new Float(w.volume));
		        Literal d = model.createTypedLiteral(new Float(w.dbo));
		        particularWatermass.addLiteral(volume, vol);
		        particularWatermass.addLiteral(dbo, d);
*/
		
		
		
	}
	
	
	
}
