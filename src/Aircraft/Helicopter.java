package src.Aircraft;

import java.io.FileWriter;
import java.io.PrintWriter;

import src.Coordinates;
import src.WeatherTower;

public class Helicopter extends Aircraft {

	WeatherTower tower;
	
	public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
			if ("SUN".equals(weather)) { // SUN - Longitude increases with 10, Height increases with 2
				coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
				writer.println("Helicopter#" + this.name + "(" + this.id + "): it shine bright tonight, like a diamond in the sky.");
			} else if ("RAIN".equals(weather)) { // RAIN - Longitude increases with 5
				coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
				writer.println("Helicopter#" + this.name + "(" + this.id + "): It's raining man.");
			} else if ("FOG".equals(weather)) { // FOG - Longitude increases with 1
				coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
				writer.println("Helicopter#" + this.name + "(" + this.id + "): I can't see.");
			} else if ("SNOW".equals(weather)) { // SNOW - Height decreases with 12
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
				writer.println("Helicopter#" + this.name + "(" + this.id + "): I would like to build a snowman.");
			}
			if (coordinates.getHeight() == 0){
				writer.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
				this.tower.unregister(this);
				writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public	void	registerTower(WeatherTower p_tower){
		this.tower = p_tower;
		this.tower.register(this);
		try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
			writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}