package pt.ruiandrade.formula;

public class NotFormula extends Formula {
	public Formula p;
	
	public NotFormula (Formula a) {
		p = a;
	}
	
	public void print() {
		System.out.print("(-");
		p.print();
		System.out.print(")");
	}
	
	@Override
	public String toString() {
		return "(-" + p.toString() + ")";
	}
	
}
