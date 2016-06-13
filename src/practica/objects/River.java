package practica.objects;

public class River extends Localization {
	String name;
	int km;
	public River(String name, int km) {
		this.name = name;
		this.km = km;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
}
