package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.api.UserLocationController;
import com.adamstuller.hereiam.models.UserLocation;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository("postgres")
public class UserLocationDataAccessService implements UserLocationDao {

    GeometryFactory gf=new GeometryFactory();
    private final JdbcTemplate jdbcTemplate;
    private final WKTReader reader = new WKTReader();
    Logger logger = LoggerFactory.getLogger(UserLocationDataAccessService.class);

    @Autowired
    public UserLocationDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertUserLocation(UserLocation userLocation) {
        return 0;
    }

    @Override
    public List<UserLocation> getAllUserLocations() {
        final String  sql = "SELECT id, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location";
        return jdbcTemplate.query(sql, (resultSet, i) ->
                {

                    Long id = resultSet.getLong("id");
                    Float x = resultSet.getFloat("x");
                    Float y = resultSet.getFloat("y");
                    String token = resultSet.getString("token");
                    Point point = gf.createPoint(new Coordinate(x, y));
                    UserLocation userLocation = new UserLocation(id, token, point);
                    return new UserLocation(id, token, point);
                }
        );
    }

    @Override
    public int deleteUserLocation(String token) {
        return 0;
    }

    @Override
    public int updateUserLocation(String token, UserLocation userLocation) {
        return 0;
    }

    @Override
    public UserLocation getUserByToken(String token) {
        return null;
    }

    @Override
    public List<UserLocation> getPointsWithinRadius(UserLocation center, int radius) {
        return null;
    }
}
