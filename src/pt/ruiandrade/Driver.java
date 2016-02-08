package pt.ruiandrade;

import pt.ruiandrade.formula.AndFormula;
import pt.ruiandrade.formula.BoolFormula;
import pt.ruiandrade.formula.Formula;
import pt.ruiandrade.formula.NotFormula;
import pt.ruiandrade.formula.OrFormula;

class Driver {

	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		//Sidenote: Xor, Impl and Equiv are handled by CUP.
		Formula f = (Formula) parser.parse().value;
		//Any expressions after this step are made of AND, OR and NOT only, and they may contain constants.
	    try {
	    	Formula cnf = convertCNF(f);
	    	System.out.print("CNF: "); cnf.print();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static Formula process (Formula f) {
		Formula cnf = f;
    	while (!cnf.toString().equals(distribute(fixNegations(cnf)).toString())) {
    		//Repeat conversion until the formula is in CNF
    		cnf = distribute(fixNegations(cnf));
    	}
    	return cnf;
	}
	
	public static Formula convertCNF (Formula f) {
		return process(f);
	}
		
	public static Formula fixNegations (Formula f) { //Move negations in (until we have none)
		if (f instanceof NotFormula) { //NOT
			if (((NotFormula) f).p instanceof NotFormula) { //NOT NOT
				return fixNegations(((NotFormula)((NotFormula) f).p).p);
			}
			else if (((NotFormula) f).p instanceof AndFormula) { //NOT AND
				return new OrFormula(fixNegations(new NotFormula(((AndFormula)((NotFormula) f).p).p)), fixNegations(new NotFormula(((AndFormula)((NotFormula) f).p).q)));
			}
			else if (((NotFormula) f).p instanceof OrFormula) { //NOT OR
				return new AndFormula(fixNegations(new NotFormula(((OrFormula)((NotFormula) f).p).p)), fixNegations(new NotFormula(((OrFormula)((NotFormula) f).p).q)));
			}
			else if (((NotFormula)f).p instanceof BoolFormula) { //NOT CONST
				return new BoolFormula(!((BoolFormula)((NotFormula)f).p).b);
			}
			else { //NOT VAR
				return fixNegations(((NotFormula)f).p);
			}
		}
		else if (f instanceof AndFormula) {
			return new AndFormula (fixNegations(((AndFormula)f).p), fixNegations(((AndFormula)f).q));
		}
		else if (f instanceof OrFormula) {
			return new OrFormula (fixNegations(((OrFormula)f).p), fixNegations(((OrFormula)f).q));
		}
		else { // VAR or CONST
			return f;
		}
	}
	
	public static Formula distribute (Formula f) { //De Morgan laws
		if (f instanceof OrFormula && ((OrFormula)f).q instanceof AndFormula) { // OR (*, AND)
			Formula left = new OrFormula(distribute(((OrFormula)f).p), distribute(((AndFormula)((OrFormula)f).q).p));
			Formula right = new OrFormula(distribute(((OrFormula)f).p), distribute(((AndFormula)((OrFormula)f).q).q));
			return new AndFormula(left, right);
		}
		else if (f instanceof OrFormula && ((OrFormula)f).p instanceof AndFormula) { // OR (AND, *)
			Formula left = new OrFormula(distribute(((AndFormula)((OrFormula)f).p).p), distribute(((OrFormula)f).q));
			Formula right = new OrFormula(distribute(((AndFormula)((OrFormula)f).p).q), distribute(((OrFormula)f).q));
			return new AndFormula(left, right);
		}
		else if (f instanceof OrFormula) {
			return new OrFormula (distribute(((OrFormula)f).p), distribute(((OrFormula)f).q));
		}
		else if (f instanceof AndFormula) {
			return new AndFormula (distribute(((AndFormula)f).p), distribute(((AndFormula)f).q));
		}
		else if (f instanceof NotFormula) {
			return new NotFormula (distribute(((NotFormula)f).p));
		}
		else { // VAR or CONST
			return f;
		}
	}
	
}