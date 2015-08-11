import utest._
import utest.ExecutionContext
import cl._

object Tests extends TestSuite {
  val tests = TestSuite {
    val p: Var = 'p
    val q: Var = 'q
    val r: Var = 'r
    val s: Var = 's

    'usage {
      val pq = p * q
      val pqr = p * q * r
      val pqrs = p * q * r * s
      val parens = p * (q * r) * s
      val nested = p * (parens) * s
      val nestedLeft = (((p * q) * r) * s)
      val nestedRight = (p * (q * (r * s)))

      'toString {
        assert(
          pq.toString == "pq",
          pqr.toString == "pqr",
          pqrs.toString == "pqrs",
          parens.toString == "p(qr)s",
          nested.toString == "p(p(qr)s)s",
          nestedLeft.toString == "pqrs",
          nestedRight.toString == "p(q(rs))"
        )
      }

      'equality {
        "non-commutative"-{
          assert(
            p * q == p * q,
            p * q != q * p
          )
        }

        "non-associative"-{
          assert(p * (q*r) != (p*q) * r)
        }

        "left-associative"-{
          assert(
            p * q * r * s == (((p*q) * r) * s),
            p * q * r * s != (p * (q * (r*s)))
          )
        }
      }

      "contains"-{
        assert(
          pq.contains(p),
          pq.contains(q),
          !pq.contains(r),
          nestedRight.contains(s)
        )
      }
    }

    "reduction"-{
      /*
      "Evaluation is complete when..."-{
        "...the term begins with a var"-{
          val vars = s*s*s*s*s*s
          assert(
            eval(s) == s,
            eval(s*s) == s*s,
            eval(vars) == (vars),
            eval(s * K * p * q) == (s * K * p * q)
          )
        }

        "...the leading combinator has too few arguments for reduction"-{
          assert(
            eval(K) == K,
            eval(K * p) == K * p,
            eval(K * p * p) == p
          )
        }
      }

      "basic combinators"-{
        assert(
          eval(I * p) == p,
          eval(K * p * q) == p,
          eval(S * p * q * r) == p * r * (q * r)
        )
      }

      "head reduction"-{
        // NOTE: Implementing head reduction separately is optional,
        // but could be a helpful first step.
        assert(
          //evalHead(K * p * q * r * s) == p * r * s,
          //evalHead(K * p * q * K * p * q) == p * K * p * q,
          //evalHead(K * I * p * q) == I * q
        )
      }

      "step by step reduction"-{
        /*assert(
          evalHead(S * (K * S) * K * p * q * r) == (S * (K * S) * K * p * q * r),
          evalHead(K * S * p * (K * p) * q * r) == (S * (K * p) * q * r),
          evalHead(S * (K * p) * q * r)         == (K * p * r * (q * r)),
          evalHead(K * p * r * (q * r))         == (p * (q * r))
        )*/
      }

      "complete reduction"-{
        assert(
          eval(S * (K * S) * K * p * q * r) == p * (q * r),
          eval(Sp * K * I * I * s) == s,
          eval(B * (B * S) * B * K * I * I * s) == s,
          eval(S*(K*S)*K * (S*(K*S)*K * S) * (S*(K*S)*K) * K * (S*K*K) * I * s) == s
        )
      }
       */
    }

    "bracket abstraction"-{
      /*
      val TRUE = K
      val FALSE = K*I

      def evalBool(bool: Term): Boolean = {
        val p: Var = 'P
        val q: Var = 'Q
        // if bool then p else q
        eval(bool * p * q) match {
          case x if x == p => true
          case x if x == q => false
        }
      }

      "Booleans evaluating properly"-{
        assert(
          evalBool(TRUE),
          !evalBool(FALSE)
        )
      }

      def not = (p: Term) => p * FALSE * TRUE
      def and = (p: Term, q: Term) => p * q * FALSE
      def or = (p: Term, q: Term) => p * TRUE * q

      "basic boolean functions"-{
        assert(
          evalBool(not(FALSE)),
          !evalBool(not(TRUE)),

          evalBool(and(TRUE, TRUE)),
          !evalBool(and(TRUE, FALSE)),
          !evalBool(and(FALSE, TRUE)),
          !evalBool(and(FALSE, FALSE)),

          evalBool(or(TRUE, TRUE)),
          evalBool(or(TRUE, FALSE)),
          evalBool(or(FALSE, TRUE)),
          !evalBool(or(FALSE, FALSE))
        )
      }

      "variable extraction"-{
        val x: Var = 'x
        val term = not(x).extract(x)

        assert(
          not(x).contains(x),
          !term.contains(x),
          evalBool(term * TRUE) == evalBool(not(TRUE)),
          evalBool(term * FALSE) == evalBool(not(FALSE))
        )
      }

      "boolean functions abstracted"-{
        val NOT: Term = bracketAbstraction(not)
        val AND: Term = bracketAbstraction(and)
        val OR: Term = bracketAbstraction(or)

        assert(
          evalBool(NOT * FALSE),
          !evalBool(NOT * TRUE),

          evalBool(AND * TRUE * TRUE),
          !evalBool(AND * TRUE * FALSE),
          !evalBool(AND * FALSE * TRUE),
          !evalBool(AND * FALSE * FALSE),

          evalBool(OR * TRUE * TRUE),
          evalBool(OR * TRUE * FALSE),
          evalBool(OR * FALSE * TRUE),
          !evalBool(OR * FALSE * FALSE)
        )
      }
      */
    }
  }
}
