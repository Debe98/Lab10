package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue = new PriorityQueue<>();

	// PARAMETRI DI SIMULAZIONE

	private final LocalTime oraApertura = LocalTime.of(6, 00);
	private final LocalTime oraChiusura = LocalTime.of(20, 00);

	// MODELLO DEL MONDO
	private Map <Integer, Integer> tavoli = new HashMap<>();
	private List <Integer> dimTavoli = new LinkedList<>();
	
	private int servizi = 2000;

	// VALORI DA CALCOLARE
	private int clienti ;
	private int insoddisfatti ;


	// METODI PER IMPOSTARE I PARAMETRI

	// METODI PER RESTITUIRE I RISULTATI
	public int getClienti() {
		return clienti;
	}

	public int getInsoddisfatti() {
		return insoddisfatti;
	}
	
	public int getSoddisfatti() {
		return clienti - insoddisfatti;
	}


	// SIMULAZIONE VERA E PROPRIA

	public void run() {
		// preparazione iniziale (mondo + coda eventi)
		this.clienti = this.insoddisfatti = 0 ;
		tavoli.put(4, 5);
		tavoli.put(6, 4);
		tavoli.put(8, 4);
		tavoli.put(10, 2);
		dimTavoli.add(4);
		dimTavoli.add(6);
		dimTavoli.add(8);
		dimTavoli.add(10);
		

		this.queue.clear();
		LocalTime oraArrivoCliente = this.oraApertura ;
		int cnt = 0;
		do {
			Event e = new Event(EventType.NEW_CLIENT_GROUP, oraArrivoCliente);
			this.queue.add(e);
			oraArrivoCliente = oraArrivoCliente.plus(Duration.of((int)(10*Math.random()+1), ChronoUnit.MINUTES));
			cnt++;
		} while(oraArrivoCliente.isBefore(this.oraChiusura) && cnt <= servizi);

		// esecuzione del ciclo di simulazione
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			//				System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		switch(e.getType()) {

		case NEW_CLIENT_GROUP:
			int persone = e.getNumeroPersone();
			clienti += persone;
			
			for (Integer i : dimTavoli) {
				if (i >= persone && i <= 2*persone && tavoli.get(i) > 0) {
					e.setDimTavolo(i);
					tavoli.replace(i, tavoli.get(i)-1);
					break;
				}
			}
			//se non ha un tavolo
			if (e.getDimTavolo() == -1) {
				//Se sono sfortunato
				if (Math.random() > e.getToll()) {
					System.out.println("\nInsoddisfatte "+e.getNumeroPersone()+" persone");
					System.out.println(String.format("%% insoddisfazione: %.2f\n", +e.getToll()));
					insoddisfatti+= persone;
				}
				else {
					//vanno al bancone;
				}
			}
			else {
				Duration servizio = Duration.of((int) (61*Math.random())+60, ChronoUnit.MINUTES);
				if (servizio.toMinutes() > 120 || servizio.toMinutes() < 60)
					System.out.println(servizio.toMinutes()+" minuti");
				queue.add(new Event(EventType.CLIENT_GROUP_LEAVE, e.getTime().plus(servizio), e.getDimTavolo(), e.getNumeroPersone()));
				
			}
			
			break;

		case CLIENT_GROUP_LEAVE:

			int i = e.getDimTavolo();
			tavoli.replace(i, tavoli.get(i)+1);

			break;
		}

	}


}
