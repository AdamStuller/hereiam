package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Proper postgres based implementation of data access storage.
 * Uses Postgis specific functions. Thus jdbcTemplate is used for executing raw sql queries.
 */
@Repository("postgres")
public class UserLocationDataAccessService implements UserLocationDao {

    private final JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(UserLocationDataAccessService.class);

    @Autowired
    public UserLocationDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Inserts new user location into postgres.
     * @param userLocation New user location to be inserted into database.
     * @return 0 if success.
     */
    @Override
    public int insertUserLocation(UserLocation userLocation) {
        final String sql = "INSERT INTO user_location (token, point) VALUES (?,  ST_SetSRID(ST_MakePoint(? , ?), 4326))";
        logger.info(sql);
        logger.info(userLocation.toString());
        jdbcTemplate.update(
                sql,
                userLocation.getToken(),
                userLocation.getPoint().getX(),
                userLocation.getPoint().getY()
        );

        return 0;
    }

    /**
     * Gets all user locationd from db.
     * @return All user locations.
     */
    @Override
    public List<UserLocation> getAllUserLocations() {
        final String  sql = "SELECT id, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location";
        logger.info(sql);
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

    /**
     * Deletes entry in user_location table, whose token matches passed token.
     * @param token Unique client side identifier.
     * @return 0 if success.
     */
    @Override
    public int deleteUserLocation(String token) {
        final String sql = "DELETE FROM user_location WHERE token = ?";
        logger.info(sql);
        logger.info(token);
        jdbcTemplate.update(
                sql,
                token
        );
        return 0;
    }

    /**
     * Updates point on given token.
     * @param token Unique client side identifier.
     * @param userLocation User location with updated coordinates
     * @return 0 if success.
     */
    @Override
    public int updateUserLocation(String token, UserLocation userLocation) {

        final  String sql = "UPDATE user_location SET point = ST_SetSRID(ST_MakePoint(? , ?), 4326) WHERE token = ?";
        logger.info(sql);
        logger.info(userLocation.toString());
        logger.info(token);
        jdbcTemplate.update(
                sql,
                userLocation.getPoint().getX(),
                userLocation.getPoint().getY(),
                token
        );
        return 0;
    }

    /**
     * Returns single user location specified by token.
     * @param token Unique client side identifier.
     * @return Searched for user location.
     */
    @Override
    public UserLocation getUserByToken(String token) {
        final String  sql = "SELECT id, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location" +
                " WHERE token = ?";

        logger.info(sql);
        logger.info(token);

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

    /**
     * Finds all user locations that are closer to center than radius.
     * @param center Point from where distances are calculated
     * @param radius Maximum distance
     * @return User locations from within radius
     */
    @Override
    public List<UserLocation> getPointsWithinRadius(UserLocation center, int radius) {

        // Uses ST_Distance - calculates distances between points.
        final String sql = "SELECT id, point, token, ST_X(point) AS x, ST_Y(point) AS y FROM user_location" +
                " WHERE round(CAST(ST_Distance(point, ST_GeomFromText('POINT(" +
                center.getPoint().getX() + " " + center.getPoint().getY() +
                ")',4326)) As numeric),2) < " + radius + ";";

        logger.info(sql);
        logger.info(center.toString());
        logger.info(Integer.toString(radius));

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
