package main.java.de.hsmainz.rdf4jpostgis.point.constructor;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

import main.java.de.hsmainz.rdf4jpostgis.geometry.base.GeometricConstructor;

public class PointFromWKB extends GeometricConstructor {

	@Override
	public Geometry construct(String input) {
		WKBReader wktreader=new WKBReader();
        Geometry geom;
		try {
			geom = wktreader.read(input.getBytes());
	        if("POINT".equalsIgnoreCase(geom.getGeometryType().toUpperCase())){
	        	return geom;
	        }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return null;
	}

}
