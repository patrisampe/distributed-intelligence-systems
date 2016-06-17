package practica.objects;


public class Permit extends RuleTable{
	public Permit(String id) {
		super(id);
	}
	public String toString()
	{
		return "Permit:"+super.toString();
	}
	
}
