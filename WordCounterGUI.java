/**
 * 
 */
package at.tw.ose;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

/**
 * @author Werner Egermann 
 *
 * for debugging: Lorem_ipsum.txt contains 9230 words 
 * 
 * 
 */
public class WordCounterGUI {

	/**
	 * @param args
	 */
	private JFrame frame; 
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTable table;
	private WordCounterTableModel model = new WordCounterTableModel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// with the following line, the app looks like a windows app
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					WordCounterGUI window = new WordCounterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Constructor 
	public WordCounterGUI() {
		initialize(); 
	}

	private void initialize() {
		//final WordCounterGUI wcgui = this; 
		frame = new JFrame(); 
		frame.setResizable(false); 
		frame.setTitle("Word Counter");
		frame.setBounds(200, 200, 800, 500); // set overall window size 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		// make a pretty layout 
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		textArea.setEditable(false);
		frame.getContentPane().add(textArea);
		
		// textArea scrollpane 
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(6, 6, 530, 411);   
		frame.getContentPane().add(scrollPane);
		
		// table that displays counted words 
		table = new JTable();
		table.setModel(model);
		// possibility to click and select a single cell 
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(116);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		
		// not needed because table is in scrollPane
		//frame.getContentPane().add(table); 
		
		// table scrollPane  
        JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.setBounds(550, 6, 232, 411); 
		scrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// maybe not needed??? 
		//scrollPaneTable.setViewportView(table);
		frame.getContentPane().add(scrollPaneTable);
		
		///////////////////////////////////////////////////////////////////
		// Menu bar 
		menuBar = new JMenuBar(); 
		frame.setJMenuBar(menuBar); 
		
		mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Load...");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// action to read an input file
				JFileChooser chooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("text file", new String[] { "txt" });
				chooser.setFileFilter(filter);
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					TxtFileReader reader = null; 
					
					WordCounter wordCounter = new WordCounter(); 
					
					try {
						reader = new TxtFileReader(file); 
						
						// display file-text  
						textArea.setText(reader.getText());
						
						// process file 
						wordCounter.setText(reader.getText()); 
						wordCounter.countWords(); 
						
						// fill table 
						//model.setReverseSortedMap(wordCounter.getReverseSortedMap());
						model.addEntry(wordCounter.getReverseSortedMap());
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Error during load", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnNewMenu.add(mntmNewMenuItem);

	}
}
