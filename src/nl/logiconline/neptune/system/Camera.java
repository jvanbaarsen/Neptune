package nl.logiconline.neptune.system;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.World;
import nl.logiconline.neptune.entities.Entity;

public class Camera {

	private Entity entity;
	private int width, height, x, y;
	private World world = World.getInstance();

	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getCameraX() {
		if (this.entity != null) {
			return this.entity.getX();
		}
		return this.x;
	}

	public int getCameraY() {
		if (this.entity != null) {
			return this.entity.getY();
		}
		return this.y;
	}

	public int getScrollX() {
		return this.getCameraX() - (this.world.getCanvasWidth() / 2);
	}

	public int getScrollY() {
		return this.getCameraY() - (this.world.getCanvasHeight() / 2);
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public int screenWidth() {
		return this.world.getWidth();
	}

	public int screenHeight() {
		return this.world.getHeight();
	}

	public void followEntity(Entity entity) {
		this.entity = entity;
	}

}
