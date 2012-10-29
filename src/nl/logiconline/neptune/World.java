package nl.logiconline.neptune;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune
 * (c) 2012 - LogicOnline
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

import nl.logiconline.neptune.assets.ResourceManager;
import nl.logiconline.neptune.states.State;
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.system.Neptune;
import nl.logiconline.neptune.utils.Color;
import nl.logiconline.neptune.utils.KeyInput;
import nl.logiconline.neptune.utils.Log;
import nl.logiconline.neptune.utils.Mouse;

public final class World extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static World instance = null;
	protected static ResourceManager resourceManager;

	private int width, height, scale;
	private int background = Color.BLACK;
	private BufferedImage image;
	private int[] pixels;
	private boolean running = false, stopped = true;
	private HashMap<Integer, State> states = new HashMap<Integer, State>();

	private State state = null;
	private Gfx g;

	private KeyInput keyInput = null;

	private World() {
	}

	public static World getInstance() {
		if (instance == null) {
			instance = new World();
		}
		return instance;
	}

	public void init(int width, int height, int scale) {
		super.setPreferredSize(new Dimension(width * scale, height * scale));
		super.setMinimumSize(new Dimension(width * scale, height * scale));
		super.setMaximumSize(new Dimension(width * scale, height * scale));

		this.width = width;
		this.height = height;
		this.scale = scale;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
		this.g = new Gfx(this.pixels, this.width, this.height);
	}

	public void start() {
		this.running = true;
		new Thread(this).start();
	}

	public void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		this.stopped = false;
		while (this.running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			while (unprocessed >= 1) {
				ticks++;
				this.update();
				unprocessed -= 1;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			frames++;
			this.clearPixels();
			this.render();

			if ((System.currentTimeMillis() - lastTimer1) > 1000) {
				lastTimer1 += 1000;
				if (Neptune.SHOW_FPS) {
					Log.debug(ticks + " ticks, " + frames + " fps");
				}
				frames = 0;
				ticks = 0;
			}
		}
		this.stopped = true;
	}

	public void update() {
		if (this.state != null) {
			this.state.update();
		}

	}

	public int getCanvasWidth() {
		return this.width;
	}

	public int getCanvasHeight() {
		return this.height;
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			this.requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (this.state != null) {
			this.state.render(this.g);
		}

		g.drawImage(this.image, 0, 0, this.width * this.scale, this.height * this.scale, null);
		g.dispose();
		bs.show();
	}


	/**
	 * Deprecated: Use Neptune.getResourceManager instead
	 * @return
	 */
	@Deprecated
	public static ResourceManager getResourceManager() {
		return Neptune.getResourceManager();
	}

	public boolean addState(int id, State state) {
		if (!this.states.containsKey(id)) {
			state.setId(id);
			this.states.put(id, state);
			return true;
		}
		return false;
	}

	public void clearPixels() {
		for (int i = 0; i < this.pixels.length; i++) {
			this.pixels[i] = this.background;
		}

	}

	public void setBackground(int color) {
		this.background = color;
	}

	public boolean enterState(int id) {
		if (this.states.containsKey(id)) {
			this.stop();
			while (!this.stopped) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.state = this.states.get(id);
			this.state.init();
			this.start();
		}
		return false;
	}

	public void enableMouseInput() {
		super.addMouseListener(new Mouse());
	}

	public void enableKeyInput() {
		this.keyInput = new KeyInput();
		super.addKeyListener(this.keyInput);
	}

	public KeyInput getKeyInput() {
		return this.keyInput;
	}

	public State getState() {
		return this.state;
	}

}
