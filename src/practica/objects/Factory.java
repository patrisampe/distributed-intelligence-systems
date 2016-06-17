package practica.objects;
//2000/60/EC
public class Factory extends Localization{
	private Permit p;

	public Factory(String id) {
		super(id);
	}
	
	
	public Permit getP() {
		return p;
	}


	public void setP(Permit p) {
		this.p = p;
	}


	public Factory(String id, Permit p) {
		super(id);
		this.p = p;
	}
	
}
