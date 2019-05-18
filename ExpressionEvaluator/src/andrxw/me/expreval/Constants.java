package andrxw.me.expreval;

public enum Constants {

	E(2.718D),
	PI(3.1415926535897932384626433832795028841971D);
	
	private final double d;
	
	private Constants(double d) {
		this.d = d;
	}
	
	public double val() {
		return d;
	}
	
}
