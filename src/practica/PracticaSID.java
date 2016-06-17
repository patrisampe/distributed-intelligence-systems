package practica;

import java.util.ArrayList;
import java.util.Vector;

import practica.objects.Localization;
import practica.objects.Pollutant;
import practica.objects.RuleTable;
import practica.objects.WaterMass;

public class PracticaSID {

	public static void main(String[] args) throws Exception  {
		
		
		try {
		Ontology ont = new Ontology();
		ont.read("onto/rius.owl");
		
		ont.loadPermitsRegulations();
		ont.loadLocalizations();
		ont.loadWaterMasses();
		ont.validateTreatmentPlants();
		

		writeOntology(ont);
		writeOptions();
		

		
		
		int r =0;
		while(r!=10){
			
			switch(r){
			
			case 1:
				
				
			case 2:
				
				
			case 3:
			
			}
			
		}
		
		
		ont.write("onto/prova.owl");
		 
		
		
		}catch(Exception e) {System.out.println("Err");throw e;}
		
		
	}
	
	
	private static void writeOntology(Ontology ont){
		

		System.out.println("Escrivim la ontologia :");
		
		System.out.println("RULES:");
		for( RuleTable r:ont.rules.values() ) {
			System.out.println(r.getId());
		}
		System.out.println("Localization");
		
		for( Localization lu:ont.places.values()) {
			System.out.println(lu.getId());
		}
		System.out.println("WaterMasses");
		for( WaterMass wm:ont.waterMasses.values()) {
			System.out.println(wm.getIdentificador());
		}
		
	}
	
	private static void writeOptions(){
		
		System.out.println("Escull la funcio que vulguis fer: ");
		
		System.out.println("1. ");
		
	}
	
	
	
	
	
	
	
}
