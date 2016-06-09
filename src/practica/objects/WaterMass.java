package practica.objects;

import java.util.Vector;

public class WaterMass {
	Localization place;
    Vector<Pollutant> pollutants;
	Vector<WaterMass> originMass;
	Vector<WaterMass> sonMass;
	int existanceTime;
}
