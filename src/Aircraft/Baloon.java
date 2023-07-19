package src.Aircraft;

import src.Coordinates;
import src.WeatherTower;

public class Baloon extends Aircraft{	
	
	WeatherTower tower;

	public Baloon(String p_name, Coordinates p_coordinate) {
		super( p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try {
			if (weather == "SUN"){ //SUN - Longitude increases with 2, Height increases with 4
				coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
				System.out.println("Baloon#" + this.name + "(" + this.id + "): it's hot up there.");
			}
			else if (weather == "RAIN") { //RAIN - Height decreases with 5
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
				System.out.println("Baloon#" + this.name + "(" + this.id + "): hallelujah.");
			}
			else if (weather == "FOG") { //FOG - Height decreases with 3
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
				System.out.println("Baloon#" + this.name + "(" + this.id + "): what the fog.");
			}
			else if (weather == "SNOW") { //SNOW - Height decreases with 15
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
				System.out.println("Baloon#" + this.name + "(" + this.id + "): going down !");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (coordinates.getHeight() == 0){
			System.out.println("Baloon#" + this.name + "(" + this.id + "): landing.");
			this.tower.unregister(this);
			System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}
	@Override
	public	void	registerTower(WeatherTower p_tower){
		this.tower = p_tower;
		this.tower.register(this);
		System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}
