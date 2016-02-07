package pt.ruiandrade;

public class NotFormula extends Formula {
	Formula p;
	
	NotFormula (Formula a) {
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
