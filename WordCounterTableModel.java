package at.tw.ose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

/**
 * <h1> WordCounterTableModel </h1> 
 * 
 * This class implements methods to modify the table in which the words and 
 * their occurences are shown. 
 * 
 * @author   Werner Egermann & Thomas Jungwirth 
 * @version  1.0 
 * @since    2018-11-08 
 */

public class WordCounterTableModel extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 12345678987654321L;
	private String [] titles = new String [] { "Word", "Occurence"};
	//private ArrayList<WordEntry> entries = new ArrayList<WordEntry>();
	private LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>(); 
	
	@Override
	public int getRowCount() {
		return reverseSortedMap.size();
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	public LinkedHashMap<String, Long> getReverseSortedMap() {
		return reverseSortedMap;
	}

	public void setReverseSortedMap(LinkedHashMap<String, Long> reverseSortedMap) {
		this.reverseSortedMap = reverseSortedMap;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Set<Map.Entry<String, Long>> entry = reverseSortedMap.entrySet();
		Map.Entry<String, Long> element = (new ArrayList<Map.Entry<String, Long>>(entry)).get(rowIndex);
		if (entry != null)
		{
			switch(columnIndex)
			{
			case 0:
				return element.getKey();
			case 1:
				return element.getValue();
			default:
				return null;		
			}
		}
		return null;
	}

	
	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
	
	/**
	 * 
	 * @param WordEntry entry
	 * This function adds the structure consisting of words and their occurences to the table. 
	 * The structure is already sorted. This is the last step before showing the result of the 
	 * WordCount program. 
	 */
	
	public void addEntry(LinkedHashMap<String, Long> reverseSortedMap) {
		//reverseSortedMap.add(reverseSortedMap); 
		this.reverseSortedMap = reverseSortedMap;
		fireTableDataChanged(); 
	}
	
	@Override
	// table is not editable, but clickable and selectable 
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
