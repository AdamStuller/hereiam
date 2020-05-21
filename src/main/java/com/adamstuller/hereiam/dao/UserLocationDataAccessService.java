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
import java.sql.ResultSet;
import java.util.Arrays;
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
        logger.info(userLocation.toString());
        jdbcTemplate.update(
                "INSERT INTO user_location (token, point) VALUES (?,  ST_SetSRID(ST_MakePoint(? , ?), 4326))",
                userLocation.getToken(),
                userLocation.getPoint().getX(),
                userLocation.getPoint().getY()
        );

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
                    return new UserLocation(id, token, x, y);
                }
        );
    }

    @Override
    public int deleteUserLocation(String token) {
        return 0;
    }

    @Override
    public int updateUserLocation(String token, UserLocation userLocation) {
        jdbcTemplate.update(
                "UPDATE user_location SET point = ST_SetSRID(ST_MakePoint(? , ?), 4326) WHERE token = ?",
                userLocation.getPoint().getX(),
                userLocation.getPoint().getY(),
                token
        );
        return 0;
    }

    @Override
    public UserLocation getUserByToken(String token) {
        final String  sql = "SELECT id, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location" +
                " WHERE token = ?";

        logger.info(sql);

        UserLocation userLocation = jdbcTemplate.queryForObject(
                sql,
                new Object[]{token},
                (resultSet, i) -> {
                    Long id = resultSet.getLong("id");
                    Float x = resultSet.getFloat("x");
                    Float y = resultSet.getFloat("y");
                    String userToken = resultSet.getString("token");
                    return new UserLocation(id, userToken, x, y);
                }
        );
        return userLocation;
    }

    @Override
    public List<UserLocation> getPointsWithinRadius(UserLocation center, int radius) {

        final String sql = "SELECT id, point, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location" +
                " WHERE round(CAST(ST_Distance(point, ST_GeomFromText('POINT(" +
                center.getPoint().getX() + " " + center.getPoint().getY() +
                ")',4326)) As numeric),2) < " + radius + ";";

        logger.info(sql);

        return jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    Long id = resultSet.getLong("id");
                    Float x = resultSet.getFloat("x");
                    Float y = resultSet.getFloat("y");
                    String userToken = resultSet.getString("token");
                    return new UserLocation(id, userToken, x, y);
                }
        );

    }
}
