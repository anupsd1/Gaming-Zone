import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuTest extends GameManager implements ActionListener {
	public static void main(String args[]) {
		// new MenuTest().init();
		new MenuTest().run();
		// new MenuTest().run();
	}

	protected GameAction configAction;

	private JButton playBtn;
	private JButton configBtn;
	private JButton quitBtn;
	private JButton pauseBtn;
	private JPanel playBtnSpace;

	// protected Container contentPane;

	public void init() {
		super.init();
		// make sure Swing components don't paint themselves
		NullRepaintManager.install();

		// create an additional GameAction for "config"
		configAction = new GameAction("config");

		// create buttons
		quitBtn = createButton("stop", "Quit");
		playBtn = createButton("play", "Continue");
		pauseBtn = createButton("pause", "Pause");
		configBtn = createButton("config", "Change Settings");
		// score = new JLabel("SCORE");
		// score.setFont(new Font(FONT.Arial));
		// playBtn.setText("P FOR PAUSE");
		// create the space where the play/pause buttons go.
		playBtnSpace = new JPanel();
		playBtnSpace.setOpaque(false);
		playBtnSpace.add(pauseBtn);

		JFrame frame = (JFrame) super.s.getFullScreenWindow();

		Container contentPane = frame.getContentPane();

		/*
		 * JPanel btns=new JPanel(); btns.setLayout(new FlowLayout(FlowLayout.LEFT));
		 * btns.add(playBtnSpace); btns.add(configBtn); btns.add(quitBtn);
		 * 
		 * JPanel scrs=new JPanel(); scrs.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 * scrs.add(super.score);
		 */

		// make sure the content pane is transparent
		if (contentPane instanceof JComponent) {
			((JComponent) contentPane).setOpaque(false);
		}

		// contentPane.add(btns);
		// contentPane.add(scrs);

		// add component to the screen's content pane
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(playBtnSpace);
		contentPane.add(configBtn);
		contentPane.add(quitBtn);
		contentPane.add(super.score);

		// contentPane2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// contentPane2.add(super.score);

		// explicitly lay out components (needed on some systems)
		frame.validate();
	}

	// Extends GameManager's functionality to draw all Swing components
	public void draw(Graphics2D g) {
		super.draw(g);
		JFrame frame = (JFrame) super.s.getFullScreenWindow();

		// the layered pane contains things like popups(tooltips,popup menus) and the content pane
		frame.getLayeredPane().paintComponents(g);
	}

	// Change the pause/play button whenever the pause state changes
	public void setPaused(boolean p) {
		super.setPaused(p);
		playBtnSpace.removeAll();
		if (isPaused()) {
			playBtnSpace.add(playBtn);
		} else {
			playBtnSpace.add(pauseBtn);
		}
	}

	// Called by the AWT event dispatch thread when a button is pressed
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == quitBtn) {
			// fire the "exit" gameAction
			super.exit.tap();
		} else if (src == configBtn) {
			// doesn't do anything (for now)
			configAction.tap();
		} else if (src == playBtn || src == pauseBtn) {
			// fire the "pause" gameAction
			super.pause.tap();
		}
	}

	/*
	 * Creates a Swing JButton. The image is modified to create a "default" look
	 * (translucent) and a "pressed" look (moved down and to the right). The button
	 * doesn't use Swing's look-and-feel and instead uses the image.
	 */
	public JButton createButton(String name, String toolTip) {
		// load the image
		String imagePath = "C://Java//Game Development2//images//" + name + ".png";
		// Image img=Toolkit.getDefaultToolkit().getImage(imagePath);
		// URL iconURL=getClass().getResource(imagePath);
		ImageIcon iconRollover = new ImageIcon(imagePath);
		// int w=iconRollover.getIconWidth();
		// int h=iconRollover.getIconHeight();
		int w = 200;
		int h = 200;
		System.out.println("Width= " + w);
		System.out.println("Height= " + h);
		// get the cursor for this button
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

		// make translucent default image
		Image image = s.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		if (image != null) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		Graphics2D g = (Graphics2D) image.getGraphics();
		if (g != null) {
			System.out.println("image is true");
		} else {
			System.out.println("image is false");
		}
		Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f);
		g.setComposite(alpha);
		g.drawImage(iconRollover.getImage(), 0, 0, null);
		g.dispose();
		ImageIcon iconDefault = new ImageIcon(image);
		if (iconDefault != null) {
			System.out.println("IconDefault is true");
		}

		// make a pressed image
		image = s.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
		g = (Graphics2D) image.getGraphics();
		if (g != null) {
			System.out.println("image is true");
		} else {
			System.out.println("image is false");
		}
		g.drawImage(iconRollover.getImage(), 2, 2, null);
		g.dispose();
		ImageIcon iconPressed = new ImageIcon(image);
		if (iconPressed != null) {
			System.out.println("IconPressed is true");
		}
		// create the button
		JButton button = new JButton();

		button.addActionListener(this);

		button.setIgnoreRepaint(true);

		button.setFocusable(false);

		button.setToolTipText(toolTip);

		button.setBorder(null);

		button.setContentAreaFilled(false);

		button.setCursor(cursor);

		button.setIcon(iconDefault);

		button.setRolloverIcon(iconRollover);

		button.setPressedIcon(iconPressed);

		return button;
	}

	/*
	 * private void updateScore(int count) { score.setText(String.valueOf(count)); }
	 */
}
