package nl.logiconline.neptune.states;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.states
 * (c) 2012 - LogicOnline
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import nl.logiconline.neptune.entities.Entity;
import nl.logiconline.neptune.system.Camera;
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.system.NeptuneException;
import nl.logiconline.neptune.utils.Color;

public abstract class State {

	private int id;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Camera camera;
	private int entitiesRendered = 0;

	public State() {
	}

	public void init() throws NeptuneException {

	}

	private Comparator<Entity> entitySorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity e0, Entity e1) {
			if (e1.getZIndex() < e0.getZIndex()) {
				return +1;
			}
			if (e1.getZIndex() > e0.getZIndex()) {
				return -1;
			}
			return 0;
		}
	};

	public void removeEntity(Entity e) {
		if (this.entities.contains(e)) {
			e.destroy();
		}
	}

	public void addEntity(Entity e) throws NeptuneException {
		if (!this.entities.contains(e)) {
			this.entities.add(e);
			e.setState(this);
			e.init();
		}
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Camera getCamera() {
		return this.camera;
	}

	public void update() throws NeptuneException {
		//Update all the normal entities
		if (this.entities.size() > 0) {
			for (int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).update();
				//Remove entities that are scheduled for destruction :-)
				if (this.entities.get(i).isDestroyed()) {
					this.entities.get(i).removeChilds();
					this.entities.remove(i);
				}
			}
		}
	}

	public void render(Gfx g) throws NeptuneException {
		this.entitiesRendered = 0;
		if (g.getCamera() == null) {
			g.setCamera(this.camera);
		}

		Collections.sort(this.entities, this.entitySorter);
		//TODO Create some sort of system to only render the entities that are in view
		//Render all the entities
		if (this.entities.size() > 0) {
			for (int i = 0; i < this.entities.size(); i++) {
				Entity ent = this.entities.get(i);
				if(this.camera != null) {
					if((ent.position.x >= (this.camera.getCameraX() - this.camera.xOffset)) && (ent.position.y >= (this.camera.getCameraY() - this.camera.yOffset)) &&
							((ent.position.x + ent.width) < ((this.camera.getCameraX() + this.camera.getWidth()) + this.camera.xOffset)) &&	((ent.position.y + ent.height) < ((this.camera.getCameraY() + this.camera.getHeight()) + this.camera.yOffset))) {
						this.entities.get(i).render(g);
						this.entitiesRendered++;
					}
				} else {
					this.entities.get(i).render(g);
					this.entitiesRendered++;
				}
			}
		}

		if((this.camera != null) && this.camera.debugCamera) {
			g.setColor(Color.MAGENTA);
			g.drawRect(this.camera.getCameraX(), this.camera.getCameraY(), this.camera.getWidth(), this.camera.getHeight());
			g.setColor(Color.BLACK);
		}
	}

	public int getRenderedEntitiesCount() {
		return this.entitiesRendered;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
