public class FiniteSet {

	int[] set;
	int lastElem; //Zeiger um nicht unnötig oft durchlaufen zu müssen

	public FiniteSet(int maxSize) {
		set = new int[maxSize];
		lastElem = 0;
	}
	
	public void insert(int elem){
	
	if (!isFound(elem) {
		//es gibt noch Platz -> einfach hinten Einfügen
		if (lastElem < set.length)  {
			
			set[lastElem] = elem;
			lastElem++;
			
		} else { //array ersetzen.
		
		int[] help = set;
		set = new int[2 * help.length];
		
		//kopieren der Elemente
		for(int i = 0; i < help.length; i++){
		
			set[i] = help[i];
			
		}
	}
	
	public void remove(int elem) {
		
		if(isFound(elem){
		
		//Element suchen
		int indexFound;
		for(int i = 0;i < set.length; i++) {
		
			if(set[i] == elem) {
		
			indexFound = i;
			
			}
		}
		//set[i] = 0;
		//Lücke füllen und damit implizit löschen 
		for(int j = indexFound; j < set.length; j++) {
		set[i] = set[i+1];
		}
		
		lastElement--;
		
		}
	}
	
	//prüft, ob elem enthalten
	private boolean isFound(int elem) {
		boolean found = false;
	
	for(int j = 0; j < set.length; j++) {
		if (set[j] == j) {
		found = true;
		}
	}
	return found;
	}
	
	}