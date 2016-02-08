package pt.ruiandrade.formula;

public class Variable extends Formula {
	public String varname;
	
	public Variable (String s) {
		varname = new String(s);
	}

	public void print() {
		System.out.print(varname);
	}
	
	@Override
	public String toString() {
		return varname;
	}
	
}
