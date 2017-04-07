package pl.edu.agh.kis.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GUI AUTOMATON
 * 
 * @author Micha³ Sieñczak
 *
 */
@SuppressWarnings("serial")
public class AutomatonGUI extends JFrame implements ActionListener {

	// dane pobrane z pól
	private int currentGame = 0;
	private int width = 10;
	private int height = 10;
	private int radious = 1;
	private boolean wrapping = true;
	private String neighbourhoodStrategy = "MoorNeighbourhood";
	private int structureID = 0;
	private int rule1D = 30;
	private String ruleGOF = "23/3";
	Timer timerGOF, timerQL, timer1D;
	Map<CellCoordinates, CellState> mapOfCells = new HashMap<CellCoordinates, CellState>();
	Automaton automat;
	// panele poszczególnych gier
	JPanel JPGameOfLife = new JPanel();
	JPanel JPQuadLife = new JPanel();
	JPanel JPWireWorld = new JPanel();
	JPanel JPLangtonAnt = new JPanel();
	JPanel JPAutomat1D = new JPanel();

	// szczególne menu Game of Life
	JPanel JPGameOfLifeMenu = new JPanel();
	JButton JBStartGOF = new JButton("Start");
	JButton JBStopGOF = new JButton("Stop");
	JLabel JLRuleGOF = new JLabel("Set rule:");
	JFormattedTextField JFTFRuleGOF = new JFormattedTextField();
	JComboBox<String> JCBStructureGOF;
	// szczególne Wire World
	JPanel JPWireWorldMenu = new JPanel();
	JButton JBStartWW = new JButton("Start");
	JButton JBStopWW = new JButton("Stop");
	// szczególne menu Langton Ant
	JPanel JPLangtonAntMenu = new JPanel();
	JButton JBStartLA = new JButton("Start");
	JButton JBStopLA = new JButton("Stop");
	// szczególne menu Quad Life
	JPanel JPQuadLifeMenu = new JPanel();
	JButton JBStartQL = new JButton("Start");
	JButton JBStopQL = new JButton("Stop");
	// szczególne menu Automatu 1D
	JPanel JP1DMenu = new JPanel();
	JButton JBStart1D = new JButton("Start");
	JButton JBStop1D = new JButton("Stop");
	JLabel JLRule1D = new JLabel("Set rule:");
	JFormattedTextField JFTFRule1D = new JFormattedTextField();

	// menu w ka¿dej grze
	JLabel JLWidth = new JLabel("Set width:");
	JLabel JLHeight = new JLabel("Set height:");
	JLabel JLRadious = new JLabel("Set radious of neighbourhood:");
	JLabel JLNeighbourhoodStrategy = new JLabel("Set neighbourhood strategy:");
	JLabel JLWrapping = new JLabel("Set wrapping:");
	JPanel JPCommonMenu = new JPanel();
	JFormattedTextField JFTFWidth = new JFormattedTextField();
	JFormattedTextField JFTFHeight = new JFormattedTextField();
	JSpinner JSRadiousOfNeighbourhood = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
	JSpinner JSNeighbourhoodStrategy;
	JSpinner JSWrapping;

