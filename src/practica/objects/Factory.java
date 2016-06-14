package practica.objects;
//2000/60/EC
public class Factory extends Localization{
	Permit p;

	public Factory(String id) {
		super(id);
	}
	public Factory(String id, Permit p) {
		super(id);
		this.p = p;
	}
	
}
