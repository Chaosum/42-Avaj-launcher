package src.Aircraft;

import java.io.FileWriter;
import java.io.PrintWriter;

import src.Coordinates;
import src.WeatherTower;

public class Baloon extends Aircraft{	
	
	WeatherTower tower;

	public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
		super(p_id, p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try (PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt", true))){
			if ("SUN".equals(weather)) {
				coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
				writer.println("Baloon#" + this.name + "(" + this.id + "): it's hot up there.");
			} else if ("RAIN".equals(weather)) {
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
				writer.println("Baloon#" + this.name + "(" + this.id + "): hallelujah.");
			} else if ("FOG".equals(weather)) {
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
				writer.println("Baloon#" + this.name + "(" + this.id + "): what the fog.");
			} else if ("SNOW".equals(weather)) {
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
				writer.println("Baloon#" + this.name + "(" + this.id + "): going down!");
			}			
			if (coordinates.getHeight() == 0){
				writer.println("Baloon#" + this.name + "(" + this.id + "): landing.");
				this.tower.unregister(this);
				writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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
			writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
