## Cheat Sheet

#### Syntax
    <term>        ::= <atom> | <combination>
    <atom>        ::= <constant> | <variable>
    <combination> ::= (<term><term>)

eg. constants: **I, K, S,** etc.

eg. variables: _x, y, z_

eg. combination: **I**_x_, _yz_, ((_yz_)(**I**_x_))
    
Given distinct terms `p, q, r, s`, the following properties hold:
 - non-commutative: `pq != qp`
 - non-associative: `p(qr) != (pq)r`
 - left-associative: `pqrs == (((pq)r)s)`

Typically, we omit parens where possible:
((_yz_)(**I**_x_)) as above is simply _yz_(**I**_x_).


Check out the parse tree for this term: [S(BBS)(KK)SI](http://mshang.ca/syntree/?i=%5B*%20%5B*%20%5B*%20%5B*%20%5BS%5D%20%5B*%20%5B*%20%5BB%5D%20%5BB%5D%5D%20%5BS%5D%5D%5D%20%5B*%20%5BK%5D%20%5BK%5D%5D%5D%20%5BS%5D%5D%20%5BI%5D%5D%0A%0A)

#### Reduction Rules
The combinators have the follwing reduction properties. Given arbitrary terms, `p, q, r, s`:

```
Ip     -> p
Kpq    -> p
Bpqr   -> p(qr)
Cpqr   -> prq
Spqr   -> pr(qr)
Wpq    -> pqq
B'pqrs -> pq(rs)
C'pqrs -> p(qs)r
S'pqrs -> p(qs)(rs)
```

If a term begins with one of these patterns the head can be reduced to form a new term. e.g:

```Bpqrstuv -> p(qr)stuv```

The term `S(KS)Kpqr` reduces as follows:

```
S(KS)Kpqr
KSp(Kp)qr
S(Kp)qr
S(Kp)qr
Kpr(qr)
p(qr)
```

#### Running:

As in a typical project: `sbt test` and `sbt compile` will test and compile your code.
With utest, you can run `test-only -- Tests --trace=false` in sbt to supress the stack trace.
