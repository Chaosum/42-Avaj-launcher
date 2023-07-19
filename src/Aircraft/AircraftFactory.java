package src.Aircraft;

import src.Coordinates;
import src.Simulator.UnknownAircraftTypeException;

public class AircraftFactory {
    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws Exception {
        if (p_type.toLowerCase().equals("helicopter")) {
            System.out.println(p_type + " " + p_name + " has been created.");
            return (new Helicopter(p_name, p_coordinates));
        }
		else if (p_type.toLowerCase().equals("jetplane")) {
            System.out.println(p_type + " " + p_name + " has been created.");
            return (new JetPlane(p_name, p_coordinates));
        }
		else if (p_type.toLowerCase().equals("baloon")) {
            System.out.println(p_type + " " + p_name + " has been created.");
            return (new Baloon(p_name, p_coordinates));
        }
		else {
			throw new UnknownAircraftTypeException(p_type);
		}
    }
}
