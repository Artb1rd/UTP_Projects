package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import org.junit.Ignore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private PreparedStatement preparedResultSet = null;
    private ResultSet resultSet = null;
    Connection connection = null;

    public UserRepository() {
        try {
            connection = DriverManager.getConnection(databaseUrl, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(UserDTO dto) {
        try {
            if (dto.getGroups() != null)
                for (GroupDTO group : dto.getGroups()) {
                    String groupQuery = "INSERT INTO USERS_GROUPS VALUES(" + group.getId() + ", " + dto.getId() + ")";
                    preparedResultSet = getConnection().prepareStatement(groupQuery);
                    resultSet = preparedResultSet.executeQuery();
                }
            String query = "INSERT INTO USERS VALUES(?,?,?)";
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.setInt(1, dto.getId());
            preparedResultSet.setString(2, dto.getLogin());
            preparedResultSet.setString(3, dto.getPassword());
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        try {
            String query = "UPDATE USERS SET " +
                    "USER_LOGIN=?," +
                    "USER_PASSWORD=?" +
                    " WHERE USER_ID = " + dto.getId();
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.setString(1, dto.getLogin());
            preparedResultSet.setString(2, dto.getPassword());
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
            beginTransaction();
        if (findById(dto.getId()) == null) {
            add(dto);
        }
        else update(dto);
        commitTransaction();
    }

    @Override
    public void delete(UserDTO dto) {
        try {
            String query = "DELETE FROM USERS WHERE " +
                    "USER_ID = " + dto.getId();
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDTO findById(int id) {
        String query = "SELECT * FROM USERS WHERE USER_ID=" + id;

        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next())
                return new UserDTO(id, resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public void beginTransaction() {
        String query = "START TRANSACTION;";
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
        } catch (SQLException ignored) {
        ignored.printStackTrace();
        }
    }

    @Override
    public void commitTransaction() {

        String query = "COMMIT;";
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public void rollbackTransaction() {

        String query = "ROLLBACK;";
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        String query = "SELECT COUNT(USER_ID) FROM USERS ";
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next())
            return resultSet.getInt(1);
        } catch (SQLException ignored) {
            ignored.printStackTrace();
            }

        return 0;
    }

    @Override
    public boolean exists(UserDTO dto) {
        String query = "SELECT USER_ID FROM USERS WHERE USER_ID = " + dto.getId();
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next())
                return true;
        }
        catch (SQLException ignored) {
        }
        return false;
    }

    @Override
    public List<UserDTO> findByName(String username) {
        String query = "SELECT * FROM USERS WHERE USER_LOGIN LIKE '" + username + "'";
        List<UserDTO> list = new ArrayList<>();
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                UserDTO user = new UserDTO(id, login, password);
                list.add(user);
        }}
            catch (SQLException ignored) {
            ignored.printStackTrace();
            }

            return list;
    }
}
