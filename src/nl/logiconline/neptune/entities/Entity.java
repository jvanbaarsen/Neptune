package nl.logiconline.neptune.entities;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.entities
 * (c) 2012 - LogicOnline
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import nl.logiconline.neptune.states.State;
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.utils.Color;
import nl.logiconline.neptune.utils.Point2D;

public abstract class Entity {

	private boolean destroyed = false;
	private State state;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashSet<String> collisionTypes = new HashSet<String>();

	protected Point2D position = new Point2D(0, 0);
	protected boolean enableSorting = true;
	protected int zIndex = 0;
	protected boolean debugHitbox = false;

	protected boolean collidable = false;

	public int hitboxOffsetX, hitboxOffsetY, hitboxWidth, hitboxHeight, height, width;
	public void init() {
	}

	public void update() {
		if(this.entities.size() > 0) {
			for(int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).update();
			}
		}
		if(this.enableSorting) {
			this.zIndex = this.getY();
		}
	}

	public void render(Gfx g) {
		if(this.entities.size() > 0) {
			for(int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).render(g);
			}
		}
		if(this.debugHitbox && this.collidable) {
			g.setColor(Color.MAGENTA);
			g.drawRect(this.getX() + this.hitboxOffsetX, this.getY() + this.hitboxOffsetY, this.hitboxWidth, this.hitboxHeight);
			g.setColor(Color.BLACK);
		}
	}

	public void addedToState() {}

	public void addEntity(Entity entity) {
		if(!this.entities.contains(entity)) {
			this.entities.add(entity);
			if(this.getState() != null) {
				this.getState().addEntity(entity);
			}
		}
	}

	public boolean hasEntities() {
		if(this.entities.size() > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	public void destroy() {
		if(this.hasEntities()) {
			for(int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).destroy();
			}
		}
		this.destroyed = true;
	}

	public void removeChilds() {
		if(this.hasEntities()) {
			for(int i = 0; i < this.entities.size(); i++) {
				this.entities.get(i).destroy();
			}
		}
	}

	public boolean isDestroyed() {
		return this.destroyed;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		if(this.state != null) {
			return this.state;
		}
		return null;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Point2D getPosition() {
		return this.position;
	}

	public int getX() {
		return this.position.getX();
	}

	public void setX(int x) {
		this.position.x = x;
	}

	public int getY() {
		return this.position.getY();
	}

	public void setY(int y) {
		this.position.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDimensions(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getZIndex() {
		return this.zIndex;
	}

	public void setHitBox(int offsetX, int offsetY, int width, int height) {
		this.hitboxOffsetX = offsetX;
		this.hitboxOffsetY = offsetY;
		this.hitboxWidth = width;
		this.hitboxHeight = height;
		this.collidable = true;
	}

	public boolean isCollidable() {
		return this.collidable;
	}

	public boolean addType(String... types) {
		return this.collisionTypes.addAll(Arrays.asList(types));
	}

	public void clearTypes() {
		this.collisionTypes.clear();
	}

	public boolean isType(String type) {
		return this.collisionTypes.contains(type);
	}

	public Entity collide(String type, int x, int y) {
		if((type == null) || type.isEmpty() || (this.getState() == null)) {
			return null;
		}
		for(Entity entity : this.getState().getEntities()) {
			if(entity.isCollidable() && entity.isType(type)) {
				if(!entity.equals(this)
						&& ((x + this.hitboxOffsetX + this.hitboxWidth) > (entity.getX() + entity.hitboxOffsetX))
						&& ((y + this.hitboxOffsetY + this.hitboxHeight) > (entity.getY()	+ entity.hitboxOffsetY))
						&& ((x + this.hitboxOffsetX) < (entity.getX() + entity.hitboxOffsetX + entity.hitboxWidth))
						&& ((y + this.hitboxOffsetY) < (entity.getY() + entity.hitboxOffsetY + entity.hitboxHeight))) {
					this.collisionResponse(entity);
					entity.collisionResponse(this);
					return entity;
				}
			}
		}
		return null;
	}

	public Entity collide(String[] types, int x, int y) {
		for (String type : types) {
			Entity e = this.collide(type, x, y);
			if (e != null) {
				return e;
			}
		}
		return null;
	}

	public void collisionResponse(Entity entity) {}
}
