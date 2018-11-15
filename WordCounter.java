package at.tw.ose;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {

	private String text; 
	private LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

	
	public WordCounter() {
		// text is initialized with an empty string so the concat() method can be used 
		this.text = ""; 
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

	public LinkedHashMap<String, Long> getReverseSortedMap() {
		return reverseSortedMap;
	}

	public void setReverseSortedMap(LinkedHashMap<String, Long> reverseSortedMap) {
		this.reverseSortedMap = reverseSortedMap;
	}

	public void countWords() {
		
		// prepare text for processing 
		this.text = text.replaceAll("[-+.^:,]", "")
				.replace('\n', ' ')
				.replace('\t', ' ')
				.replace('\r', ' ')
				.trim()
				.toLowerCase(); 
		String[] stringArray = text.split(" "); 
		
		// process every word in text 
		Map<String, Long> map = Arrays.stream(stringArray)
			    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		//System.out.println(map);
		
		// not needed
//		Map<String, Long> sortedMap = map.entrySet().stream()
//		          .sorted(Comparator.comparing(Entry::getValue))
//	              .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
//	                      (e1, e2) -> e1, LinkedHashMap::new));
		//System.out.println(sortedMap);
	
		// bring sorted map in reverse order 
		map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		System.out.println(reverseSortedMap);		
		
	} // countWords
	
}