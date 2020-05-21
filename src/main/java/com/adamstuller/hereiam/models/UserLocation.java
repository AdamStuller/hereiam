package com.adamstuller.hereiam.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Point;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class UserLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NonNull
    private Long id;

    @JsonProperty("token")
    @NonNull
    private String token;

    @JsonProperty("point")
    @NonNull
    @Column(columnDefinition="geometry(Point,4326)", nullable=true)
    private Point point;

    public UserLocation() {
    }

    public UserLocation(Long id, String token, Point point) {
        this.id = id;
        this.token = token;
        this.point = point;
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
