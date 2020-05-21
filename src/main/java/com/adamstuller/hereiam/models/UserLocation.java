package com.adamstuller.hereiam.models;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class UserLocation {

    @Transient
    private final GeometryFactory gf=new GeometryFactory();

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, unique = true)
    private Long id;

    @JsonProperty("token")
    @NonNull
    @Column( nullable = false, unique = true)
    private String token;

    @JsonProperty("point")
    @NonNull
    // Special Postgis specific type
    @Column(columnDefinition="geometry(Point,4326)", nullable=true)
    @JsonSerialize(using = GeometrySerializer.class)
    private Point point;

    public UserLocation() {
    }

    /**
     * Constructor creating user location without id.
     * @param token Unique identification from client side
     * @param lantitute X coordination
     * @param longitute Y coordination
     */
    public UserLocation( String token, Float lantitute, Float longitute) {
        this.token = token;
        this.point = gf.createPoint(new Coordinate(lantitute,longitute));
    }

    /**
     * Constructor setting id and token directly. Point is created from x and y coordinates.
     * @param id Unique identification number of user location
     * @param token Unique identification from client side
     * @param lantitute X coordination
     * @param longitute Y coordination
     */
    public UserLocation(Long id, String token, Float lantitute, Float longitute) {
        this.id = id;
        this.token = token;
        this.point = gf.createPoint(new Coordinate(lantitute,longitute));
    }

    public String getToken() {
        return token;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "token='" + token + '\'' +
                ", latitute=" + point.getX() +
                ", longitute=" + point.getY() +
                '}';
    }
}
