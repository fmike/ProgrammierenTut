public class Oma extends Mutter {
public Oma(String name) {
super(name);
this.titel = "Oma";
}

public void anstrengend(int j) {
System.out.println(this.toString() + ": \"Nimm doch noch " + j
+ " Muffins.\"");
}

public void anstrengend(String s) {
System.out.println(this.toString() + ": \"Der Pulli steht dir "
+ s + ".\"");
}
public void anstrengend() {
System.out.println(this.toString() + ": \"Du bist so duenn geworden!\"");
}
}