package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event>{
		
		public enum EventType {
			NEW_CLIENT_GROUP, CLIENT_GROUP_LEAVE
		}
		
		private EventType type ;
		private LocalTime time ;
		private double toll;
		private int numeroPersone;
		private int dimTavolo;
		
		public Event(EventType type, LocalTime time) {
			super();
			this.type = type;
			this.time = time;
			this.toll = Math.random()*0.9;
			this.numeroPersone = (int) (10*Math.random()+1);
			dimTavolo = -1;
		}

		public Event(EventType type, LocalTime time, int dimTavolo, int numeroPersone) {
			super();
			this.type = type;
			this.time = time;
			this.toll = 0;
			this.numeroPersone = numeroPersone;
			this.dimTavolo = dimTavolo;
		}

		public EventType getType() {
			return type;
		}

		public void setType(EventType type) {
			this.type = type;
		}

		public LocalTime getTime() {
			return time;
		}

		public void setTime(LocalTime time) {
			this.time = time;
		}

		public double getToll() {
			return toll;
		}

		public void setToll(double toll) {
			this.toll = toll;
		}

		public int getNumeroPersone() {
			return numeroPersone;
		}

		public void setNumeroPersone(int numeroPersone) {
			this.numeroPersone = numeroPersone;
		}

		public int getDimTavolo() {
			return dimTavolo;
		}

		public void setDimTavolo(int dimTavolo) {
			this.dimTavolo = dimTavolo;
		}

		@Override
		public int compareTo(Event o) {
			return this.time.compareTo(o.time);
		}
		
		
		
		

}
