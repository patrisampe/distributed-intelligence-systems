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
			ont.read("sid/onto/rius.owl");
			
			//ont.addWaterMass(null);
			ont.loadPermitsRegulations();
			ont.loadLocalizations();
			ont.loadWaterMasses();
			



			System.out.println("RULES:");
			for( RuleTable r:ont.rules.values() ) {
				System.out.println(r);
			}
			System.out.println("Localization");
			
			for( Localization lu:ont.places.values()) {
				System.out.println(lu);
			}
			System.out.println("WaterMasses");
			for( WaterMass wm:ont.waterMasses.values()) {
				System.out.println(wm);
			}

			
			

			OntModel onti= ont.aux();
			
			Individual i = onti.getIndividual(ont.aux2()+"wm2");
			String PREF = ont.aux2();
			System.out.println("nameeee " +i.getLocalName() + "clase " + i.getOntClass().getLocalName());

Localization l = new Localization("Fact1");
Pollutant a = new Pollutant("patri1","unit1", "Lead", 1);

Pollutant c = new Pollutant("patri3","unit3", "Nitrogen", 1);


ArrayList<Pollutant> aux = new ArrayList<Pollutant>();
aux.add(a);

aux.add(c);


WaterMass mw= new  WaterMass(aux, new Vector<WaterMass>(), 20,345,l );
OntClass waterMass = onti.getOntClass(PREF + "WaterMass");
OntClass pr = onti.getOntClass(PREF + "PollutantRelation");
Individual in2 = onti.createIndividual(PREF+"patriwm",waterMass);
Property  L= onti.getProperty(PREF+"hasLocalization");


in2.addLiteral(L, PREF + mw.getPlace().toString());
Property ok = onti.getProperty(PREF+"hasPollutant");
System.out.println("A1 " );
for(Pollutant p:mw.getPollutants()){
	String rri = PREF+UUID.randomUUID();
	Individual ipr = onti.createIndividual(rri,pr);
	System.out.println("A2 " +rri + " maybe "+ ipr.getLocalName());
	Individual rr = onti.getIndividual(PREF+p.getType());
	System.out.println("A3 " +rr.getLocalName() +  " " + rr.getOntClass().getLocalName());
	Property po = onti.getProperty(PREF+"pollutantType");
	//System.out.println("A4" );
	ipr.addLiteral(po, rr);			
	//System.out.println("AA5" );
	po = onti.getProperty(PREF+"pollutionAmount");
	//System.out.println("A6" );
	ipr.addLiteral(po, p.getAmount());
	//System.out.println("AA8 " );
	in2.addLiteral(ok, ipr);
	
	System.out.println("AA7 " );
//	in.addLiteral(ok, );
	
}

System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " );
Ontology ont2 = new Ontology();


ont.loadWaterMasses();

System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " );

			
			ok = onti.getProperty(PREF+"existanceTimeEnd");
			i.addLiteral(ok, 123);

			
			System.out.println("nameeee "+i.getProperty(ok));
			
			i.addLiteral(ok, 125);

			
			System.out.println("nameeee "+i.getProperty(ok));
			long ry=126;
		i.addLiteral(ok, ry);
			
			System.out.println("nameeee "+i.getProperty(ok));
			
			ont.write("sid/onto/prova.owl");
			//ont.loadPollutants();
			
			
			
			
			
			ont.loadPermitsRegulations();
			ont.loadLocalizations();
			ont.loadWaterMasses();
			



			System.out.println("RULES:");
			for( RuleTable r:ont.rules.values() ) {
				System.out.println(r);
			}
			System.out.println("Localization");
			
			for( Localization lu:ont.places.values()) {
				System.out.println(lu);
			}
			System.out.println("WaterMasses");
			for( WaterMass wm:ont.waterMasses.values()) {
				System.out.println(wm);
			}

			
			
			
			
			
			
			
		}catch(Exception e) {System.out.println("Err");throw e;}
	}
	
	private String file;
	private OntModel ont;
	private String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
	
	public LinkedHashMap<String,Pollutant> pollutants = new LinkedHashMap<String,Pollutant>();
	public LinkedHashMap<String,WaterMass> waterMasses = new LinkedHashMap<String,WaterMass>();
	public LinkedHashMap<String,Localization> places = new LinkedHashMap<String,Localization>();
	public LinkedHashMap<String,RuleTable> rules = new LinkedHashMap<String,RuleTable>();

	
	public OntModel aux(){
		return ont;
		
	}
	public String aux2(){
		return PREF;
		
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
		//System.out.println("EEERRR " +s);
		return s.substring(PREF.length(),s.length());
	}
	
	public Pollutant getPollutantRelation(Individual polRel) {
		
		//Busquem tipus i quantiat
		//String rel = s.getObject().toString();
		//Individual polRel = ont.getIndividual(rel);
		Property p = ont.getProperty(PREF+"pollutantType");
		//System.out.println("BABABAB" +polRel.getProperty(p).toString());
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
		return polly;
		//pollutants.put(id,polly);
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
					p.getMaxAllowed().put(pollutant, amount);
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
					double amount = polly.getAmount();
					String pollutant = polly.getType();
					p.getMaxAllowed().put(pollutant, amount);
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
					tp.getPe().put(pollutant, amount);
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
					/*
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
					*/
					Pollutant polly = this.getPollutantRelation(polRel);
					String id = polly.getId();
					pollutants.put(id,polly);
					waterMasses.get(name).getPollutants().add(polly);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeStart") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeStart(l);
					System.out.println(l);
				} else if( s.getPredicate().toString().equals(PREF+"existanceTimeEnd") ){
					Long l = s.getLong();
					waterMasses.get(name).setExistanceTimeEnd(l);
				} else if( s.getPredicate().toString().equals(PREF+"hasLiters") ){
					Double l = s.getDouble();
					waterMasses.get(name).setLiters(l);
				}
			}
			
			
		}
		/*
		for( WaterMass wm1:waterMasses.values()) {
			String id = wm1.getIdentificador();
		}*/
		
	}

	
	
	
	
	
}
