package nl.logiconline.neptune.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nl.logiconline.neptune.World;
import nl.logiconline.neptune.assets.gfx.Sprite;
import nl.logiconline.neptune.assets.gfx.SpriteSheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class ResourceManager {

	private HashMap<String, SpriteSheet> spritesheets = new HashMap<String, SpriteSheet>();
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

	public void initResourceManager(String resourcefile) {
		try {
			Document doc = this.readXML(World.class.getResourceAsStream(resourcefile));
			this.parseDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Document readXML(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		db = dbf.newDocumentBuilder();
		return db.parse(is);
	}

	private void parseDocument(Document doc) {
		//Get spritesheets
		NodeList nodes = doc.getElementsByTagName("spritesheet");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element el = (Element)nodes.item(i);
			String name = el.getAttribute("name");
			String file = el.getAttribute("file");
			int width = Integer.parseInt(el.getAttribute("width"));
			int height = Integer.parseInt(el.getAttribute("height"));
			try {
				this.addSpritesheet(name, ImageIO.read(World.class.getResourceAsStream(file)), width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//Get sprite
		nodes = doc.getElementsByTagName("sprite");
		for(int i = 0; i < nodes.getLength(); i++) {
			Element el = (Element)nodes.item(i);
			String name = el.getAttribute("name");
			String file = el.getAttribute("file");
			try {
				this.addSprite(name, ImageIO.read(World.class.getResourceAsStream(file)));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void addSpritesheet(String name, BufferedImage image, int width, int height) {
		if(this.spritesheets.containsKey(name)) {
			throw new IllegalArgumentException("SpriteSheet for key " + name + " already exist!");
		}
		this.spritesheets.put(name, new SpriteSheet(image, width, height));
	}

	public SpriteSheet getSpritesheet(String name) {
		if(!this.spritesheets.containsKey(name)) {
			throw new IllegalArgumentException("SpriteSheet for key " + name + " does not exist!");
		}
		return this.spritesheets.get(name);
	}

	public void addSprite(String name, BufferedImage image) {
		if(this.sprites.containsKey(name)) {
			throw new IllegalArgumentException("Sprite for key " + name + " already exist!");
		}
		this.sprites.put(name, new Sprite(image));
	}

	public Sprite getSprite(String name) {
		if(!this.sprites.containsKey(name)) {
			throw new IllegalArgumentException("Sprite for key " + name + " does not exist!");
		}
		return this.sprites.get(name);
	}
}
