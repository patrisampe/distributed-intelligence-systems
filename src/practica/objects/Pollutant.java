package practica.objects;

import java.util.UUID;

public class Pollutant {
	private String unit;
	private String type;
	private String id;
	private double amount;
	
	public String toString()
	{
		return this.amount + " " + this.unit + " of " + this.type;
	}
	
	public Pollutant(String id,String unit, String type, double amount) {
		this.id = id;
		this.unit = unit;
		this.type = type;
		this.amount = amount;
	}
	public Pollutant(String unit, String type, double amount) {
		this.id = UUID.randomUUID().toString();
		this.unit = unit;
		this.type = type;
		this.amount = amount;
	}
	
	public Pollutant(Pollutant p) {
		this.id = UUID.randomUUID().toString();
		this.unit = p.unit;
		this.type = p.type;
		this.amount = p.amount;
	}
	public String getId() {
		return this.id;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void incAmount(double amount){
		
		this.amount += amount;
	}
	

}
