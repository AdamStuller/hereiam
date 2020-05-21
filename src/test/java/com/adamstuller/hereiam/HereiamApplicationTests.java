package com.adamstuller.hereiam;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class HereiamApplicationTests {

	@Test
	public void shouldConvertWktToGeometry() throws ParseException {
		WKTReader reader = new WKTReader();
		Geometry geometry = reader.read("POINT (2 5)");

		assertEquals("Point", geometry.getGeometryType());
		assertTrue(geometry instanceof Point);
	}
}
