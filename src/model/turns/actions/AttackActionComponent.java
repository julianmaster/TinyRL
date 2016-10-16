package model.turns.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.entities.AttributesComponent;
import model.entities.TakeDamageEvent;
import pattern.Component;
import pattern.Engine;
import pattern.Entity;
import pattern.Event;
import util.Pair;

public class AttackActionComponent implements Component {
	
	private final static Random rand = new Random();

	@Override
	public void process(Event e, double deltaTime) {
		if(e instanceof AttackActionEvent) {
			AttackActionEvent attackActionEvent = (AttackActionEvent)e;
			
			Entity entity = attackActionEvent.getEntity();
			AttributesComponent attributesComponent = entity.getComponentByClass(AttributesComponent.class);
			
			Entity entityToAttack = attackActionEvent.getEntityToAttack();
			
			List<Integer> damages = new ArrayList<>();
			List<Pair<Integer, Integer>> damagesRanges = attributesComponent.damages();
			for(Pair<Integer, Integer> damageRange : damagesRanges) {
				damages.add(rand.nextInt(damageRange.value - damageRange.key) + damageRange.key);
			}
			
			Engine.getInstance().addHeadEvent(new TakeDamageEvent(entityToAttack, damages));
		}
	}
}
