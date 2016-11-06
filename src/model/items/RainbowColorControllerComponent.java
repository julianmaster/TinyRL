package model.items;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import pattern.Component;
import pattern.Event;

public class RainbowColorControllerComponent implements Component {
	
	public static final int TIME_CHANGE_COLOR = 1;
	
	private List<Color> colors = new LinkedList<Color>() {{
		add(Color.RED);
		add(Color.GREEN);
		add(Color.BLUE);
		add(Color.RED);
	}};
	
	private float currentTime = 0;

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof RainbowColorControllerEvent) {
			currentTime += deltaTime / 60;
			currentTime  = currentTime % TIME_CHANGE_COLOR;
			
			float position = currentTime * (colors.size() - 1) / TIME_CHANGE_COLOR;
			
			Color color = interpolate(colors.get((int)position), colors.get((int)position+1), position % 1);
			ItemRarity.RARE_ITEM.setColor(color);
		}
	}
	
	private Color interpolate(Color colorA, Color colorB, float bAmount) {
	    float aAmount = 1.0f - bAmount;
	    int red = (int)(colorA.getRed() * aAmount + colorB.getRed() * bAmount);
	    int green = (int)(colorA.getGreen() * aAmount + colorB.getGreen() * bAmount);
	    int blue = (int)(colorA.getBlue() * aAmount + colorB.getBlue() * bAmount);
	    return new Color(red, green, blue);
	}
}
