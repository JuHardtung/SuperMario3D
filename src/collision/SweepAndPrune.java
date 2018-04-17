package collision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Player;

public class SweepAndPrune implements BroadphaseCollisionDetection{
	
	private final class SweepPoint {
		
		public final Entity entity;
		public final boolean min;
		public final int axis;
	
		
		public SweepPoint(Entity entity, boolean min, int axis) {
			super();
			this.entity = entity;
			this.min = min;
			this.axis = axis;
		}
		
		public final float value() {
			
			if(min) {
				if ((axis == 0)) {
					return entity.getMinBounds().getX();
				} else if ((axis == 1)){
					return entity.getMinBounds().getY();
				} else {
					return entity.getMinBounds().getZ();
				}
			} else {
				 if (axis == 0) {
					return entity.getMaxBounds().getX();
				} else if (axis == 1) {
					return entity.getMaxBounds().getY();
				} else  {
					return entity.getMaxBounds().getZ();
				}
			}
		}
	}
	
	private ArrayList<Handler> handlers = new ArrayList<Handler>();
	private ArrayList<Pair<Entity>> overlappingPairs = new ArrayList<Pair<Entity>>();
	private Map<Pair<Entity>, Counter> counters = new LinkedHashMap<Pair<Entity>, Counter>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	private ArrayList<SweepPoint> xAxis = new ArrayList<SweepPoint>();
	private ArrayList<SweepPoint> yAxis = new ArrayList<SweepPoint>();
	private ArrayList<SweepPoint> zAxis = new ArrayList<SweepPoint>();
	
	private final class Counter {
		public boolean wasOverlapping = false;
		public int overlaps;
	}
	
	
	private final void sortAxis(ArrayList<SweepPoint> axis) {
		
		for(int j=1;j<axis.size();j++) {
			final SweepPoint keyelement = axis.get(j);
			double key = keyelement.value();
			
			if(Double.isNaN(key)) {
				throw new IllegalStateException("Geometry has NaN in its bounding box values");
			}
			
			int i=j-1;
			while(i>=0 && axis.get(i).value() > key) {
				final SweepPoint swapper = axis.get(i);
				
				if(keyelement.min && !swapper.min) {
					// increment overlap (max before min)
					final Pair<Entity> pair = new Pair<Entity>(keyelement.entity, swapper.entity);
					if(counters.containsKey(pair)) {
						counters.get(pair).overlaps++;

					} else {
						Counter counter = new Counter();
						counter.overlaps = 1;
						counters.put(pair, counter);
					}
				}
				
				if(!keyelement.min && swapper.min) {
					// decrement overlap (begin before end)
					final Pair<Entity> pair = new Pair<Entity>(keyelement.entity, swapper.entity);
					if(counters.containsKey(pair)) {
						counters.get(pair).overlaps--;
					}
				}
				
				axis.set(i+1, swapper);
				i=i-1;
			}
			axis.set(i+1, keyelement);
		}
	}
	
	
	@Override
	public void add(Entity entity) {
		if(!entities.contains(entity)) {
			entities.add(entity);
			
			//create new sweep Points
			xAxis.add(new SweepPoint(entity, true, 0));
			xAxis.add(new SweepPoint(entity, false, 0));
			yAxis.add(new SweepPoint(entity, true, 1));
			yAxis.add(new SweepPoint(entity, false, 1));
			zAxis.add(new SweepPoint(entity, true, 2));
			zAxis.add(new SweepPoint(entity, false, 2));
		} else {
			throw new IllegalArgumentException("Given entity already exists");
		}
	}
	
	@Override
	public void remove(Entity e) {
		if(entities.contains(e)) {

			entities.remove(e);
			
			//remove SweepPoints
			removeSweepPoint(xAxis, e);
			removeSweepPoint(yAxis, e);
			removeSweepPoint(zAxis, e);
			
			// go through counters and delete the ones that involve e. If the counter var
			// an overlapping counter, signal an seperation event
			Iterator<Entry<Pair<Entity>, Counter>> iter = counters.entrySet().iterator();
			
			while(iter.hasNext()) {
				Entry<Pair<Entity>, Counter> entry = iter.next();
				Counter c = entry.getValue();
				Pair<Entity> pair = entry.getKey();
				if(pair.getFirst() == e || pair.getSecond() == e) {
					if(c.wasOverlapping) {
						//notify handlers
						for(Handler h: handlers) {
							h.seperation(pair);
						}
						overlappingPairs.remove(pair);
					}
					//remove counter
					iter.remove();
				}
			}
		} else {
			throw new IllegalArgumentException("Given entity does not exist");
		}
	}
	
	private final void removeSweepPoint(List<SweepPoint> list, Entity e) {
		
		ListIterator<SweepPoint> iter = list.listIterator();
		
		while(iter.hasNext()) {
			SweepPoint p = iter.next();
			if(p.entity == e) {
				iter.remove();
			}
		}
	}
	
	@Override
	public void run() {
		// sort each axis and update counters
		sortAxis(xAxis);
		sortAxis(yAxis);
		sortAxis(zAxis);


		
		int countingOverlaps = 0;
		// go through all counters
		Iterator<Entry<Pair<Entity>, Counter>> iter = counters.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Pair<Entity>, Counter> entry = iter.next();
			
			Counter c = entry.getValue();
			Pair<Entity> pair = entry.getKey();
			
			if(c.overlaps == 3) {
				countingOverlaps ++;
			}
			if(c.wasOverlapping) {
				//report seperation
				if(c.overlaps < 3) {
					overlappingPairs.remove(pair);
					c.wasOverlapping = false;
					
					//notify handlers
					for(Handler h: handlers) {
						h.seperation(pair);
					}
				}
			} else {
				if(c.overlaps > 2) {
					overlappingPairs.add(pair);
					c.wasOverlapping = true;
					
					//notify handlers
					for (Handler h: handlers) {
						h.overlap(pair);
					}
				}
			}
			if(c.overlaps < 1) {
				iter.remove();
			}
		}
		//System.out.println("full Overlaps: " + countingOverlaps);
	}
	
	@Override
	public void addHandler(Handler h) {
		handlers.add(h);
	}
	
	@Override
	public void removeHandler(Handler h) {
		handlers.remove(h);
	}
	
	@Override
	public Set<Pair<Entity>> getOverlappingPairs() {
		return new LinkedHashSet<Pair<Entity>>(overlappingPairs);
	}
	
}
