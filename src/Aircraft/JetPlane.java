package src.Aircraft;

import src.Coordinates;
import src.WeatherTower;

public class JetPlane extends Aircraft {
	WeatherTower tower;

	public JetPlane(String p_name, Coordinates p_coordinate) {
		super(p_name, p_coordinate);
	}
	@Override
	public void updateConditions() {
		String weather = tower.getWeather(coordinates);
		try {
			if (weather == "SUN"){ //SUN - Latitude increases with 10, Height increases with 2
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): sun is up!");
			}
			else if (weather == "RAIN") { //RAIN - Latitude increases with 5
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): it's raining men, amen!");
			}
			else if (weather == "FOG") { //FOG - Latitude increases with 1
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): did you know that a website with fog jokes exist?"); //https://punsandoneliners.com/randomness/fog-jokes/
			}
			else if (weather == "SNOW") { //SNOW - Height decreases with 7
				coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): snow jokes are snow funny!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (coordinates.getHeight() == 0){
			System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
			this.tower.unregister(this);
			System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}
	@Override
	public	void	registerTower(WeatherTower p_tower){
		this.tower = p_tower;
		this.tower.register(this);
		System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}
}
