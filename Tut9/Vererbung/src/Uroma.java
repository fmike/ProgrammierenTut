public class Uroma extends Oma {
public Uroma(String name) {
super(name);
this.titel = "Uroma";
} /*
public void anstrengend() {
System.out.println(this.toString() + ": \"Bist du gross geworden!\"");
}*/
public void anstrengend(String s) {
System.out.println(this.toString() + ": \"Ist der Kuchen nicht "
+ s + "?\"");
}
public void anstrengend(int i) {
System.out.println(this.toString() + ": \"Nimm doch noch "
+ i + " Stueck Kuchen.\"");
}
}