package practica.objects;

import java.util.LinkedHashMap;
import java.util.Vector;

public class River extends Localization {
	private Integer km;
	private String name;
	
	public River(String id, String name, Integer km) {
		super(id);
		this.km = km;
		this.name = name;
	}
	public String getName() {
		return this.id;
	}
	public void setName(String name) {
		this.id= name;
	}
	public Integer getKm() {
		return km;
	}
	public void setKm(Integer km) {
		this.km = km;
	}
}
