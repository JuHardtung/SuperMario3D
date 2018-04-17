package collision;

import java.util.Set;

import entities.Entity;
import entities.Player;

public interface BroadphaseCollisionDetection {

	
	public interface Handler {
		
		public void overlap(Pair<Entity> pair);
		
		public void seperation(Pair<Entity> pair);
	}
	
	
	public void addHandler(Handler h);
	public void removeHandler(Handler h);
	
	public void add(Entity entity);
	
	public void remove(Entity entity);
	
	public Set<Pair<Entity>> getOverlappingPairs();
	
	public void run();
	
	
}
