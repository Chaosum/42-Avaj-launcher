package src.Aircraft;

import src.Coordinates;

public class Aircraft extends Flyable{
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(long p_id, String name, Coordinates coordinates) {
		this.id = p_id;
		this.name = name;
		this.coordinates = coordinates;
	}

	@Override
	public void updateConditions() {}
}
