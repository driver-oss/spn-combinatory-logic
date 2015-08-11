package object cl {
  sealed trait Term {
    def *(that: Term) = cl.*(this, that)

    // Given a term M and variable x, M.extract(x) returns a term
    // without x, M', s.t. (M' * x) evaluates the same way as M
    def extract(x: Var): Term = this match {
      case a: Atom if a != x => K * a
      case a: Atom if a == x => I
      case l * r => S * l.extract(x) * r.extract(x)
    }

    def contains(x: Var): Boolean = ???
  }

  case class *(left: Term, right: Term) extends Term {
    override def toString = this match {
      case p * (q * r) => s"$p(${q * r})"
      case _ => s"$left$right"
    }
  }

  def eval(t: Term): Term = ???

  def bracketAbstraction(f: Term => Term): Term = {
    val x = Var("variable")
    f(x).extract(x)
  }

  def bracketAbstraction(f: (Term, Term) => Term): Term = {
    val x = Var("variable-x")
    val y = Var("variable-y")
    f(x, y).extract(y).extract(x)
  }

  sealed trait Atom extends Term
  sealed trait Const extends Atom
  case object S extends Const
  case object K extends Const
  case object I extends Const
  case object B extends Const
  case object C extends Const
  case object W extends Const
  case object Bp extends Const
  case object Cp extends Const
  case object Sp extends Const

  case class Var(name: String) extends Atom {
    override def toString = name
  }

  implicit def symbolToVar(s: Symbol) = new Var(s.name)
}
