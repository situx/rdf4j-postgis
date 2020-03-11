package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.aggregate;

import org.eclipse.rdf4j.query.algebra.AbstractAggregateOperator;
import org.eclipse.rdf4j.query.algebra.QueryModelVisitor;
import org.eclipse.rdf4j.query.algebra.ValueExpr;

/**
 * Aggregate function to find the average x coordinate of a set of geometries.
 *
 */
public class AvgX extends AbstractAggregateOperator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4165503381085356852L;

	public AvgX(ValueExpr arg) {
		super(arg);
	}

	public AvgX(ValueExpr arg, boolean distinct) {
		super(arg, distinct);
	}

	@Override
	public <X extends Exception> void visit(QueryModelVisitor<X> visitor) throws X {
		visitor.meet(this);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof AvgX && super.equals(other);
	}

	@Override
	public int hashCode() {
		return super.hashCode() ^ "AvgX".hashCode();
	}

	@Override
	public AvgX clone() {
		return (AvgX) super.clone();
	}
}