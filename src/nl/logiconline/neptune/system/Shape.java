package nl.logiconline.neptune.system;

abstract class Shape {

	protected Gfx g;
	protected int x, y;

	public Shape(Gfx g, int x, int y) {
		this.g = g;
		this.x = x;
		this.y = y;
	}

	public abstract void draw();

}