	// mapa gry
	JPanel JPBoard = new JPanel();
	JButton[][] JBCells;

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);

	public AutomatonGUI() {

		// dodanie elementów panelu gier
		tabbedPane.addTab("Game of Life", JPGameOfLife);
		tabbedPane.addTab("Quad Life", JPQuadLife);
		tabbedPane.addTab("WireWorld", JPWireWorld);
		tabbedPane.addTab("LangtonAnt", JPLangtonAnt);
		tabbedPane.addTab("Automat 1D", JPAutomat1D);

		// ustawienie powszechnego menu(Menu potrzebne do ka¿dej grze)
		String[] neighbours = new String[2];
		neighbours[0] = "MoorNeighbourhood";
		neighbours[1] = "VonNeumanNeighbourhood";
		JSNeighbourhoodStrategy = new JSpinner(new SpinnerListModel(neighbours));

		String[] wrap = new String[2];
		wrap[0] = "With wrapping";
		wrap[1] = "Without wrapping";
		JSWrapping = new JSpinner(new SpinnerListModel(wrap));
		JFTFWidth.setValue(new Integer(10));
		JFTFHeight.setValue(new Integer(10));

		// dodanie elementów powszechnego menu
		JPCommonMenu.setLayout(new GridLayout(5, 2));
		JPCommonMenu.add(JLWidth);
		JPCommonMenu.add(JFTFWidth);
		JPCommonMenu.add(JLHeight);
		JPCommonMenu.add(JFTFHeight);
		JPCommonMenu.add(JLRadious);
		JPCommonMenu.add(JSRadiousOfNeighbourhood);
		JPCommonMenu.add(JLNeighbourhoodStrategy);
		JPCommonMenu.add(JSNeighbourhoodStrategy);
		JPCommonMenu.add(JLWrapping);
		JPCommonMenu.add(JSWrapping);

		// menu gry Game of Life
		String[] structures = { "Cell", "Glider", "Gun", "Penthadecathlon"};
		JCBStructureGOF = new JComboBox<String>(structures);
		JPGameOfLifeMenu.add(JCBStructureGOF);
		JBStartGOF.addActionListener(this);
		JPGameOfLifeMenu.add(JBStartGOF);
		JBStopGOF.addActionListener(this);
		JPGameOfLifeMenu.add(JBStopGOF);
		JPGameOfLifeMenu.add(JLRuleGOF);
		JFTFRuleGOF.setValue(new String("23/3"));
		JPGameOfLifeMenu.add(JFTFRuleGOF);

		// menu gry Wire World
		JPWireWorldMenu.add(JBStartWW);
		JPWireWorldMenu.add(JBStopWW);

		// menu gry Langton Ant
		JPLangtonAntMenu.add(JBStartLA);
		JPLangtonAntMenu.add(JBStopLA);

		// menu gry Quad Life
		JBStartQL.addActionListener(this);
		JBStopQL.addActionListener(this);
		JPQuadLifeMenu.add(JBStartQL);
		JPQuadLifeMenu.add(JBStopQL);

		// menu gry 1D
		JP1DMenu.add(JLRule1D);
		JFTFRule1D.setValue(new Integer(30));
		JP1DMenu.add(JFTFRule1D);
		JBStart1D.addActionListener(this);
		JP1DMenu.add(JBStart1D);
		JBStop1D.addActionListener(this);
		JP1DMenu.add(JBStop1D);

		// g³ówne okno
		this.add(tabbedPane);
		setTitle("Automaty");
		this.setSize(1200, 800);

		JPGameOfLife.add(JPCommonMenu);
		JPGameOfLife.add(JPGameOfLifeMenu);
		JPGameOfLife.add(JPBoard);
		setActionListeners();
		setButtons(height, width);
		setCells();
		setTimers();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentGame == 0) {
			new GameOfLifeSetCellsGUI(e, mapOfCells, JBCells, width, height, JBStartGOF, JBStopGOF, structureID,
					timerGOF);
		} else if (currentGame == 1) {
			new QuadLifeSetCellsGUI(e, mapOfCells, JBCells, width, height, JBStartQL, JBStopQL, timerQL);
		} else if (currentGame == 4) {
			new Automaton1DSetCellsGUI(e, mapOfCells, JBCells, width, JBStart1D, JBStop1D, timer1D, rule1D, radious);
			
		}


	}

	/**
	 * metoda pobieraj¹ce dane z pól menu
	 */
	private void setActionListeners() {

		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if ((tabbedPane.getSelectedIndex() == 0)) {
					currentGame = 0;
					JPGameOfLife.add(JPCommonMenu);
					JPGameOfLife.add(JPGameOfLifeMenu);
					JPGameOfLife.add(JPBoard);
					setButtons(height, width);
					setCells();
				} else if (tabbedPane.getSelectedIndex() == 1) {
					currentGame = 1;
					JPQuadLife.add(JPCommonMenu);
					JPQuadLife.add(JPQuadLifeMenu);
					JPQuadLife.add(JPBoard);
					setButtons(height, width);
					setCells();
				} else if (tabbedPane.getSelectedIndex() == 2) {
					currentGame = 2;
					JPWireWorld.add(JPCommonMenu);
					JPWireWorld.add(JPWireWorldMenu);
					JPWireWorld.add(JPBoard);
					setButtons(height, width);
					setCells();
				} else if (tabbedPane.getSelectedIndex() == 3) {
					currentGame = 3;
					JPLangtonAnt.add(JPCommonMenu);
					JPLangtonAnt.add(JPLangtonAntMenu);
					JPLangtonAnt.add(JPBoard);
					setButtons(height, width);
					setCells();
				} else if (tabbedPane.getSelectedIndex() == 4) {
					currentGame = 4;
					height = 1;
					JPAutomat1D.add(JPCommonMenu);
					JPAutomat1D.add(JP1DMenu);
					JPAutomat1D.add(JPBoard);
					setButtons(height, width);
					setCells();
					
				}
			}
		});
		
		JFTFWidth.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (currentGame == 4 ){
					width = (int) JFTFWidth.getValue();
					height = 1;
				}
				else width = (int) JFTFWidth.getValue();
				setButtons(height, width);
				setCells();
			}
		});

		JFTFHeight.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (currentGame == 4 ){
					height = 1;
				}
				else height = (int) JFTFHeight.getValue();
				
				setButtons(height, width);
				setCells();
			}
		});

		JSRadiousOfNeighbourhood.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				radious = (int) JSRadiousOfNeighbourhood.getValue();
			}
		});

		JSNeighbourhoodStrategy.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				neighbourhoodStrategy = (String) JSNeighbourhoodStrategy.getValue();
			}
		});

		JSWrapping.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (JSWrapping.getValue() == "With wrapping") {
					wrapping = true;
				} else
					wrapping = false;
			}
		});

		JFTFRule1D.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				rule1D = (int) JFTFRule1D.getValue();
				if (rule1D > 255) {
					rule1D = 255;
				}
				if (rule1D < 0) {
					rule1D = 0;
				}
			}
		});

		JFTFRuleGOF.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				ruleGOF = (String) JFTFRuleGOF.getValue();
			}
		});

		JCBStructureGOF.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> JCTmp = (JComboBox<String>) e.getSource();
				String selectedStructure = (String) JCTmp.getSelectedItem();
				if (selectedStructure.equals("Cell")) {
					structureID = 0;
				} else if (selectedStructure.equals("Glider")) {
					structureID = 1;
				} else if (selectedStructure.equals("Gun")) {
					structureID = 2;
				} else if (selectedStructure.equals("Penthadecathlon")) {
					structureID = 3;
				}
			}
		});
	}

	private void setTimers() {
		timerGOF = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GameOfLifeNextStateGUI(e, automat, mapOfCells, neighbourhoodStrategy, wrapping, radious, JBCells,
						width, height, ruleGOF);
			}
		});

		timerQL = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new QuadLifeNextStateGUI(e, automat, mapOfCells, neighbourhoodStrategy, wrapping, radious, JBCells,
						width, height);
			}
		});
		
		timer1D = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Automaton1DNextStateGUI(e, automat, mapOfCells, wrapping, radious, JBCells,
						width, height, rule1D);
			}
		});
	}

	public void setButtons(int height, int width) {
		JBCells = new JButton[width+1][height+1];
		JPBoard.removeAll();
		JPBoard.setLayout(new GridLayout(height, width));
		if (currentGame != 4)
			JPBoard.setPreferredSize(new Dimension(600, 600));
		else
			JPBoard.setPreferredSize(new Dimension(600, 100));

		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				JBCells[j][i] = new JButton();
				JBCells[j][i].setBackground(Color.WHITE);
				JPBoard.add(JBCells[j][i]);
				JBCells[j][i].addActionListener(this);
			}
		}
	}

	public void setCells() {
		mapOfCells.clear();
		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (currentGame == 0) {
					mapOfCells.put(new Coords2D(i, j), BinaryState.DEAD);
				} else if (currentGame == 1) {
					mapOfCells.put(new Coords2D(i, j), QuadState.DEAD);
				} else if (currentGame == 4) {
					mapOfCells.put(new Coords1D(i), BinaryState.DEAD);
				}
			}
		}
	}
}
