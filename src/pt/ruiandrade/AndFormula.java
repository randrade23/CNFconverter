package pt.ruiandrade;

public class AndFormula extends Formula  {
	Formula p; 
	Formula q;
	
	AndFormula (Formula a, Formula b) {
		p = a;
		q = b;
	}

	public void print() {
		System.out.print("(");
		p.print();
		System.out.print(" && ");
		q.print();
		System.out.print(")");
	}
	
	@Override
	public String toString() {
		return "(" + p.toString() + " && " + q.toString() + ")";
	}
	
}
