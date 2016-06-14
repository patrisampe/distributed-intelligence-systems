package practica.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;



public class WaterMass {
	public String identificador;
	private Localization place;
    private ArrayList<Pollutant> pollutants;
	private Vector<WaterMass> originMass;
	private Vector<WaterMass> sonMass;//Pot ser null nomes creada per els raonadors, no en fem res
	private Long existanceTimeStart;
	private Long existanceTimeEnd;
	private double liters;
	
	
	
	public WaterMass(String identificador) {
		super();
		this.identificador = identificador;
		pollutants = new ArrayList<Pollutant>();
		originMass = new Vector<WaterMass>();
		sonMass = new Vector<WaterMass>();
	}

	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
		this.place = l;
	}	
	
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
	}
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,
			long existanceTimeStart, long existanceTimeEnd) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTimeStart;
		this.existanceTimeEnd = existanceTimeEnd;
		this.liters = liters;
	}
	public WaterMass(ArrayList<Pollutant> arrayList, Vector<WaterMass> originMass, double liters) {
		super();
		this.pollutants = arrayList;
		this.originMass = originMass;
		this.liters = liters;
		this.existanceTimeStart = java.lang.System.currentTimeMillis();
		this.existanceTimeEnd = null;
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
	public Vector<WaterMass> getSonMass() {
		return sonMass;
	}
	public void setSonMass(Vector<WaterMass> sonMass) {
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
	public void pushSonMass( WaterMass w ){
		this.sonMass.addElement(w);
	}
	public void pushPollutant( Pollutant p ){
		this.pollutants.add(p);
	}
	
}
