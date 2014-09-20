package sora.havok;

import java.util.Collection;

public class FizzBuzz{
	
	private final Collection<Buzzer> buzzers;
	
	public FizzBuzz(final Collection<Buzzer> buzzers) {
		this.buzzers = buzzers;
	}
	
	public void play(final int end) { play(1, end); }
	
	public void play(final int start, final int end) {
		boolean buzzed = false;
		for(int i = start; i <= end; ++i) {
			for(final Buzzer buzzer : buzzers) {
				if( buzzer.willBuzz(i)) {
					System.out.print(buzzer.message());
					buzzed = true;
				}				
			}
			System.out.println(buzzed ? "" : i);
			buzzed = false;
		}
	}
}
