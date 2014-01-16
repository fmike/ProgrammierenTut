
public class Familienfest {

	public static void main(String[] args) {
		
		Uroma u1 = new Uroma("Irmgard");
		Oma o1 = u1;
		Oma o2 = new Oma("Jutta");
		
		Mutter m1 = new Mutter("Julia");
		Mutter m2 = o2;
		Mutter m3 = o1;
		
		
	
		m3.anstrengend(3);
		m2.anstrengend(2);
		m1.anstrengend(0.5);
		m3.anstrengend("fantastisch");
		m3.anstrengend();
		m2.anstrengend("hervorragend");
		m1.anstrengend("gut");
		u1.anstrengend();
		u1.anstrengend("wunderbar");
		o2.anstrengend();
		o1.anstrengend(3.14);
		
		Kuchen k1 = new Kuchen();
		Kuchen k2 = new Marmorkuchen();
		Marmorkuchen mk = new Marmorkuchen();
		m1.backe(k1);
		m1.backe(k2);
		m1.backe(mk);
		o1.backe(k1);
		m2.backe(k2);
		m3.backe(mk);
	}
}
