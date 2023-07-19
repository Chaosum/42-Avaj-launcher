package src.Aircraft;

import src.Coordinates;
import src.WeatherTower;

public class Helicopter extends Aircraft {

	WeatherTower tower;
	
	public Helicopter(String p_name, Coordinates p_coordinate) {
		super(p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try {
			if (weather == "SUN"){ //SUN - Longitude increases with 10, Height increases with 2
				coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): it shine bright tonight, like a diamond in the sky.");
			}
			else if (weather == "RAIN") { //RAIN - Longitude increases with 5
				coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): It's raining man.");
			}
			else if (weather == "FOG") { //FOG - Longitude increases with 1
				coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): I can't see.");
			}
			else if (weather == "SNOW") { //SNOW - Height decreases with 12
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 10);
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): I would like to build a snowman.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (coordinates.getHeight() == 0){
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			this.tower.unregister(this);
			System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}
	@Override
	public	void	registerTower(WeatherTower p_tower){
		this.tower = p_tower;
		this.tower.register(this);
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}