package exceptions;

public class NoSuchProperty extends Exception {
	private String key;
	public NoSuchProperty(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "No such property: "+key;
	}

}
