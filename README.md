#CNFconverter

Convert any formula to Conjunctive Normal Form. No simplifications take place (yet).

##Reference sheet for symbols

* && : AND
* || : OR
* ^ : XOR
* - : NOT
* -> : IMPL
* <=> : EQUIV
* true: TRUE
* false: FALSE

End the formula with a semicolon.

##Examples

```
(a && b) || (c && d) || e;
CNF: ((((a || c) || e) && ((b || c) || e)) && (((a || d) || e) && ((b || d) || e)))
```

```
(a ^ b);
CNF: ((a || b) && (a || b))
```

```
(a && b) <=> (c ^ d) -> e;
CNF: ((((a || (a || b)) && (b || (a || b))) && (((((c || c) || e) || (a || b)) && (((d || c) || e) || (a || b))) && ((((c || d) || e) || (a || b)) && (((d || d) || e) || (a || b))))) && (((((a || (c || d)) && (b || (c || d))) && (((((c || c) || e) || (c || d)) && (((d || c) || e) || (c || d))) && ((((c || d) || e) || (c || d)) && (((d || d) || e) || (c || d))))) && (((a || (c || d)) && (b || (c || d))) && (((((c || c) || e) || (c || d)) && (((d || c) || e) || (c || d))) && ((((c || d) || e) || (c || d)) && (((d || d) || e) || (c || d)))))) && (((a || e) && (b || e)) && (((((c || c) || e) || e) && (((d || c) || e) || e)) && ((((c || d) || e) || e) && (((d || d) || e) || e))))))
```

##How this works

Formulas are read and parsed with the help of a CUP spec. The parser translates the expression to the adequate data structure. The only special case is when we have XOR, IMPL or EQUIV - these are automatically converted to AND/OR/NOT equivalent expressions. The expressions are then converted with the help of negation propagation and De Morgan laws. The resulting CNF expression is simplified if it contains TRUE/FALSE values (further optimizations are necessary). 

##TODO

* Simplify formulas after they are converted (removing redundant clauses and making them shorter, as you can see in the examples above)
