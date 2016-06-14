package practica;

import java.io.FileReader;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import practica.objects.*;
import java.util.Vector;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Ontology {
	
	
	
	public static void main(String[] args) throws Exception {
		try {
			Ontology ont = new Ontology();
			ont.read("onto/rius.owl");
			//ont.loadPollutants();
		}catch(Exception e) {System.out.println("Err");throw e;}
	}
	
	
	private OntModel ont;
	private String PREF = "http://www.semanticweb.org/miquel.jubert/ontologies/2016/3/riusSID#";
	LinkedHashMap<String,Pollutant> pollutants = new LinkedHashMap<String,Pollutant>();

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
			FileReader frd = new FileReader(dont);
			this.ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
			this.ont.read(frd,null,"RDF/XML");
			
			System.out.println(getClassFromLabel("WaterMass"));
			Iterator<Individual> it = ont.listIndividuals();
			while(it.hasNext()) {
				System.out.println(it.next().getLocalName());
			}
			System.out.println(ont.getOntClass(PREF+"WaterMass@en"));
			
			} catch (Exception e) {throw e;}
	}
	
	public Class getClass( String name ) {
		return null;
	}
	
	public void loadPollutants()
	{
		Individual instancia = ont.getIndividual(PREF+"Nitrogen");
		OntClass classe = ont.getOntClass(PREF+"Pollutant");
		System.out.println(instancia.getOntClass().equals(classe));
	}

	
	
	
	
	
}
