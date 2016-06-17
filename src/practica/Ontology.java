package practica;

import java.io.FileReader;
import java.io.FileWriter;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import practica.objects.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.UUID;

public class Ontology {
	
	
	private String file;
	private OntModel ont;
	private String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
	
	public LinkedHashMap<String,Pollutant> pollutants = new LinkedHashMap<String,Pollutant>();
	public LinkedHashMap<String,WaterMass> waterMasses = new LinkedHashMap<String,WaterMass>();
	public LinkedHashMap<String,Localization> places = new LinkedHashMap<String,Localization>();
	public LinkedHashMap<String,RuleTable> rules = new LinkedHashMap<String,RuleTable>();

	
		
	public void ClassifyWaterMasses()
	{
		String cPrefix = PREF + "_waterregulatedclass_";
		for(RuleTable rt:this.rules.values()) {
			if(rt.getClass().equals(Regulation.class)) {
				Regulation r = (Regulation)rt;
				OntClass oc = ont.createClass(cPrefix+r);
				for( WaterMass wm: waterMasses.values() ) {
					boolean complies = true;
					if( !r.compliant(wm) ) {
						complies = false;
					}
					if(complies) {
						Individual ind = ont.getIndividual(wm.getIdentificador());
						ind.addOntClass(oc);
					}
				}
			}
		}
		
	}
	
	
	private OntClass getClassFromLabel(String label) {
		Iterator<OntClass> it = ont.listClasses();
		while(it.hasNext()) {
			OntClass loc = it.next();
			if( label.equals(loc.getLabel("en")))
				return loc;
		}
		return null;
	}
	
	public void read(String dont) throws Exception{
		try {
			this.file = dont;
			FileReader frd = new FileReader(dont);
			this.ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
			this.ont.read(frd,null,"RDF/XML");
			} catch (Exception e) {throw e;}
	}
	
	public void writeBack() throws Exception {
		FileWriter out = null;
		try {
		  out = new FileWriter( this.file );
		  ont.write( out, "RDF/XML" );
		} catch (Exception e) {
			throw e;
		}
	}
	public void write(String dont) throws Exception {
		FileWriter out = null;
		try {
		  out = new FileWriter( dont );
		  ont.write( out, "RDF/XML" );
		} catch (Exception e) {
			throw e;
		}
	}
	

	
	
	private String removePrefix(String s) {
		return s.substring(PREF.length(),s.length());
	}
	
	public Pollutant getPollutantRelation(Individual polRel) {
		
		Property p = ont.getProperty(PREF+"pollutantType");
		String poll = removePrefix(polRel.getProperty(p).getObject().toString());
		p = ont.getProperty(PREF+"pollutionAmount");
		Double d = polRel.getProperty(p).getDouble();
		//Busquem unitat
		Individual pi = ont.getIndividual(PREF+poll);
		p = ont.getProperty(PREF+"pollutionUnit");
		String unit = pi.getProperty(p).getString();
		String id = removePrefix(polRel.toString());
		Pollutant polly = new Pollutant(id,unit,poll,d);
		return polly;
	}
	
