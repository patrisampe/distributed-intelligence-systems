package practica;

import java.io.FileReader;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

public class Ontology {
	private OntModel ont;
	
	
	public void read(String dont){
		try {
			FileReader frd = new FileReader(dont);
			ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_TRANS_INF);
			ont.read(frd,null,"RDF/XML");
		} catch (Exception e) {}
	}
	
	public Class getClass( String name ) {
		return null;
	}
	
	
}
