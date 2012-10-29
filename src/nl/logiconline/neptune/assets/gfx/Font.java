package nl.logiconline.neptune.assets.gfx;

import nl.logiconline.neptune.World;
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.utils.Point2D;

public class Font {
	private String glyphs = "" +
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
			"0123456789";
	private int glyphsPerRow = 26;
	private int startPosFirstGlyph = 0;
	private static Font fontInstance;
	public static Font getInstance() {
		if(Font.fontInstance == null) {
			Font.fontInstance = new Font();
		}
		return Font.fontInstance;
	}

	public void draw(Gfx g, Point2D position, String text) {
		this.draw(g, position.getX(), position.getY(), text);
	}

	public void draw(Gfx g, int x, int y, String text) {
		text = text.toUpperCase();
		for(int i = 0; i < text.length(); i++) {
			int glyphPos = this.glyphs.indexOf(text.charAt(i));
			if(glyphPos >= 0) {
				int row = glyphPos / this.glyphsPerRow;
				int col = (glyphPos - (row * this.glyphsPerRow));
				g.drawSprite((World.getResourceManager().getSpritesheet("font").getSpriteWidth() * i) + x, y, World.getResourceManager().getSpritesheet("font").getSprite(col, row + this.startPosFirstGlyph));
			}
		}
	}
}