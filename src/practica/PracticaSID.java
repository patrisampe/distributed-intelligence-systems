package practica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

import practica.objects.Localization;
import practica.objects.Pollutant;
import practica.objects.Regulation;
import practica.objects.RuleTable;
import practica.objects.TreatmentPlant;
import practica.objects.WaterMass;
import practica.objects.Factory;


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
		
	
		while(r<13){
			
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
				
				
				System.out.println("La massa d'aigua resultant es"+ Methods.proofmergeWaterMasses(og).toString());
				
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
				
				//System.out.println(" El temps de la massa sera l'actual ");
				System.out.println("Introdueix la data en el format: dd-MM-yyyy hh:mm:ss");
				
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				String tiempo= entrada.nextLine();
				
				Date date = ft.parse(tiempo);
				
				Long time = date.getTime();
				
				
				System.out.println("Afegeix una localitzacio");
				
				String loc= entrada.nextLine();
				
				Localization l= ont.places.get(loc);
				
			
				
				//System.out.println("La massa d'aigua resultant es");
				ont.ontoMergeWater(og, l, time);
				//ont.ontoMergeWater(og, l);
				
				System.out.println("Ya s'ha generat la barreja de les masses d'aigua");	
				
				break;
			}	
			case 3:
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
				
			
				
				//System.out.println("La massa d'aigua resultant es");
				
				ont.ontoMergeWater(og, l);
				
				System.out.println("Ya s'ha generat la barreja de les masses d'aigua");	
				
				break;
			}	
				
			case 4:
			{
				
				System.out.println("Afegeix un identificadors, per les masses que seran masses pare. Mentre que afegeixis identificador diferents a 'fi' s'afegiran aquestes masses d'aigua com masses pare");
				
				
				String id = entrada.nextLine();
				Vector<WaterMass> og = new Vector<>();
				
				while(!id.equals("fi")){
					og.addElement(ont.waterMasses.get(id));
					id = entrada.nextLine();
				}
				

				System.out.println("Introdueix la data en el format: dd-MM-yyyy hh:mm:ss");
				
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				String tiempo= entrada.nextLine();
				
				Date date = ft.parse(tiempo);
				
				Long t = date.getTime();
				
				
				System.out.println("Afegeix una localitzacio");
				
				String loc= entrada.nextLine();
				
				Localization l= ont.places.get(loc);
				
				System.out.println("Afegeix litres");
				
				Integer lit= Integer.parseInt(entrada.nextLine());
				
				System.out.println("Afegeix parelles de (identificador, quantitat, unitat (en lineas diferents)), pels contaminants de la massa. Mentre que afegeixis identificador diferents a 'fi' s'afegiran com contaminants d'aquesta massa");
				
				String idp = entrada.nextLine();
				ArrayList<Pollutant> p = new ArrayList<>();
				
				while(!idp.equals("fi")){
					Double quant= Double.parseDouble(entrada.nextLine());
					String unit = entrada.nextLine();
					Pollutant pinst = new Pollutant(unit, idp,quant);
					p.add(pinst);
					idp = entrada.nextLine();
					
				}
				
				ont.ontogenerateWaterMass(p, og, lit,t, l );
					
				
				System.out.println("Ya se generat la massa d'aigua");	

				
				
				
				break;
				
				
			}
			case 5:
			{
				
		System.out.println("Introdueix la data en el format: dd-MM-yyyy hh:mm:ss");
				
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				
				String tiempo= entrada.nextLine();
				
				Date date = ft.parse(tiempo);
				
				Long t = date.getTime();
				
				System.out.println("Afegeix una fabrica");
				
				String loc= entrada.nextLine();
				
				Factory l= (Factory) ont.places.get(loc);
				
				System.out.println("Afegeix litres");
				
				Integer lit= Integer.parseInt(entrada.nextLine());
				
				ont.ontogenerateWaterMass(  t, l,lit );
				
				
				System.out.println("Ya se generat la massa d'aigua");			
				
				
				break;
				
				
			}
			
			case 6:
			{
	
				System.out.println(" Afegeix una water mass ");
				
				String wms= entrada.nextLine();
				
				WaterMass m = ont.waterMasses.get(wms);
				
				
				
				System.out.println("Afegeix una depuradora");
				
				String loc= entrada.nextLine();
				
				TreatmentPlant l= (TreatmentPlant) ont.places.get(loc);
				
				System.out.println("Afegeix un permis");
				
				String ru= entrada.nextLine();
				
				RuleTable rt= ont.rules.get(ru);
				
				
				Double time = Methods.calculateTime(m, l,rt );
				
				System.out.println(" El temps que tarda (en unitats de temps que esta la planta de tractament) es "+ time.toString());
				
				
				break;
				
				
			}
			
			case 7:
			{
	
				System.out.println(" Afegeix una water mass ");
				
				String wms= entrada.nextLine();
				
				WaterMass m = ont.waterMasses.get(wms);
				

				System.out.println("Introdueix la data en el format: dd-MM-yyyy hh:mm:ss");
						
						
						SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				
				String tiempo= entrada.nextLine();
				
				Date date = ft.parse(tiempo);
				
				Long t = date.getTime();
				
				System.out.println("Afegeix una depuradora");
				
				String loc= entrada.nextLine();
				
				TreatmentPlant l= (TreatmentPlant) ont.places.get(loc);
				
				System.out.println("Afegeix un permis");
				
				String ru= entrada.nextLine();
				
				RuleTable rt= ont.rules.get(ru);
				
				ont.ontodepureMass(m, l, rt, t);
				
				System.out.println(" Ya hem depurat la massa d'aigua" );
				
				break;
				
				
			}
			
			
			
			case 8:
			{
	
				ont.validateTreatmentPlants();
				
				System.out.println(" Ya hem validat totes les plantes de tractament" );
				
				break;
				
				
			}
			
			

			case 9:
			{
	
				System.out.println(" Afegeix una water mass ");
				
				String wms= entrada.nextLine();
				
				WaterMass m = ont.waterMasses.get(wms);
				
				
				HashSet<WaterMass> swm= new HashSet<>();
				
				
				System.out.println("Afegeix una regulacio ");
				
				String ru= entrada.nextLine();
				
				Regulation rt= (Regulation)ont.rules.get(ru);
				

				
				Methods.needInspection(m, swm, rt);
				
				System.out.println(" Les masses d'aigua que necessiten inspeccio son " );
				
				for(WaterMass c: swm){
					
					c.toString();
					
				}
				
				break;
				
				
			}
			
			case 10:
			{
	
				System.out.println(" Afegeix una water mass ");
				
				String wms= entrada.nextLine();
				
				WaterMass m = ont.waterMasses.get(wms);
				
				
				
				
				System.out.println("Afegeix una regulacio ");
				
				String ru= entrada.nextLine();
				
				Regulation rt= (Regulation)ont.rules.get(ru);
				

				
				WaterMass result= Methods.mostProbablyGuiltedaux(m, rt);
				
				if(result == null){
					System.out.println("Aquesta massa no esta contaminada");
				}
				else System.out.println(" La massa d'aigua que es mes probablement que ho causi es  "+result.toString() );
				
				
				break;
				
				
			}
			
			case 11:
			{
	
				
				

				System.out.println("Afegeix una depuradora");
				
				String loc= entrada.nextLine();
				
				TreatmentPlant l= (TreatmentPlant) ont.places.get(loc);
				
				Double result=Methods.efficiency(l);
				
				
				
				
				System.out.println(" La eficiencia de la depuradora es "+ result );
				
				
				break;
				
				
			}
			case 12:
			{
				
				System.out.println("FI DE L'APLICACIO! escriu path d'on vols escriurela ");
				String pathi = entrada.nextLine();
				
				ont.write(pathi);
				 
				System.out.println("GRACIES PER UTILITZAR AQUEST PROGRAMA. ENS VEIEM AVIAT!  ");
				System.out.close();
				
			}
			
			}
			writeOptions();
			r = Integer.parseInt(entrada.nextLine());
			
		}
		
		
	//	ont.write("sid/onto/prova.owl");
		 
		
		
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
		
		System.out.println("2. unifiquem dues masses d'aigua modificant ontologia donant el temps");
		
		System.out.println("3. unifiquem dues masses d'aigua modificant ontologia sense donar el temps");
		
		//System.out.println("4. provem de generar una massa d'aigua sense modificar l'ontologia");
		
		System.out.println("4. Generem una massa d'aigua modificant ontologia");
	
		System.out.println("5. Generem una massa d'aigua d'un proceso industrial");
		
		System.out.println("6. Calculem temps que tarda en depurar una massa d'aigua en una depuradora concreta");
		
	//	System.out.println("8. Provem depurar una massa d'aigua sense modificar ontologia");
		
		System.out.println("7. Depurar una massa d'aigua modificant ontologia");
		
		System.out.println("8. Totes les depuradores en qualsevol moment no superen la seva capacitat maxima? ");
		
		System.out.println("9. Donat una massa d'aigua et diu les masses d'aigua que necessiten inspeccio, ja que els seus pares estan nets o be no te pares, si ella esta contaminada");
		
		System.out.println("10. Donat una massa d'aigua et diu la massa d'aigua que es mes probable que sigui culpable de la contaminacio, ja que els seus pares estan nets o be no te pares, si ella esta contaminada");
		
		System.out.println("11. Eficiencia d'una planta de tractament");
		
		System.out.println("12. Acabar i escriure ontologia");
		
		
		//generateWaterMass
		
		//calculateTime
		
		
	}
	
	
	
	
	
	
	
}
