package src;

import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import src.Aircraft.AircraftFactory;
import src.Aircraft.Flyable;

public class Simulator {

	private static final Set<String> knownAircraftTypes = new HashSet<>();
	static {
		knownAircraftTypes.add("Baloon");
		knownAircraftTypes.add("JetPlane");
		knownAircraftTypes.add("Helicopter");
	}
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

	public static class LineFormatException extends Exception {
		public LineFormatException() {
			super("Error: Incorect file format");
		}
		public LineFormatException(String exception) {
			super(exception);
		}
		public LineFormatException(Exception exception) {
			super(exception);
		}
	}

	public static class wrongArgumentsException extends Exception {
		public wrongArgumentsException() {
			super("Error: wrong arguments. Expected only a sceanario file");
		}
		public wrongArgumentsException(String exception) {
			super(exception);
		}
		public wrongArgumentsException(Exception exception) {
			super(exception);
		}
	}
	
public static void verifyFileAndFormat(String file) throws Exception {
		String line = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
		}
		catch (Exception e) {
			if (reader != null){
				reader.close();
			}
			throw new Exception("Error while reading file : " + e.getMessage());
		}
		if (line == null || !line.matches("\\d+")) {
			reader.close();
			throw new LineFormatException("Error, in file format : First line must be a positiv integer");
		}
		int cycles = Integer.parseInt(line);
		if (cycles < 0) {
			reader.close();
			throw new LineFormatException("Error, in file format : The cycle number me be positiv");
		}
		
		while ((line = reader.readLine()) != null) {
			verifyLineFormat(line);
		}
		reader.close();
	}

	private static void verifyLineFormat(String line) throws Exception {
		String[] parts = line.split(" ");
		if (parts.length != 5) {
			throw new LineFormatException("Error, in file format : expected format is 'TYPE NAME LONGITUDE LATITUDE HEIGHT' you entered \n>" + line);
		}

		String type = parts[0];
		if (!knownAircraftTypes.contains(type)) {
			throw new UnknownAircraftTypeException(type);
		}

		try {
			int latitude = Integer.parseInt(parts[2]);
			int longitude = Integer.parseInt(parts[3]);
			int height = Integer.parseInt(parts[4]);

			if (latitude < 0) throw new LineFormatException("Error, in file format : LATITUDE cannot be negativ \n>"  + line);
			if (longitude < 0) throw new LineFormatException("Error, in file format : LONGITUDE cannot be negativ \n>"+ line);
			if (height < 0) throw new LineFormatException("Error, in file format : HEIGHT cannot be negativ \n>" + line);
		} catch (NumberFormatException e) {
			throw new LineFormatException("Error, in file format : coordonates and height must be integers\n>" + line);
		}
	}

	private static void initSimualtion(String file) throws Exception {
		String [] content;
		String line;
		WeatherTower WeatherTower = new WeatherTower();
		List<Flyable> aircraftList =  new ArrayList<>();
		int cycles;
		BufferedReader reader = null;
		verifyFileAndFormat(file);
		try {
			reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
		}
		catch (Exception e) {
			if (reader != null){
				reader.close();
			}
			throw new Exception("Error while reading file : " + e.getMessage());
		}
		PrintWriter writer = new PrintWriter("simulation.txt");
		writer.close();

		cycles = Integer.parseInt(line);
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
		try {
			if (args.length != 1) {
				throw new wrongArgumentsException();
			}
			initSimualtion(args[0]);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
