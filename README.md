# ExpressionEvaluator
A general Java library for expression evaluation and Algebra.
> Currently a work in progress.
## Examples
Basic Addition
```java
Expression expr = new Expression("1+1");
expr.evaluate();
```
## Expressions with functions
```java
Expression expr = new Expression("sin(3.14/180)+1");
expr.evaluate();
```
