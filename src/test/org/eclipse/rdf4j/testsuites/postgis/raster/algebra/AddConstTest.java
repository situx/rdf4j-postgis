package org.eclipse.rdf4j.testsuites.postgis.raster.algebra;

import static org.junit.Assert.assertNotEquals;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.raster.algebra.AddConst;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.raster.HexWKBRastDatatype;
import org.eclipse.rdf4j.testsuites.postgis.util.SampleRasters;
import org.junit.jupiter.api.Test;


public class AddConstTest extends SampleRasters {

	public static final String combinedRasterLiteral="";
	
	@Test
	public void testAddConst() {
        System.out.println(displayRasterSummary(wkbString1));
		ValueFactory valfac=SimpleValueFactory.getInstance();
        Value cov1 = valfac.createLiteral(wkbString1, HexWKBRastDatatype.LiteralIRI);
        Value constt = valfac.createLiteral(10);
        AddConst instance=new AddConst();
        Value expResult = valfac.createLiteral(wkbString1, HexWKBRastDatatype.LiteralIRI);
        Value result= instance.evaluate(valfac,cov1,valfac.createLiteral(0),constt);
        System.out.println(displayRasterSummary(result));
        assertNotEquals(expResult, result);
	}

}
