package src.Aircraft;

import java.io.FileWriter;
import java.io.PrintWriter;

import src.Coordinates;
import src.WeatherTower;

public class JetPlane extends Aircraft {
	WeatherTower tower;

	public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))) {
			if ("SUN".equals(weather)) { // SUN - Latitude increases with 10, Height increases with 2
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
				writer.println("JetPlane#" + this.name + "(" + this.id + "): sun is up!");
			} else if ("RAIN".equals(weather)) { // RAIN - Latitude increases with 5
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
				writer.println("JetPlane#" + this.name + "(" + this.id + "): it's raining men, amen!");
			} else if ("FOG".equals(weather)) { // FOG - Latitude increases with 1
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
				writer.println("JetPlane#" + this.name + "(" + this.id + "): did you know that a website with fog jokes exist?");
			} else if ("SNOW".equals(weather)) { // SNOW - Height decreases with 7
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
				writer.println("JetPlane#" + this.name + "(" + this.id + "): snow jokes are snow funny!");
			}
			if (coordinates.getHeight() == 0){
				writer.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
				this.tower.unregister(this);
				writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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
			writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
