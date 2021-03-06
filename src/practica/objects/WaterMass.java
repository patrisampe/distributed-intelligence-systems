package practica.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.Vector;



public class WaterMass {
	public String identificador;
	private Localization place;
    private ArrayList<Pollutant> pollutants;
	private Vector<WaterMass> originMass;
	private WaterMass sonMass;//Pot ser null nomes creada per els raonadors, no en fem res
	private Long existanceTimeStart;
	private Long existanceTimeEnd;
	private double liters;
	
	
	

	@Override
	public String toString() {
		return "WaterMass [identificador=" + identificador + ", place=" + place + ", pollutants=" + pollutants
				+ ", existanceTimeStart=" + existanceTimeStart + ", existanceTimeEnd=" + existanceTimeEnd + ", liters="
				+ liters + "]";
	}

	public WaterMass(String identificador) {
		super();
		this.identificador = identificador;
		pollutants = new ArrayList<Pollutant>();
		originMass = new Vector<WaterMass>();
		sonMass = null;
	}

	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		this.identificador= "wm" +UUID.randomUUID().toString();
		sonMass = null;
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
		this.place = l;
	}	
	

	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime,long endTime, Localization l ) {
		this.identificador= "wm" +UUID.randomUUID().toString();
		sonMass = null;
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = endTime;
		this.liters = liters;
		this.place = l;
	}	
	
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime ) {
		this.identificador= "wm" +UUID.randomUUID().toString();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
		sonMass = null;
	}
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,
			long existanceTimeStart, long existanceTimeEnd) {
		
		
		this.identificador= "wm" +UUID.randomUUID().toString();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTimeStart;
		this.existanceTimeEnd = existanceTimeEnd;
		this.liters = liters;
		sonMass = null;
	}
	public WaterMass(ArrayList<Pollutant> arrayList, Vector<WaterMass> originMass, double liters) {
		this.identificador= "wm" +UUID.randomUUID().toString();
		
		this.pollutants = arrayList;
		this.originMass = originMass;
		this.liters = liters;
		this.existanceTimeStart = java.lang.System.currentTimeMillis();
		this.existanceTimeEnd = null;
		sonMass = null;
	}
	public Localization getPlace() {
		return place;
	}
	public void setPlace(Localization place) {
		this.place = place;
	}
	public ArrayList<Pollutant> getPollutants() {
		return pollutants;
	}

	public void setPollutants(ArrayList<Pollutant> pollutants) {

		this.pollutants = pollutants;
	}
	
	
//this.existanceTimeStart = java.lang.System.currentTimeMillis();
	public Vector<WaterMass> getOriginMass() {
		return originMass;
	}
	public void setOriginMass(Vector<WaterMass> originMass) {
		this.originMass = originMass;
	}
	public WaterMass getSonMass() {
		return sonMass;
	}
	public void setSonMass(WaterMass sonMass) {
		this.sonMass = sonMass;
	}
	public Long getExistanceTimeStart() {
		return existanceTimeStart;
	}
	public Long getExistanceTimeEnd() {
		return existanceTimeEnd;
	}

	public double getLiters() {
		return liters;
	}
	public void setLiters(double liters) {
		this.liters = liters;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public void setExistanceTimeStart(Long existanceTimeStart) {
		this.existanceTimeStart = existanceTimeStart;
	}

	public void setExistanceTimeEnd(Long existanceTimeEnd) {
		this.existanceTimeEnd = existanceTimeEnd;
	}
	
	public void pushOriginMass( WaterMass w ){
		this.originMass.addElement(w);
	}

	public void pushPollutant( Pollutant p ){
		this.pollutants.add(p);
	}
	
}
