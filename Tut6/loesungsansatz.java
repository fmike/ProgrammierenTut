public class FiniteSet {

	private int[] elements;
	private int lastElem; //Zeiger um nicht unnötig oft durchlaufen zu müssen

	public FiniteSet(int maxSize) {
		elements = new int[maxSize];
		lastElem = 0;
	}
	
	public void insert(int elem){
	
	if (!contains(elem)) {
		//es gibt noch Platz -> einfach hinten Einfügen
		if (lastElem < elements.length)  {
			
			elements[lastElem] = elem;
			lastElem++;
			
		} else { //array ersetzen.
		
		int[] help = elements;
		elements = new int[2 * help.length];
		
		//kopieren der Elemente
		for(int i = 0; i < help.length; i++){
		
			elements[i] = help[i];
			
		}
		}
	}
	}
	
	public void remove(int elem) {
		
		if(contains(elem)){
		
		//Element suchen
		int indexFound = 0;
		for(int i = 0;i < elements.length; i++) {
		
			if(elements[i] == elem) {
		
			indexFound = i;
			
			}
		}
		//set[i] = 0;
		//Lücke füllen und damit implizit löschen 
		for(int j = indexFound; j < elements.length; j++) {
		elements[j] = elements[j+1];
		}
		
		lastElem--;
		
		}
	}
	
    /**
     * Pr&uuml;ft, ob das angegebene Element in der Menge enthalten ist.
     *
     * @param elem Das zu pr&uuml;fende Element
     *
     * @return true, falls das Element in der Menge enthalten ist, sonst
     * false.
     */
    public boolean contains(int elem) {
	for (int idx = 0; idx < lastElem; idx++) {
	    if (elements[idx] == elem) {
		return true;
	    }
	}

	return false;
    }
	
    /**
     * Bildet den Schnitt der aktuellen Menge mit einer zweiten Menge.
     * 
     * @param set Die Menge, mit der der Schnitt gebildet werden soll.
     *
     * @return Ein neues <code>FiniteSet</code>-Objekt, das den
     * Schnitt der beiden Mengen repr&auml;sentiert.
     */
    public FiniteSet intersect(FiniteSet set) {
	FiniteSet result = new FiniteSet(Math.min(elements.length, set.elements.length));

	for (int idx = 0; idx < lastElem; idx++) {
	    if (set.contains(elements[idx])) {
		result.insert(elements[idx]);
	    }
	}

	return result;
    }

    /**
     * Bildet die Vereinigung der aktuellen Menge mit einer zweiten Menge.
     *
     * @param set Die Menge, die mit der aktuellen Menge vereinigt
     * werden soll.
     *
     * @return Ein neues <code>FiniteSet</code>-Objekt, welches
     * die union der beiden Mengen repr&auml;sentiert.
     */
    public FiniteSet union(FiniteSet set) {
	FiniteSet result
	    = new FiniteSet(elements.length + set.elements.length);

	for (int idx = 0; idx < lastElem; idx++) {
	    result.insert(elements[idx]);
	}

	for (int idx = 0; idx < set.lastElem; idx++) {
	    result.insert(set.elements[idx]);
	}

	return result;
    }

    /**
     * Bildet die Differenz der aktuellen Menge mit einer zweiten Menge.
     *
     * @param set Die Menge, welche von der aktuellen Menge abgezogen
     * werden soll.
     *
     * @return Ein neues <code>FiniteSet</code>-Objekt, welches
     * die difference der aktuellen Menge mit <code>set</code>
     * repr&auml;sentiert.
     */
    public FiniteSet difference(FiniteSet set) {
	FiniteSet result = new FiniteSet(elements.length);

	for (int idx = 0; idx < lastElem; idx++) {
	    if (!set.contains(elements[idx])) {
		result.insert(elements[idx]);
	    }
	}

	return result;
	
    }

    /**
     * Vergleicht die aktuelle Menge mit einer zweiten Menge.
     *
     * @param set Die Menge, mit der die aktuelle Menge verglichen werden soll
     *
     * @return true, falls <code>this</code> und <code>set</code>
     * die selben Elemente enthält, sonst false.
     */
    public boolean equals(FiniteSet set) {
	boolean equal;

	if (lastElem != set.lastElem) {
	    equal = false;
	} else {
	    equal = true;
	    for (int idx = 0; idx < lastElem; idx++) {
		equal = equal && set.contains(elements[idx]);
	    }
	}

	return equal;
    }

    /**
     * Liefert eine <code>String</code>-Darstellung der aktuellen set.
     *
     * @return Eine <code>String</code>-Darstellung der aktuellen set
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("{ ");

	for (int idx = 0; idx < lastElem; idx++) {
	    sb.append(elements[idx]);
	    sb.append(", ");
	}

	return sb.substring(0,Math.max(sb.length() - 2, 2)) + " }";
    }
}