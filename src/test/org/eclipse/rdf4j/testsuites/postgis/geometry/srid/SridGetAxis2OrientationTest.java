package org.eclipse.rdf4j.testsuites.postgis.geometry.srid;

import static org.junit.Assert.assertEquals;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.srid.SridGetAxis2Orientation;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.vector.WKTDatatype;
import org.junit.Test;

public class SridGetAxis2OrientationTest {

	public static final String testPoint="<http://www.opengis.net/def/crs/EPSG/0/4326> POINT(1 2 3)";
	
	@Test
	public void testSridAxis2Orientation() {
		SridGetAxis2Orientation is3d=new SridGetAxis2Orientation();
		ValueFactory valfac=SimpleValueFactory.getInstance();
		Value geo=valfac.createLiteral(testPoint,WKTDatatype.LiteralIRI);
		Value result=is3d.evaluate(valfac, geo);
		Value expResult=valfac.createLiteral("X");
	    assertEquals(expResult, result);
	}
	
}
