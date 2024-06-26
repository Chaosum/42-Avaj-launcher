package src.Aircraft;

import java.io.FileWriter;
import java.io.PrintWriter;

import src.Coordinates;
import src.Simulator.UnknownAircraftTypeException;

public class AircraftFactory {
    // private static AircraftFactory instance;

    // private AircraftFactory() {
    // }
    // public static AircraftFactory getInstance() {
    //     // Crée l'instance si elle n'existe pas encore
    //     if (instance == null) {
    //         instance = new AircraftFactory();
    //     }
    //     return instance;
    // }
    private static long idCounter = 0;
    public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws Exception {
        if (p_type.toLowerCase().equals("helicopter")) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
                writer.println(p_type + " " + p_name + " has been created.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return (new Helicopter(idCounter++, p_name, p_coordinates));
        }
		else if (p_type.toLowerCase().equals("jetplane")) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
                writer.println(p_type + " " + p_name + " has been created.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return (new JetPlane(idCounter++, p_name, p_coordinates));
        }
		else if (p_type.toLowerCase().equals("baloon")) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
                writer.println(p_type + " " + p_name + " has been created.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return (new Baloon(idCounter++, p_name, p_coordinates));
        }
		else {
			throw new UnknownAircraftTypeException(p_type);
		}
    }
}
