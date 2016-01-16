package model.animations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.TinyRL;
import model.Room;

import util.Pair;

public class RainHandler implements AnimationHandler {
	
	public enum RainType {
		TINY_RAIN(5, 6),
		MEDIUM_RAIN(15, 3.2),
		BIG_RAIN(30, 1.5);
		
		public int size;
		public double waitTime;
		
		private RainType(int size, double waitTime) {
			this.size = size;
			this.waitTime = waitTime;
		}
	}
	
	private RainType rainType;
	private List<Rain> rains = new ArrayList<>();
	private double elapseTime = 0;
	private Random rand = new Random();
	
	public RainHandler(RainType rainType) {
		this.rainType = rainType;
		System.out.println(rainType.name());
	}

	@Override
	public boolean update(double delta) {
		boolean changed = false; 
		
		if(rains.size() < rainType.size) {
			elapseTime += delta;
			if(elapseTime >= rainType.waitTime) {
				elapseTime = 0;
				
				int xRainPosition = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
				int rainLife = rand.nextInt(Room.ROOM_SIZE - 2) + 1;
				
				Rain rain = new Rain(rainLife, rainLife);
				TinyRL.world.getCurrentRoom().getCell(new Pair<Integer, Integer>(xRainPosition, 0)).getAnimations().add(rain);
				rains.add(rain);
				changed = true;
			}
		}
		
		List<Rain> rainsDone = new ArrayList<>();
		for(Rain rain : rains) {
			if(rain.done()) {
				rainsDone.add(rain);
			}
		}
		rains.removeAll(rainsDone);
		
		return changed;
	}

	@Override
	public boolean done() {
		return false;
	}
}
