package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.raster.attribute;

import org.apache.sis.coverage.grid.GridCoverage;
import org.eclipse.rdf4j.model.vocabulary.POSTGIS;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.raster.base.RasterAttributeFunction;

public class NumXTiles extends RasterAttributeFunction{

	@Override
	public String getURI() {
		return POSTGIS.st_numXTiles.stringValue();
	}

	@Override
	public Double attribute(GridCoverage raster) {
		return (double) raster.render(raster.getGridGeometry().getExtent()).getNumXTiles();
	}

}
