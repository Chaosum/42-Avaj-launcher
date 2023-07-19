package src.Aircraft;

import src.Coordinates;

public class Aircraft extends Flyable{
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	public static long max_id = 0;

	protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
		this.id = max_id;
		max_id++;
	}

	@Override
	public void updateConditions() {}
}
