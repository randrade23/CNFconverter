package pt.ruiandrade.formula;

public class BoolFormula extends Formula {
	public boolean b;
	
	public BoolFormula (boolean f) {
		b = f;
	}
	
	@Override
	public void print() {
		System.out.print(b);
	}

	@Override
	public String toString() {
		return Boolean.toString(b);
	}
}
