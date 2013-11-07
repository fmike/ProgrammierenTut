class Baby {

	String name;
	int weight; //in grams
	int heigt; // in cm 
	int volume; // in dB
	
	public Baby(String na, int weight, int height) {

	name = na;
	weight= weight;
	height= height;


	}

	void scream(String name, int volume) {
	
	System.out.println(name + " screams with " + volume+ " dB");		

	}

	
	public static void main(String[] args) {
	
	Baby anna = new Baby("Anna", 3000, 70)

	anna.scream(anna.name, 200);
	
	}

}