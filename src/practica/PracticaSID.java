package practica;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import practica.objects.Localization;
import practica.objects.Pollutant;
import practica.objects.RuleTable;
import practica.objects.WaterMass;

public class PracticaSID {

	public static void main(String[] args) throws Exception  {
		
		
		try {
		Ontology ont = new Ontology();
		Scanner entrada=new Scanner(System.in);
		System.out.println("Escriu path absolut de la ontologia");
		
		String path=entrada.nextLine();
		
		System.out.println("prova " +path);
		
		ont.read(path);
		
		ont.loadPermitsRegulations();
		ont.loadLocalizations();
		ont.loadWaterMasses();
		ont.validateTreatmentPlants();
		

		writeOntology(ont);
		writeOptions();
		
		Integer r = Integer.parseInt(entrada.nextLine());
		
	
		while(r!=10){
			
			switch(r){
			
			
			case 1: 
			{
				System.out.println("Afegeix un identificadors, per les masses per fer el merge. Mentre que afegeixis identificador diferents a 'fi' s'afegiran aquestes masses d'aigua com masses per fer el merge");
				
				
				String id = entrada.nextLine();
				Vector<WaterMass> og = new Vector<>();
				
				while(!id.equals("fi")){
					og.addElement(ont.waterMasses.get(id));
					id = entrada.nextLine();
				}
				
				System.out.println(" El temps de la massa sera l'actual ");
				
				
				System.out.println("La massa d'aigua resultant es");
				
				Methods.proofmergeWaterMasses(og).toString();
				
				break;
			}
				
				
			case 2:
			{
				System.out.println("Afegeix un identificadors, per les masses per fer el merge. Mentre que afegeixis identificador diferents a 'fi' s'afegiran aquestes masses d'aigua com masses per fer el merge");
				
				
				String id = entrada.nextLine();
				Vector<WaterMass> og = new Vector<>();
				
				while(!id.equals("fi")){
					og.addElement(ont.waterMasses.get(id));
					id = entrada.nextLine();
				}
				
				System.out.println(" El temps de la massa sera l'actual ");
				
				System.out.println("Afegeix una localitzacio");
				
				String loc= entrada.nextLine();
				
				Localization l= ont.places.get(loc);
				
				System.out.println("La massa d'aigua resultant es");
				
				ont.ontoMergeWater(og, l);
				
				
				
				break;
			}	
				
			case 4:{
				
				System.out.println("Afegeix un identificadors, per les masses que seran masses pare. Mentre que afegeixis identificador diferents a 'fi' s'afegiran aquestes masses d'aigua com masses pare");
				
				
				String id = entrada.nextLine();
				Vector<WaterMass> og = new Vector<>();
				
				while(!id.equals("fi")){
					og.addElement(ont.waterMasses.get(id));
					id = entrada.nextLine();
				}
				
				System.out.println(" El temps de la massa sera l'actual ");
				Long t = java.lang.System.currentTimeMillis();
				
				System.out.println("Afegeix una localitzacio");
				
				String loc= entrada.nextLine();
				
				Localization l= ont.places.get(loc);
				
				System.out.println("Afegeix litres");
				
				Integer lit= Integer.parseInt(entrada.nextLine());
				
				System.out.println("Afegeix parelles de (identificador, quantitat), pels contaminants de la massa. Mentre que afegeixis identificador diferents a 'fi' s'afegiran com contaminants d'aquesta massa");
				
				String idp = entrada.nextLine();
				ArrayList<Pollutant> p = new ArrayList<>();
				
				while(!idp.equals("fi")){
					og.addElement(ont.waterMasses.get(id));
					idp = entrada.nextLine();
				}
				
				
				System.out.println("La massa d'aigua resultant es");
				
				ont.ontoMergeWater(og, l);
				
				
				
				break;
				
				
			}
				
				
			
			}
			
		}
		
		
		ont.write("onto/prova.owl");
		 
		
		
		}catch(Exception e) {System.out.println("Err");throw e;}
		
		
	}
	
	
	private static void writeOntology(Ontology ont){
		

		System.out.println("Escrivim la ontologia :");
		
		System.out.println("RULES:");
		for( RuleTable r:ont.rules.values() ) {
			System.out.println(r.toString());
		}
		System.out.println("Localization");
		
		for( Localization lu:ont.places.values()) {
			System.out.println(lu.toString());
		}
		System.out.println("WaterMasses");
		for( WaterMass wm:ont.waterMasses.values()) {
			System.out.println(wm.toString());
		}
		
	}
	
	private static void writeOptions(){
		
		System.out.println("Escull la funcio que vulguis fer: ");
		
		System.out.println("1. provem d'unificar dues masses d'aigua sense modificar ontologia ");
		
	//	System.out.println("2. unifiquem dues masses d'aigua modificant ontologia donant el temps");
		
		System.out.println("3. unifiquem dues masses d'aigua modificant ontologia sense donar el temps");
		
//r		System.out.println("4. provem de generar una massa d'aigua sense modificar l'ontologia");
		
		System.out.println("5. Generem una massa d'aigua modificant ontologia");
	
		System.out.println("6. Generem una massa d'aigua d'un proceso industrial");
		
		System.out.println("7. Calculem temps que tarda en depurar una massa d'aigua en una depuradora concreta");
		
		System.out.println("8. Provem depurar una massa d'aigua sense modificar ontologia");
		
		System.out.println("9. Depurar una massa d'aigua modificant ontologia");
		
		System.out.println("10. Totes les depuradores en qualsevol moment no superen la seva capacitat maxima? ");
		
		System.out.println("11. Donat una massa d'aigua et diu les masses d'aigua que necessiten inspeccio, ja que els seus pares estan nets o be no te pares, si ella esta contaminada");
		
		System.out.println("12. Donat una massa d'aigua et diu la massa d'aigua que es mes probable que sigui culpable de la contaminacio, ja que els seus pares estan nets o be no te pares, si ella esta contaminada");
		
		System.out.println("13. Donat una massa d'aigua et diu la massa d'aigua que es mes probable que sigui culpable de la contaminacio, ja que els seus pares estan nets o be no te pares, si ella esta contaminada");
		
		System.out.println("14. Eficiencia d'una planta de tractament");
		
		
		
		//generateWaterMass
		
		//calculateTime
		
		
	}
	
	
	
	
	
	
	
}
