package src;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import src.Aircraft.AircraftFactory;
import src.Aircraft.Flyable;

public class Simulator {
	public static class UnknownAircraftTypeException extends Exception {
		public UnknownAircraftTypeException() {
			super("Error: Type not recognized - known types are : Baloon, Helicopter, JetPlane");
		}
		public UnknownAircraftTypeException(String exception) {
			super("Error: Type \"" + exception + "\" not recognized - known types are : Baloon, Helicopter, JetPlane");
		}
		public UnknownAircraftTypeException(Exception exception) {
			super(exception);
		}
	}

	public static class WrongLongitudeException extends Exception {
		public WrongLongitudeException() {
			super("Error: a longitude cannot be nagative");
		}
		public WrongLongitudeException(String exception) {
			super("Error: a longitude cannot be nagative. invalid number : " + exception);
		}
		public WrongLongitudeException(Exception exception) {
			super(exception);
		}
	}

	public static class WrongLatitudeException extends Exception {
		public WrongLatitudeException() {
			super("Error: a latitude cannot be nagative");
		}
		public WrongLatitudeException(String exception) {
			super(exception);
		}
		public WrongLatitudeException(Exception exception) {
			super(exception);
		}
	}

	private static void initSimualtion(String file) throws Exception {
		String [] content;
		String line;
		WeatherTower WeatherTower = new WeatherTower();
		List<Flyable> aircraftList =  new ArrayList<>();
		int cycles;
		BufferedReader reader = new BufferedReader(new FileReader(file));

		cycles = Integer.parseInt(reader.readLine());
		int i = 0;
		while ((line = reader.readLine()) != null) {
			content = line.split(" ");
			Coordinates coordinates = new Coordinates(Integer.parseInt(content[2]), Integer.parseInt(content[3]), Integer.parseInt(content[4]));
			aircraftList.add(AircraftFactory.newAircraft(content[0], content[1], coordinates));
			aircraftList.get(i).registerTower(WeatherTower);
			i++;
		}
		reader.close();
		int cur_cycle = 0;

		while (cur_cycle < cycles) {
			WeatherTower.changeWeather();
			cur_cycle++;
		}

	}


	public static void main(String [] args) {
		if (args.length != 1) {
			return ;
		}
		try {
			initSimualtion(args[0]);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
