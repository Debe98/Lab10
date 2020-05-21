package it.polito.tdp.bar.model;

public class Model {

	private Simulator s = new Simulator();

	public void avviaSimulazione() {
		s.run();
	}
	
	public int getClienti() {
		return s.getClienti();
	}
	
	public int getInsoddisfatti() {
		return s.getInsoddisfatti();
	}
}
