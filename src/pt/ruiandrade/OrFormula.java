package pt.ruiandrade;

public class OrFormula extends Formula {
	Formula p; 
	Formula q;
	
	OrFormula (Formula a, Formula b) {
		p = a;
		q = b;
	}
	
	public void print() {
		System.out.print("(");
		p.print();
		System.out.print(" || ");
		q.print();
		System.out.print(")");
	}
	
	@Override
	public String toString() {
		return "(" + p.toString() + " || " + q.toString() + ")";
	}
	
}
