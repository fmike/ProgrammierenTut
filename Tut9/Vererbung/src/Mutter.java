
public class Mutter {
	protected String name;
	protected String titel;
	public Mutter(String name) {
	this.name = name;
	this.titel = "Mama";
	}
	public void anstrengend() {
	
	}
	public void anstrengend(int k) {
	System.out.println(this.toString() + ": \"" + k
	+ "Stueck Kuchen sind genug!\"");
	}
	public void anstrengend(String s) {
	System.out.println(this.toString() + ": \"So viel Kuchen tut dir nicht "
	+ s + "!\"");
	}
	public void anstrengend(double l) {
	System.out.println(this.toString() + ": \"Du kannst auch nur " + l
	+ " Stueck Kuchen haben.\"");
	}
	public void backe(Kuchen k) {
	System.out.println(this.toString() + " baeckt Kuchen.");
	}
	public void backe(Marmorkuchen m) {
	System.out.println(this.toString() + " baeckt Marmorkuchen.");
	}
	public String toString() {
	return this.titel + " " + this.name;
	}
	
}
