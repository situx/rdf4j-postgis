/*
 * Copyright 2018 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.literals.vector;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.POSTGIS;
import org.eclipse.rdf4j.query.algebra.evaluation.function.postgis.util.LiteralUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import ch.hsr.geohash.GeoHash;

/**
 * WKTDatatype class allows the URI "geo:wktLiteral" to be used as a datatype
 * and it will parse that datatype to a JTS Geometry.
 *
 * Req 10 All RDFS Literals of type geo:wktLiteral shall consist of an optional
 * URI identifying the coordinate reference system followed by Simple Features
 * Well Known Text (WKT) describing a geometric value. Valid geo:wktLiterals are
 * formed by concatenating a valid, absolute URI as defined in [RFC 2396], one
 * or more spaces (Unicode U+0020 character) as a separator, and a WKT string as
 * defined in Simple Features [ISO 19125-1].
 *
 * Req 11 The URI {@code <http://www.opengis.net/def/crs/OGC/1.3/CRS84>} shall
 * be assumed as the spatial reference system for geo:wktLiterals that do not *
 * specify an explicit spatial reference system URI.
 */
public class GeoHashDatatype extends VectorLiteral {

    /**
     * The default WKT type URI.
     */
    public static final String URI = POSTGIS.NAMESPACE+POSTGIS.GeoHash;

    /**
     * A static instance of WKTDatatype.
     */
    public static final GeoHashDatatype INSTANCE = new GeoHashDatatype();
    
	public static final IRI LiteralIRI=SimpleValueFactory.getInstance().createIRI(POSTGIS.NAMESPACE+POSTGIS.GeoHash);

    /**
     * This method Un-parses the JTS Geometry to the WKT literal
     *
     * @param geometry - the JTS Geometry to be un-parsed
     * @return WKT - the returned WKT Literal.
     * <br> Notice that the Spatial Reference System is not specified in
     * returned WKT literal.
     *
     */
    @Override
    public String unparse(Geometry geom1) {
    	if(geom1.getGeometryType().equalsIgnoreCase("Point")) {
    		return GeoHash.geoHashStringWithCharacterPrecision(geom1.getCoordinate().getY(), geom1.getCoordinate().getX(), 32);
    	}else {
    		return GeoHash.geoHashStringWithCharacterPrecision(geom1.getCentroid().getCoordinate().getY(), geom1.getCentroid().getCoordinate().getX(), 32);
    	}
    }

    @Override
    public Geometry read(String geometryLiteral) {
    	GeoHash hash=GeoHash.fromGeohashString(geometryLiteral);
    	Coordinate coord=new Coordinate(hash.getOriginatingPoint().getLatitude(), hash.getOriginatingPoint().getLongitude());
    	List<Coordinate> coords=new LinkedList<Coordinate>();
    	coords.add(coord);
    	return LiteralUtils.createGeometry(coords, "Point", 4326);				
    }


    @Override
    public String toString() {
        return "GeoHashDatatype{" + URI + '}';
    }

}