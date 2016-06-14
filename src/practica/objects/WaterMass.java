package practica.objects;

import java.util.Collection;
import java.util.Vector;

public class WaterMass {
	String identificador;
	private Localization place;
    private Collection<Pollutant> pollutants;
	private Vector<WaterMass> originMass;
	private Vector<WaterMass> sonMass;//Pot ser null (creada per nosaltres, no raonada)
	private Long existanceTimeStart;
	private Long existanceTimeEnd;
	private double liters;
	
	public WaterMass(Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime, Localization l ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
		this.place = l;
	}	
	
	public WaterMass(Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,long existanceTime ) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTime;
		this.existanceTimeEnd = null;
		this.liters = liters;
	}
	public WaterMass(Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters,
			long existanceTimeStart, long existanceTimeEnd) {
		super();
		this.pollutants = pollutants;
		this.originMass = originMass;
		this.existanceTimeStart = existanceTimeStart;
		this.existanceTimeEnd = existanceTimeEnd;
		this.liters = liters;
	}
	public WaterMass(Collection<Pollutant> pollutants, Vector<WaterMass> originMass, double liters) {
		super();
		this.pollutants = pollutants;
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
	public Collection<Pollutant> getPollutants() {
		return pollutants;
	}this.existanceTimeStart = java.lang.System.currentTimeMillis();
	public void setPollutants(Collection<Pollutant> pollutants) {
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
	public long getExistanceTimeStart() {
		return existanceTimeStart;
	}
	public long getExistanceTimeEnd() {
		return existanceTimeEnd;
	}
	public void setExistanceTimeStart(long existanceTime) {
		this.existanceTimeStart = existanceTime;
	}
	public void setExistanceTimeEnd(long existanceTime) {
		this.existanceTimeEnd = existanceTime;
	}
	public double getLiters() {
		return liters;
	}
	public void setLiters(double liters) {
		this.liters = liters;
	}
	
}
