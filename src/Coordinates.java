package src;

import src.Simulator.WrongLatitudeException;
import src.Simulator.WrongLongitudeException;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;

	public Coordinates(int p_longitude, int p_latitude, int p_height) throws Exception {
		if (p_longitude < 0)
			throw new WrongLongitudeException(Integer.toString(p_longitude));
		if (p_latitude < 0)
			throw new WrongLatitudeException(Integer.toString(p_latitude));
		if (p_height > 100)
			p_height = 100;
		else if (p_height < 0)
			p_height = 0;
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
	}

	public int getLongitude() {
		return this.longitude;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public int getHeight() {
		return this.height;
	}
}
