package practica.objects;

public class Regulation extends RuleTable {
	
	public Regulation(String id)
	{
		super(id);
	}
	
	public String toString()
	{
		return "Permit:"+super.toString();
	}
}
