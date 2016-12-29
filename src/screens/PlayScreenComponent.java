package screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import generator.EntityGenerator;
import main.TinyRL;
import model.RenderRoomEvent;
import model.WorldTickEvent;
import model.entities.AttributesComponent;
import model.particles.AddParticleEvent;
import model.particles.Particle;
import model.particles.RenderParticlesEvent;
import model.particles.TickParticleHandlerEvent;
import model.particles.UpdateParticleEvent;
import model.turns.entities.PlayerTurnComponent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import ui.AsciiPanel;
import ui.CustomColor;
import util.Pair;

public class PlayScreenComponent extends ScreenComponent {
	
	public static final String HP = String.valueOf((char)3);
	public static final String MANA = String.valueOf((char)247);
	public static final String PHYSICAL_ARMOR = String.valueOf((char)31);
	public static final String MAGICAL_ARMOR = String.valueOf((char)31);
	
	boolean pause = false;
	boolean info = false;

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof ScreenEvent) {
			Entity playScreen = Engine.getInstance().getEntityByComponent(this);
			
			// TODO Adding the animation blocking turn system.
			
			/**
			 * UPDATE
			 */
			
			KeyEvent event = TinyRL.getInstance().getAsciiTerminal().getEvent();
			if(event != null) {
				if(event.getKeyCode() == KeyEvent.VK_I) {
					Engine.getInstance().removeEntity(playScreen);
					Engine.getInstance().addEntity(EntityGenerator.newInventoryScreen());
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
				if(event.getKeyCode() == KeyEvent.VK_SPACE) {
					pause = !pause;
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
				
				if(event.getKeyCode() == KeyEvent.VK_P) {
					Pair<Integer, Integer> position = new Pair<Integer, Integer>(EntityGenerator.rand.nextInt(TinyRL.WINDOW_WIDTH-5)+5, EntityGenerator.rand.nextInt(TinyRL.WINDOW_HEIGHT-3)+3);
					Color color = new Color(EntityGenerator.rand.nextInt(155)+100, EntityGenerator.rand.nextInt(155)+100, EntityGenerator.rand.nextInt(155)+100);
					Entity particleEmitter = EntityGenerator.newParticleEmitter(position, 0.02f, 2000, 5f, 30, color);
					Engine.getInstance().addEntity(particleEmitter);
					
					TinyRL.getInstance().getAsciiTerminal().setEvent(null);
				}
			}
			
			KeyEvent otherEvent = TinyRL.getInstance().getAsciiTerminal().getOtherEvent();
			if(otherEvent != null) {
				
				// Show help info
				if(otherEvent.getKeyCode() == KeyEvent.VK_COMMA && otherEvent.getID() == KeyEvent.KEY_PRESSED && info == false) {
					info = true;
				}
				// Hidden help info
				else if(otherEvent.getKeyCode() == KeyEvent.VK_COMMA && otherEvent.getID() == KeyEvent.KEY_RELEASED && info == true) {
					info = false;
				}
				
				TinyRL.getInstance().getAsciiTerminal().setOtherEvent(null);
			}
			
			if(!pause) {
				// Animation update
				Engine.getInstance().addTailEvent(new WorldTickEvent());
				Engine.getInstance().addTailEvent(new TickParticleHandlerEvent());
			}
			
			
			
			
			/**
			 * DRAW
			 */
			
			AsciiPanel asciiPanel = TinyRL.getInstance().getAsciiPanel();
			asciiPanel.clear();
			
			Engine.getInstance().addTailEvent(new RenderParticlesEvent());
			Engine.getInstance().addTailEvent(new RenderRoomEvent());
			
			// Paint pause
			if(pause) {
				asciiPanel.writeString(12, 0, "PAUSE", CustomColor.VIKING, CustomColor.ELF_GREEN);
			}
			// Paint help info
			else if(info) {
				asciiPanel.writeString(10, 1, HP + " HP", CustomColor.BROWN);
				asciiPanel.writeString(10, 2, MANA + " MANA", CustomColor.ROYAL_BLUE);
				asciiPanel.writeString(10, 3, PHYSICAL_ARMOR + " PH ARMOR", CustomColor.GOLDEN_FIZZ);
				asciiPanel.writeString(10, 4, MAGICAL_ARMOR + " MA ARMOR", CustomColor.VIKING);
			}
			// Paint player info
			else{
				Entity player = Engine.getInstance().getEntityByComponentClass(PlayerTurnComponent.class);
				AttributesComponent attributesComponent = player.getComponentByClass(AttributesComponent.class);
				
				asciiPanel.writeString(10, 1, HP + String.format(" %3.0f",attributesComponent.getHp()) + "/" + String.format("%.0f",attributesComponent.getHpMax()), CustomColor.BROWN);
				asciiPanel.writeString(10, 2, MANA + String.format(" %3.0f",attributesComponent.getMana()) + "/" + String.format("%.0f",attributesComponent.getManaMax()), CustomColor.ROYAL_BLUE);
				asciiPanel.writeString(10, 3, PHYSICAL_ARMOR + String.format(" %2s",attributesComponent.getPhysicalArmor()), CustomColor.GOLDEN_FIZZ);
				asciiPanel.writeString(10, 4, MAGICAL_ARMOR + String.format(" %2s",attributesComponent.getMagicalArmor()), CustomColor.VIKING);
				asciiPanel.writeString(10, 8, "I:Inv", CustomColor.WHITE);
				asciiPanel.writeString(19, 8, "?", CustomColor.WHITE);
			}
		}
	}
}
