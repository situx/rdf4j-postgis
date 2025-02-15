package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.base;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.function.Function;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.LiteralRegistry;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.LiteralUtils;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.LiteralType;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.vector.VectorLiteral;
import org.locationtech.jts.geom.Geometry;

public abstract class GeometricRelationIntegerBinaryFunction implements Function {

	@Override
	public Value evaluate(ValueFactory valueFactory, Value... args) throws ValueExprEvaluationException {
		if (args.length != 3) {
			throw new ValueExprEvaluationException(getURI() + " requires exactly 3 arguments, got " + args.length);
		}

		LiteralType l=LiteralRegistry.getLiteral(((Literal)args[0]).getDatatype().toString());
		LiteralType l2=LiteralRegistry.getLiteral(((Literal)args[1]).getDatatype().toString());
		Integer value=Integer.valueOf(args[2].stringValue());
		if(l instanceof VectorLiteral && l2 instanceof VectorLiteral) {
			Geometry geom=((VectorLiteral)l).read(args[0].stringValue());
			Geometry geom2=((VectorLiteral)l2).read(args[1].stringValue());
			Geometry transformed=LiteralUtils.transform(geom2, geom);
			boolean result = relation(geom,transformed,value);
			return valueFactory.createLiteral(result);
		}
		throw new ValueExprEvaluationException("Arguments given are not a geometry literal");

	}

	protected abstract boolean relation(Geometry g1, Geometry g2,Integer value);

}
