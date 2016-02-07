package pt.ruiandrade;

public class Variable extends Formula {
	String varname;
	
	Variable (String s) {
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
