package practica;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.XSD;
import org.apache.jena.datatypes.xsd.XSDDatatype;


public class HelloWorldRDF {

	public static void main(String[] args) {
		
		String NS = "http://hworld.com/";
		
		OntModel onto = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
		OntClass helloWorld = onto.createClass(NS + "HelloWorld");
		DatatypeProperty literal= onto.createDatatypeProperty(NS + "estring");
		literal.addDomain(helloWorld);
		literal.addRange(XSD.xstring);
		Individual i0 = onto.createIndividual(NS + "Hola1",helloWorld);
		i0.addLiteral(literal, "hiproperty");
		
		onto.write(System.out);
	}
	
	
		
}
