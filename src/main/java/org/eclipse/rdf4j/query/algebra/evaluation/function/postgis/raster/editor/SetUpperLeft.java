package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.raster.editor;

import org.apache.sis.coverage.grid.GridCoverage;
import org.eclipse.rdf4j.model.vocabulary.POSTGIS;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.raster.base.RasterModifierFunction;

public class SetUpperLeft extends RasterModifierFunction {

	@Override
	public String getURI() {
		return POSTGIS.st_setUpperLeft.stringValue();
	}


	@Override
	public GridCoverage modify(org.apache.sis.coverage.grid.GridCoverage coverage) {
		// TODO Auto-generated method stub
		return null;
	}

}
