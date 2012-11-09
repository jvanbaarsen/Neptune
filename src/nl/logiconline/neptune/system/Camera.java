package nl.logiconline.neptune.system;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.entities.Entity;

public class Camera {

	private Entity entity;
	private int width, height, x, y;
	public int xOffset = 50;
	public int yOffset = 50;
	public int maxScrollX = 0, maxScrollY = 0;
	public boolean debugCamera = false;

	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public double getCameraX() {
		if (this.entity != null) {
			double camPosX = this.entity.getX() - (((this.width - this.xOffset) / 2) + this.entity.getWidth());
			if(camPosX < 0) {
				camPosX = 0;
			}
			if((this.maxScrollX != 0) && ((camPosX + (this.width)) > this.maxScrollX)) {
				camPosX = this.maxScrollX - (this.width);
			}
			return camPosX;
		}
		return this.x;
	}

	public double getCameraY() {
		if (this.entity != null) {
			double camPosY = this.entity.getY() - (((this.height - this.yOffset) / 2) + this.entity.getHeight());
			if(camPosY < 0) {
				camPosY = 0;
			}
			if((this.maxScrollY != 0) && ((camPosY + this.height) > this.maxScrollY)) {
				camPosY = this.maxScrollY - this.height;
			}
			return camPosY;
		}
		return this.y;
	}

	public double getScrollX() {
		return this.getCameraX();
	}

	public double getScrollY() {
		return this.getCameraY();
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public int screenWidth() {
		return Neptune.getWorld().getWidth();
	}

	public int screenHeight() {
		return Neptune.getWorld().getHeight();
	}

	public void followEntity(Entity entity) {
		this.entity = entity;
	}

}
