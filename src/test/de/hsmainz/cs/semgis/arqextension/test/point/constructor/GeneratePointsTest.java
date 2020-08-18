package de.hsmainz.cs.semgis.arqextension.test.point.constructor;

import static org.junit.Assert.assertEquals;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.point.constructor.GeneratePoints;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.vector.WKTDatatype;
import org.junit.Test;
import org.locationtech.jts.geom.Geometry;

public class GeneratePointsTest {

	@Test
	public void testGeneratePoints() {
		GeneratePoints is3d=new GeneratePoints();
		ValueFactory valfac=SimpleValueFactory.getInstance();
		Value geo=valfac.createLiteral("LINESTRING (10 40, 40 30, 20 20, 30 10)", WKTDatatype.LiteralIRI);
		Value result=is3d.evaluate(valfac, geo,valfac.createLiteral(2));
		Value expResult=valfac.createLiteral("POINT (0 0, 1 1)",WKTDatatype.LiteralIRI);
		System.out.println(result.toString());
		System.out.println(expResult.toString());
		Geometry expGeom=WKTDatatype.INSTANCE.read(expResult.toString());
		Geometry resultGeom=WKTDatatype.INSTANCE.read(result.toString());
	    assertEquals(expGeom.getCoordinates().length, resultGeom.getCoordinates().length);
	}
	
}
