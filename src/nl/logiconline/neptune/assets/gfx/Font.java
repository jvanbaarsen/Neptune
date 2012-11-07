package nl.logiconline.neptune.assets.gfx;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.assets.gfx
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.system.Neptune;
import nl.logiconline.neptune.system.NeptuneException;
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

	public void draw(Gfx g, Point2D position, String text) throws NeptuneException {
		this.draw(g, position.getX(), position.getY(), text);
	}

	public void draw(Gfx g, double x, double y, String text) throws NeptuneException {
		text = text.toUpperCase();
		for(int i = 0; i < text.length(); i++) {
			int glyphPos = this.glyphs.indexOf(text.charAt(i));
			if(glyphPos >= 0) {
				int row = glyphPos / this.glyphsPerRow;
				int col = (glyphPos - (row * this.glyphsPerRow));
				g.drawSprite((Neptune.getResourceManager().getSpritesheet("font").getSpriteWidth() * i) + x, y, Neptune.getResourceManager().getSpritesheet("font").getSprite(col, row + this.startPosFirstGlyph));
			}
		}
	}
}