	public void loadPermitsRegulations()
	{	{
		OntClass classe = ont.getOntClass(PREF + "Permit");
		Iterator<Individual> iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			Permit p = new Permit(id);
			
			StmtIterator it = i.listProperties();
			while ( it.hasNext() ) {
				Statement s = it.next();
				if( s.getPredicate().toString().equals(PREF+"hasRule") ){
					String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					Pollutant polly = this.getPollutantRelation(polRel);
					double amount = polly.getAmount();
					String pollutant = polly.getType();
					p.getMaxAllowed().put(pollutant, polly);
				}
			}
			this.rules.put(id, p);
		}
		}	
		{
		OntClass classe = ont.getOntClass(PREF + "Regulation");
		Iterator<Individual> iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			Regulation p = new Regulation(id);
			
			StmtIterator it = i.listProperties();
			while ( it.hasNext() ) {
				Statement s = it.next();
				if( s.getPredicate().toString().equals(PREF+"hasRule") ){
					String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					Pollutant polly = this.getPollutantRelation(polRel);
					String pollutant = polly.getType();
					p.getMaxAllowed().put(pollutant, polly);
				}
			}
			this.rules.put(id, p);
			
		}
		}
		
	}
	
	public void loadLocalizations()
	{
		OntClass classe = ont.getOntClass(PREF + "Factory");
		Iterator<Individual> iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			this.places.put(id, new Factory(id));
		}
		//////
		classe = ont.getOntClass(PREF + "River");
		iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			Property ok = ont.getProperty(PREF+"km");
			Property on = ont.getProperty(PREF+"name");
			Integer km = i.getProperty(ok).getInt();
			String name = i.getProperty(on).getString();
			this.places.put(id, new River(id,name,km));
		}
		/////////
		classe = ont.getOntClass(PREF + "TreatmentPlant");
		iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			TreatmentPlant tp = new TreatmentPlant(id);
			
			StmtIterator it = i.listProperties();
			while ( it.hasNext() ) {
				Statement s = it.next();
				if( s.getPredicate().toString().equals(PREF+"reducesPollutant") ){
					String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					Pollutant polly = this.getPollutantRelation(polRel);
					double amount = polly.getAmount();
					String pollutant = polly.getId();
					tp.getPe().put(pollutant, polly);
				}
			}
		}
		//////
		classe = ont.getOntClass(PREF + "Sewage");
		iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String id = i.getLocalName();
			this.places.put(id, new Factory(id));
		}
		
	}
	
	
	
	public void loadWaterMasses()//I els pollutants
	{
		waterMasses = new LinkedHashMap<String,WaterMass>();
		OntClass classe = ont.getOntClass(PREF + "WaterMass");
		
		
		
		
		Iterator<Individual> iter = ont.listIndividuals(classe);
		
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String name = i.getLocalName();
			this.waterMasses.put(name, new WaterMass(name));
		}
		iter = ont.listIndividuals(classe);
		while( iter.hasNext() ) {
			//Afegint fills
			Individual i = iter.next();
			String name = i.getLocalName();
			StmtIterator it = i.listProperties();
			while ( it.hasNext() ) {
				Statement s = it.next();
				if( s.getPredicate().toString().equals(PREF+"originMass")) {
					String ori = removePrefix(s.getObject().toString());
					WaterMass wmo = waterMasses.get(ori);
					waterMasses.get(name).pushOriginMass(wmo);
					waterMasses.get(ori).setSonMass(wmo);
				} else if( s.getPredicate().toString().equals(PREF+"hasLocalization") ){
					
					String loc = removePrefix(s.getObject().toString());
					waterMasses.get(name).setPlace(this.places.get(loc));
				} else if( s.getPredicate().toString().equals(PREF+"hasPollutant") ){
					//Busquem tipus i quantiat
					String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					
					Pollutant polly = this.getPollutantRelation(polRel);
					String id = polly.getId();
					pollutants.put(id,polly);
					waterMasses.get(name).getPollutants().add(polly);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeStart") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeStart(l);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeEnd") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeEnd(l);
				} else if( s.getPredicate().toString().equals(PREF+"hasLiters") ){
					Double l = s.getDouble();
					waterMasses.get(name).setLiters(l);
				}
			}
			
			
		}
		
	}

	
	public void addWaterMass(WaterMass mw) {
		String id = mw.getIdentificador();
		OntClass waterMass = ont.getOntClass(PREF + "WaterMass");
		OntClass classPolRelation = ont.getOntClass(PREF + "PollutantRelation");
		Individual instWaterMass = ont.createIndividual(PREF+id,waterMass);
		Property  L= ont.getProperty(PREF+"hasLocalization");

		if(mw.getPlace()!= null){
			Individual instLocation = ont.getIndividual(PREF +mw.getPlace().getId());
	
	
			instWaterMass.addProperty(L, instLocation);
		}
		
		Property  propOM= ont.getProperty(PREF+"originMass");
		
		if(!mw.getOriginMass().isEmpty()){
			
			for(WaterMass ow: mw.getOriginMass()){	
				
				Individual instLocation = ont.getIndividual(PREF +ow.getIdentificador());
				
				
				instWaterMass.addProperty(propOM, instLocation);
			}
		}
		
		Property prHasPollutant = ont.getProperty(PREF+"hasPollutant");

		for(Pollutant p:mw.getPollutants()){
			String urirelation = PREF+UUID.randomUUID();
			Individual instPolRelation = ont.createIndividual(urirelation,classPolRelation);
			
			Individual instPollutant = ont.getIndividual(PREF+p.getType());
			
			Property prPollutantType = ont.getProperty(PREF+"pollutantType");
			
			instPolRelation.addProperty(prPollutantType,  instPollutant);

			Property prPollutantAmount = ont.getProperty(PREF+"pollutionAmount");
			
			instPolRelation.addLiteral(prPollutantAmount, p.getAmount());
			
			instWaterMass.addProperty(prHasPollutant, instPolRelation);

			
		}
		Property po = ont.getProperty(PREF+"hasLiters");
		instWaterMass.addLiteral(po, mw.getLiters());
		 po = ont.getProperty(PREF+"existanceTimeEnd");
		 if(instWaterMass.hasProperty(po)){

			instWaterMass.addLiteral(po, mw.getExistanceTimeStart());
		}
		  po = ont.getProperty(PREF+"existanceTimeEnd");
		if(instWaterMass.hasProperty(po)){
		 
		   instWaterMass.addLiteral(po, mw.getExistanceTimeEnd());
		}
		
		
	}
	
	public void updateWaterMass(String id, String Property, long updated){
		
		Individual in = ont.getIndividual(PREF+id);

		Property pr = ont.getProperty(PREF+Property);
		if(in.hasProperty(pr)){
		    in.removeProperty(pr, in.getPropertyValue(pr));
		}
		in.addLiteral(pr, updated);
		
	}
	
	public void ontoMergeWater(Vector<WaterMass> wms,Localization l ) {
		try{
			WaterMass mw=Methods.mergeWaterMasses(wms);
			mw.setPlace(l);
			for(WaterMass w: wms){
				updateWaterMass(w.getIdentificador(),"existanceTimeEnd",w.getExistanceTimeEnd());
			}
			
			addWaterMass(mw);	
			waterMasses.put(mw.getIdentificador(), mw);
			
		}catch (Exception e) {System.out.println("ERROR :"+e.getMessage());}
		
		
	}
	public void ontoMergeWater(Vector<WaterMass> wms,Localization l , long time) {
		try{
			WaterMass mw=Methods.mergeWaterMasses(wms,time);
			mw.setPlace(l);
			for(WaterMass w: wms){
				updateWaterMass(w.getIdentificador(),"existanceTimeEnd",w.getExistanceTimeEnd());
			}
			
			addWaterMass(mw);	
			waterMasses.put(mw.getIdentificador(), mw);
		}catch (Exception e) {System.out.println("ERROR :"+e.getMessage());}
		
		
	}
	
	public void ontogenerateWaterMass( ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ){
		try{
			WaterMass mw=Methods.generateWaterMass(pollutants, originMass, liters, existanceTime, l);
			addWaterMass(mw);
			waterMasses.put(mw.getIdentificador(), mw);
		}
		catch (Exception e) {System.out.println("ERROR :"+e.getMessage());}
		
	} 
	
	
	public void ontogenerateWaterMass(  long existanceTime, Factory f,double liters ){
	
			WaterMass mw=Methods.generateWaterMass(existanceTime,f,liters);
			addWaterMass(mw);
			waterMasses.put(mw.getIdentificador(), mw);

	} 
	
	
	public void validateTreatmentPlants(){
		
		for(Localization l:places.values()){
			
			if(l.getClass().equals(TreatmentPlant.class)){
				try{
					Methods.validTreatmentPlant(waterMasses, (TreatmentPlant) l);
				}
				catch (Exception e) {System.out.println("ERROR :"+e.getMessage());System.exit(1);}
			}
			
		}
		
	}
	
	public void ontodepureMass(WaterMass w, TreatmentPlant tp, RuleTable p,long existanceTime) {
	    
		
		try {
			WaterMass mwnew=Methods.depureMass(waterMasses,w, tp, p, existanceTime);
		    updateWaterMass(w.getIdentificador(),"existanceTimeEnd",w.getExistanceTimeEnd());
			addWaterMass(mwnew);
			waterMasses.put(mwnew.getIdentificador(), mwnew);
		} 
		catch (Exception e) {System.out.println("ERROR :"+e.getMessage());}
		
		
		
	}



	public OntModel getOnt() {
		return ont;
	}


	public void setOnt(OntModel ont) {
		this.ont = ont;
	}


	public String getPREF() {
		return PREF;
	}


	public void setPREF(String pREF) {
		PREF = pREF;
	} 
	
	
	}
