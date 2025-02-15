package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.point.constructor;

import org.eclipse.rdf4j.model.vocabulary.POSTGIS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.base.GeometricConstructor;

/**
 * Creates a point from a WKT string representation.
 */
public class PointFromText extends GeometricConstructor {

	@Override
	public Geometry construct(String input) {
        WKTReader wktreader=new WKTReader();
        Geometry geom;
		try {
			geom = wktreader.read(input);
	        if("POINT".equalsIgnoreCase(geom.getGeometryType().toUpperCase())){
	        	return geom;
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return null;
	}

	@Override
	public String getURI() {
		return POSTGIS.st_pointFromText.stringValue();
	}

}
