package practica.objects;

public class Pollutant {
	private String unit;
	private pollutantTypes type;
	private double amount;
	public Pollutant(String unit, pollutantTypes type, double amount) {
		this.unit = unit;
		this.type = type;
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public pollutantTypes getType() {
		return type;
	}
	public void setType(pollutantTypes type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
