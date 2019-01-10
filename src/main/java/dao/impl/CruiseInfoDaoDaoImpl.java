package dao.impl;

import dao.ConnectionFactory;
import dao.CruiseInfoDao;
import dao.exception.DaoException;
import model.CruiseInfo;
import model.enums.RoomType;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseInfoDaoDaoImpl implements CruiseInfoDao {
    private static final Logger log = Logger.getLogger(CruiseInfo.class);

    @Override
    public CruiseInfo create(CruiseInfo cruiseInfo) {
        log.info("Enter create with parameters : " + cruiseInfo);
        String sql = "INSERT INTO cruise_info (ship_id, room_type, total_price, user_id) " +
                "VALUES (?, ?, ?, ?)";
        CruiseInfo createdCruiseInfo = new CruiseInfo();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, cruiseInfo.getShipId());
            log.info("Set ship id : " + cruiseInfo.getShipId());
            insertStatement.setString(2, cruiseInfo.getRoomType().toString());
            log.info("Set room type : " + cruiseInfo.getRoomType().toString());
            insertStatement.setDouble(3, cruiseInfo.getTotalPrice());
            log.info("Set price : " + cruiseInfo.getTotalPrice());
            insertStatement.setInt(4, cruiseInfo.getUserId());
            log.info("Set user id : " + cruiseInfo.getUserId());
            insertStatement.execute();
            try (ResultSet resultSet = insertStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    createdCruiseInfo.setId(resultSet.getInt("id"));
                    log.info("Found  id : " + cruiseInfo.getId());
                    createdCruiseInfo.setShipId(resultSet.getInt("ship_id"));
                    log.info("Set ship id : " + cruiseInfo.getShipId());
                    createdCruiseInfo.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                    log.info("found room type : " + cruiseInfo.getRoomType().toString());
                    createdCruiseInfo.setTotalPrice(resultSet.getFloat("total_price"));
                    log.info("found price  : " + cruiseInfo.getTotalPrice());
                    createdCruiseInfo.setUserId(resultSet.getInt("user_id"));
                    log.info("Found  id : " + cruiseInfo.getUserId());
                }
            }
        } catch (SQLException e) {
            log.error("Can`t create cruise info");
            throw new DaoException("Can`t create cruiseInfo", e);
        }
        log.info("Exit create");
        return createdCruiseInfo;
    }

    @Override
    public CruiseInfo getById(int id) {
        log.info("Enter getById with parameters : " + id);
        CruiseInfo cruiseInfo = null;
        String sql = "Select * from cruise_info WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    cruiseInfo.setId(resultSet.getInt("id"));
                    log.info("Found  id : " + cruiseInfo.getId());
                    cruiseInfo.setShipId(resultSet.getInt("ship_id"));
                    log.info("Set ship id : " + cruiseInfo.getShipId());
                    cruiseInfo.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                    log.info("found room type : " + cruiseInfo.getRoomType().toString());
                    cruiseInfo.setTotalPrice(resultSet.getFloat("total_price"));
                    log.info("found price  : " + cruiseInfo.getTotalPrice());
                    cruiseInfo.setUserId(resultSet.getInt("user_id"));
                    log.info("Found  id : " + cruiseInfo.getUserId());
                }
            }
        } catch (SQLException e) {
            log.error("Can`t get cruise info");
            throw new DaoException("Can`t get cruiseInfo", e);
        }
        log.info("Exit get with : " + cruiseInfo);
        return cruiseInfo;
    }

    @Override
    public void update(CruiseInfo cruiseInfo) {
        log.info("Enter update with parameters : " + cruiseInfo);
        String sql = "UPDATE  cruise_info set ship_id, room_type = ?, total_price = ? where id = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
            try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
                insertStatement.setInt(1, cruiseInfo.getShipId());
                log.info("Set ship id : " + cruiseInfo.getShipId());
                insertStatement.setString(2, cruiseInfo.getRoomType().toString());
                log.info("Set room type : " + cruiseInfo.getRoomType().toString());
                insertStatement.setDouble(3, cruiseInfo.getTotalPrice());
                log.info("Set price : " + cruiseInfo.getTotalPrice());
                insertStatement.setInt(4, cruiseInfo.getUserId());
                log.info("Set user id : " + cruiseInfo.getUserId());
                insertStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                log.error("Cant update");
                throw new DaoException("Can`t update cruiseInfo", e);
            }
        } catch (SQLException e) {
            log.error("Cant update");
            throw new DaoException("Can`t update cruiseInfo", e);
        }
        log.info("Exit update");
    }

    @Override
    public void deleteById(int id) {
        log.info("Enter delete method with id : " + id);
        String sql = "DELETE FROM cruise_info WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            log.info("Set id with " + id);
            statement.execute();
        } catch (SQLException e) {
            log.error("Cant delete");
            throw new DaoException("Can`t delete cruiseInfo", e);
        }
        log.info("Exit delete: ");
    }

    @Override
    public List<CruiseInfo> findAll() {
        log.info("Enter findAll()");
        CruiseInfo cruiseInfo = null;
        List<CruiseInfo> allCruisesInfo = new ArrayList<>();
        String sql = "select * from CRUISE_INFO";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql);
             ResultSet resultSet = insertStatement.executeQuery()) {
            while (resultSet.next()) {
                cruiseInfo = new CruiseInfo();
                cruiseInfo.setId(resultSet.getInt("id"));
                log.info("Found  id : " + cruiseInfo.getId());
                cruiseInfo.setShipId(resultSet.getInt("ship_id"));
                log.info("Set ship id : " + cruiseInfo.getShipId());
                cruiseInfo.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                log.info("found room type : " + cruiseInfo.getRoomType().toString());
                cruiseInfo.setTotalPrice(resultSet.getFloat("total_price"));
                log.info("found price  : " + cruiseInfo.getTotalPrice());
                cruiseInfo.setUserId(resultSet.getInt("user_id"));
                log.info("Found  id : " + cruiseInfo.getUserId());
                allCruisesInfo.add(cruiseInfo);
            }
        } catch (SQLException e) {
            log.error("Cant delete");
            throw new DaoException("Can`t get all cruiseInfo", e);
        }
        log.info("Exit findAll with parameters : " + allCruisesInfo);
        return allCruisesInfo;
    }

    public List<CruiseInfo> getAllCruiseInfoBtUserId(int id) {
        log.info("Enter findAll() with id : " + id);
        CruiseInfo cruiseInfo = null;
        List<CruiseInfo> cruiseInfoList = new ArrayList<>();
        String sql = "select * from CRUISE_INFO join USER_ROLE on CRUISE_INFO.USER_ID = USER_ROLE.id where user_role.id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, id);
            try (ResultSet resultSet = insertStatement.executeQuery()) {
                while (resultSet.next()) {
                    cruiseInfo = new CruiseInfo();
                    cruiseInfo.setId(resultSet.getInt("id"));
                    log.info("Found  id : " + cruiseInfo.getId());
                    cruiseInfo.setShipId(resultSet.getInt("ship_id"));
                    log.info("Set ship id : " + cruiseInfo.getShipId());
                    cruiseInfo.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
                    log.info("found room type : " + cruiseInfo.getRoomType().toString());
                    cruiseInfo.setTotalPrice(resultSet.getFloat("total_price"));
                    log.info("found price  : " + cruiseInfo.getTotalPrice());
                    cruiseInfo.setUserId(resultSet.getInt("user_id"));
                    log.info("Found  id : " + cruiseInfo.getUserId());
                    cruiseInfoList.add(cruiseInfo);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Can`t get all cruiseInfo by user id", e);
        }
        log.info("Exit findAll with parameters : " + cruiseInfoList);
        return cruiseInfoList;
    }
}
