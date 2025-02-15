package org.eclipse.rdf4j.testsuites.postgis.geometry.transform;

import static org.junit.Assert.assertEquals;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.transform.Force4D;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.vector.WKTDatatype;
import org.junit.jupiter.api.Test;

public class Force4DTest {

	public static final String testLineString="LINESTRING Z (0 0 1,0 5 2,5 0 3,0 0 4)";
	
	@Test
	public void testForce4D() {
		Force4D instance=new Force4D();
		ValueFactory valfac=SimpleValueFactory.getInstance();
		Value geo=valfac.createLiteral(testLineString, WKTDatatype.LiteralIRI);
		Value result=instance.evaluate(valfac, geo);
		Value expResult=valfac.createLiteral("LINESTRING ZM (0 0 0 1,0 5 0 2,5 0 0 0,0 0 0 0)");
		assertEquals(expResult, result);
	}
	
}
