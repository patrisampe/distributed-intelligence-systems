package practica.objects;

import java.util.LinkedHashMap;
import java.util.Vector;

public class River extends Localization {
	private String name;
	private Integer km;
	
	public River(String name, Integer km) {
		this.name = name;
		this.km = km;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getKm() {
		return km;
	}
	public void setKm(Integer km) {
		this.km = km;
	}
}
