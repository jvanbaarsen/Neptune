package nl.logiconline.neptune.system;
import nl.logiconline.neptune.World;
import nl.logiconline.neptune.assets.ResourceManager;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */

public class Neptune {
	public static boolean SHOW_FPS = true;
	public static boolean SHOW_DEBUG = true;
	public static boolean SHOW_ERROR = false;
	public static boolean SHOW_EVENTS = false;
	public static boolean SHOW_TIMESTAMP = false;
	private static ResourceManager resourceManager;
	private static World world;
	public static ResourceManager getResourceManager() {
		if (Neptune.resourceManager == null) {
			Neptune.resourceManager = new ResourceManager();
		}
		return Neptune.resourceManager;
	}
	public static World getWorld() {
		if(Neptune.world == null) {
			Neptune.world = new World();
		}
		return Neptune.world;
	}
}
