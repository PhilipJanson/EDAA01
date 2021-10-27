package textproc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 800, 500));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {

		// Create JFrame and pane
		JFrame frame = new JFrame(title);
		Container pane = frame.getContentPane();

		// Create SortedListModel, JList and JScrollPane and add them to the pane
		SortedListModel<Map.Entry<String, Integer>> sortedList = new SortedListModel<Map.Entry<String, Integer>>(counter.getWordList());
		JList<Map.Entry<String, Integer>> list = new JList<Map.Entry<String, Integer>>(sortedList);
		JScrollPane scrollPane = new JScrollPane(list);
		pane.add(scrollPane);

		// Create the JPanel and add it to the pane
		JPanel panel = new JPanel();
		pane.add(panel, BorderLayout.SOUTH);

		// Create the two JRadioButtons and add them to the ButtonGroup and add it to the JPanel
		ButtonGroup group = new ButtonGroup();
		JRadioButton alph = new JRadioButton("Alphabetical");
		JRadioButton freq = new JRadioButton("Frequency");
		group.add(alph);
		group.add(freq);
		panel.add(alph);
		panel.add(freq);

		// Create the JTextField and the search JButton and add them to the JPanel
		JTextField textField = new JTextField("Enter search word");
		JButton search = new JButton("Search");
		panel.add(textField);
		panel.add(search);

		// Add the FocusListener and KeyListener to the JTextField
		textField.addFocusListener(new ClearTextFieldListener());
		textField.addKeyListener(new SearchListener(search));

		// Add ActionListeners to the two JRadioButtons to handle sorting
		alph.addActionListener(e -> sortedList.sort((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey())));
		freq.addActionListener(e -> sortedList.sort((o1, o2) -> o2.getValue() - o1.getValue()));

		// Add the ActionListener to the search JButton to handle searching the JList
		search.addActionListener(e -> {
			int index = 0;
			for (; index < sortedList.getSize(); index++) {
				if (sortedList.getElementAt(index).getKey().equals(textField.getText().trim().toLowerCase())) {
					break;
				}
			}

			if (index >= sortedList.getSize()) {
				JOptionPane.showMessageDialog(frame, "Word not found", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				list.ensureIndexIsVisible(index);
				list.setSelectedIndex(index);
			}
		});

		// Pack the JFrame and add closing, size, visibility, resizability and location options to it
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	private static class ClearTextFieldListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			((JTextField) e.getComponent()).setText(null);
		}

		@Override
		public void focusLost(FocusEvent e) {
		}
	}

	private static class SearchListener implements KeyListener {

		private JButton button;

		private SearchListener(JButton b) {
			button = b;
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				button.doClick();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}