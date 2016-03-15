
import demomvc.PaintPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class DemoView extends JFrame {
    /** 
     * the model of this MVC example 
     */
	private final DemoModel model;
	
	/** 
	 * the JPanel where the user can paint
	 */
	private final PaintPanel mousePanel;
	
	/** 
	 * for displaying a list of colors
	 */
	private final JList colorList;
	
	/** 
	 * the panel where the JList will be placed 
	 */
	private final JPanel listPanel;

	/** 
	 * the String names of the colors that the user can select 
	 */
	private static final String[] colorNames = {"Black", "Blue", "Cyan",
		"Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
		"Orange", "Pink", "Red", "White", "Yellow"};
	
	/** 
	 * the Colors that the user can select 
	 */
	private static final Color[] colours = {Color.BLACK, Color.BLUE,
		Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
		Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
		Color.RED, Color.WHITE, Color.YELLOW};

	/**
	 * Create and organize the components of the window.
     * @param model
	 */
	public DemoView(DemoModel model) {
		super("Illustrate Model-View-Controller");
		this.model = model;

		/* CENTER: Add a panel that the user can draw on. */
		mousePanel = new PaintPanel(model);
		mousePanel.setBackground(Color.WHITE);
		add(mousePanel, BorderLayout.CENTER);

		/* WEST: Add a list so the user can select a color. */
		listPanel = new JPanel();
		add(listPanel, BorderLayout.WEST);
		colorList = new JList(colorNames);
		colorList.setVisibleRowCount(5);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPanel.add(new JScrollPane(colorList), BorderLayout.CENTER);
	} // end constructor

	/**
	 * Register the controller as the listener to the JList and the
	 * MousePanel.
	 * @param listener
	 */
	public void registerListener(DemoController listener) {
		colorList.addListSelectionListener(listener);
		mousePanel.addMouseMotionListener(listener);
	}

	/**
	 * @return The color selected by the user.
	 */
	public Color getSelectedColor() {
		return colours[colorList.getSelectedIndex()];
	}

	/**
	 * Sets the background color of the JList and calls super.paint(g)
	 * to paint the components.
	 */
        @Override
	public void paint(Graphics g) {
		listPanel.setBackground(model.getSelectedColor());
		super.paint(g); // This will paint the components.
	} // end method paint
}
