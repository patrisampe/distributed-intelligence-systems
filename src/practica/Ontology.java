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
	
	private String file;
	private OntModel ont;
	private String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
	
	LinkedHashMap<String,Pollutant> pollutants = new LinkedHashMap<String,Pollutant>();
	LinkedHashMap<String,WaterMass> waterMasses = new LinkedHashMap<String,WaterMass>();
	LinkedHashMap<String,Localization> places = new LinkedHashMap<String,Localization>();

	
	
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
			/*Codi per exemple
			System.out.println(getClassFromLabel("WaterMass"));
			Iterator<Individual> it = ont.listIndividuals();
			while(it.hasNext()) {
				System.out.println(it.next().getLocalName());
			}
			*/
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
	
	public void addWaterMass(String id) {
		OntClass waterMass = ont.getOntClass(PREF + "WaterMass");
		Individual i = ont.createIndividual(PREF+id,waterMass);
		
		
		//OntClass oc = this.getClassFromLabel("WaterMass");
		
	}
	
	public Class getClass( String name ) {
		return null;
	}
	
	private String removePrefix(String s) {
		return s.substring(PREF.length(),s.length());
	}
	
	public Pollutant getPollutantRelation(Individual polRel) {
		String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					Property p = ont.getProperty(PREF+"pollutantType");
					String poll = removePrefix(polRel.getProperty(p).getObject().toString());
					System.out.println(poll);
					p = ont.getProperty(PREF+"pollutionAmount");
					Double d = polRel.getProperty(p).getDouble();
					//Busquem unitat
					Individual pi = ont.getIndividual(PREF+poll);
					p = ont.getProperty(PREF+"pollutionUnit");
					String unit = pi.getProperty(p).getString();
					String id = removePrefix(polRel.toString());
					System.out.println(id +" "+unit +" "+poll+" "+d);
					Pollutant polly = new Pollutant(id,unit,poll,d);
					pollutants.put(id,polly);
					waterMasses.get(name).getPollutants().add(polly);
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
		

	}
	
	public void loadWaterMasses()//I els pollutants
	{
		OntClass classe = ont.getOntClass(PREF + "WaterMass");
		System.out.println(classe);
		
		Iterator<Individual> iter = ont.listIndividuals(classe);
		
		while( iter.hasNext() ) {
			Individual i = iter.next();
			String name = i.getLocalName();
			System.out.println("Massa:"+name);
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
					System.out.println(ori);
					WaterMass wmo = waterMasses.get(ori);
					waterMasses.get(name).pushOriginMass(wmo);
					waterMasses.get(ori).pushSonMass(wmo);
				} else if( s.getPredicate().toString().equals(PREF+"hasLocalization") ){
					String loc = removePrefix(s.getObject().toString());
					waterMasses.get(name).setPlace(this.places.get(loc));
					System.out.println(loc);
				} else if( s.getPredicate().toString().equals(PREF+"hasPollutant") ){
					//Busquem tipus i quantiat
					String rel = s.getObject().toString();
					Individual polRel = ont.getIndividual(rel);
					Property p = ont.getProperty(PREF+"pollutantType");
					String poll = removePrefix(polRel.getProperty(p).getObject().toString());
					System.out.println(poll);
					p = ont.getProperty(PREF+"pollutionAmount");
					Double d = polRel.getProperty(p).getDouble();
					//Busquem unitat
					Individual pi = ont.getIndividual(PREF+poll);
					p = ont.getProperty(PREF+"pollutionUnit");
					String unit = pi.getProperty(p).getString();
					String id = removePrefix(polRel.toString());
					System.out.println(id +" "+unit +" "+poll+" "+d);
					Pollutant polly = new Pollutant(id,unit,poll,d);
					pollutants.put(id,polly);
					waterMasses.get(name).getPollutants().add(polly);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeStart") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeStart(l);
					System.out.println(l);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeEnd") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeEnd(l);
				}
			}
			
			
		}
		/*
		for( WaterMass wm1:waterMasses.values()) {
			String id = wm1.getIdentificador();
		}*/
		
	}

	
	
	
	
	
}
