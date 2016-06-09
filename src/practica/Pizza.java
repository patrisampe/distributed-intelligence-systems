package practica;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.XSD;
import org.apache.jena.datatypes.xsd.XSDDatatype;


public class Pizza {
	static String PREF = "http://www.co-ode.org/ontologies/pizza/pizza.owl#";
	public static void main(String[] args) {
		try {
			FileReader frd = new FileReader("/home2/users/alumnes/1181389/Downloads/PizzaPatatitas.owl");
			OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
			base.read(frd,null,"RDF/XML");
			/////
			
			/*
			//1
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a class: ");
			String cl = reader.nextLine();
		
			OntClass classe = base.getOntClass(PREF+cl);
			
			Iterator<OntClass> iter = classe.listSubClasses();
			while( iter.hasNext() ) {
				System.out.println(iter.next().getLocalName());
				
			}
			*/
			/*
			//2
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a Class: ");
			String cl = reader.nextLine();
			System.out.println("Enter a Instance: ");
			String ins = reader.nextLine();
			Individual instancia = base.getIndividual(PREF+ins);
			OntClass classe = base.getOntClass(PREF+cl);
			System.out.println(instancia.getOntClass().equals(classe));
			*/
			
			//3
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a Class: ");
			String c1 = reader.nextLine();
			System.out.println("Enter a Class: ");
			String c2 = reader.nextLine();

			OntClass classe1 = base.getOntClass(PREF+c1);
			OntClass classe2 = base.getOntClass(PREF+c2);

			System.out.println(classe1.getSuperClass().equals(classe2.getSuperClass()));
			System.out.println(classe1.getSuperClass().getLocalName());
			System.out.println(classe2.getSuperClass().getLocalName());
			
			
			//base.write(System.out);
		} catch (Exception e) {}
		
	}


}
