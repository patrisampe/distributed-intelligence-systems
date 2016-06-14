package practica.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import practica.Pollutatnt;

public class WaterMass {
	private Localization place;
    private ArrayList<Pollutant> pollutants;
	private Vector<WaterMass> originMass;
	private Vector<WaterMass> sonMass;//Pot ser null (creada per nosaltres, no raonada)
	private long existanceTime;
	private double liters;
	
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTime = existanceTime;
		this.liters = liters;
		this.place = l;
	}	
	
	public WaterMass(ArrayList<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTime = existanceTime;
		this.liters = liters;
	}
	public WaterMass(ArrayList<Pollutant> arrayList, Vector<WaterMass> originMass, double liters) {
		super();
		this.pollutants = arrayList;
		this.originMass = originMass;
		this.liters = liters;
		this.existanceTime = java.lang.System.currentTimeMillis();
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
	public long getExistanceTime() {
		return existanceTime;
	}
	public void setExistanceTime(long existanceTime) {
		this.existanceTime = existanceTime;
	}
	public double getLiters() {
		return liters;
	}
	public void setLiters(double liters) {
		this.liters = liters;
	}
	
}
