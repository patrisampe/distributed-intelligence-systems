package practica.objects;

public class Pollutant {
	private String unit;
	private String name;
	private double amount;
	
	public Pollutant(Pollutant p)
	{
		this.unit = p.unit;
		this.name = p.name;
		this.amount = 0.;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void incAmount(double amount) {
		this.amount = this.amount+amount;
	}

}
