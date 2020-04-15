package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.relation;

import org.eclipse.rdf4j.model.vocabulary.POSTGIS;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.geometry.base.GeometricRelationDoubleBinaryFunction;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.LiteralUtils;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.distance3d.Distance3DOp;

public class WithinDistance3D extends GeometricRelationDoubleBinaryFunction {

	@Override
	public String getURI() {
		return POSTGIS.ST_DISTANCE3D.stringValue();
	}

	@Override
	protected boolean relation(Geometry g1, Geometry g2, Double distance) {
		Geometry transformed=LiteralUtils.transform(g2, g1);
        Distance3DOp op3d=new Distance3DOp(g1, transformed);
        double distance3d=op3d.distance();
        if(distance<=distance3d) {
        	return true;
        }
        return false;	
	}

}
