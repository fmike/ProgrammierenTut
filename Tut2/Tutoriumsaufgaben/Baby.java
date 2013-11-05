class Baby {

	String name;
	int gewicht; //in gramm
	int groesse; // in cm 
	int lautstaerke;
	
	public Baby(String na, int gewicht, int groesse) {

	name = na;
	gewicht = gewicht;
	groesse = groesse;


	}

	void schreien(String name, int lautstaerke) {
	
	System.out.println(name + " schreit mit " + lautstaerke + " dB");		

	}

	
	public static void main(String[] args) {
	
	Baby anna = new Baby("Anna", 3000, 70)

	anna.schreien(anna.name, 200);
	
	}

}