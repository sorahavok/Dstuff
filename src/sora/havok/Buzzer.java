package sora.havok;

public class Buzzer {
	private final String message;
	private final int buzzCount;

	public Buzzer(final String message, final int buzzCount) {
		this.message = message;
		this.buzzCount = buzzCount;
	}

	public boolean willBuzz(final int check){
		return check % buzzCount == 0;
	}
	
	public String message() {
		return message;
	}

	@Override
	public String toString() {
		return "Buzzer(" + message + ", " + buzzCount + ")";
	}
}
