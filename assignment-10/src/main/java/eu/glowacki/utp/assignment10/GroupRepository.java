package eu.glowacki.utp.assignment10;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {


    private PreparedStatement preparedResultSet = null;
    private ResultSet resultSet = null;
    Connection connection = null;

    public GroupRepository() {
        try {
            connection = DriverManager.getConnection(databaseUrl, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<GroupDTO> findByName(String name) {
        String query = "SELECT * FROM `GROUPS` WHERE GROUP_NAME LIKE '" + name + "'";
        List<GroupDTO> list = new ArrayList<>();
        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String group_name = resultSet.getString(2);
                String description = resultSet.getString(3);
                GroupDTO group = new GroupDTO(id, group_name, description);
                list.add(group);
            }}
        catch (SQLException ignored) {
            ignored.printStackTrace();
        }

        return list;
    }


    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(GroupDTO dto) {
        try {
            if (dto.getUsers() != null)
                for (UserDTO user : dto.getUsers()) {
                    String userQuery = "INSERT INTO USERS_GROUPS VALUES(" + user.getId() + ", " + dto.getId() + ")";
                    preparedResultSet = getConnection().prepareStatement(userQuery);
                    resultSet = preparedResultSet.executeQuery();
                }
            String query = "INSERT INTO `GROUPS` VALUES(?,?,?)";
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.setInt(1, dto.getId());
            preparedResultSet.setString(2, dto.getName());
            preparedResultSet.setString(3, dto.getDescription());
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GroupDTO dto) {
        try {
            String query = "UPDATE `GROUPS` SET " +
                    "GROUP_NAME=?," +
                    "GROUP_DESCRIPTION=?" +
                    " WHERE GROUP_ID = " + dto.getId();
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.setString(1, dto.getName());
            preparedResultSet.setString(2, dto.getDescription());
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        beginTransaction();
        if (findById(dto.getId()) == null) {
            add(dto);
        }
        else update(dto);
        commitTransaction();
    }


    @Override
    public void delete(GroupDTO dto) {
        try {
            String query = "DELETE FROM `GROUPS` WHERE " +
                    "GROUP_ID = " + dto.getId();
            preparedResultSet = getConnection().prepareStatement(query);
            preparedResultSet.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GroupDTO findById(int id) {
        String query = "SELECT * FROM `GROUPS` WHERE GROUP_ID=" + id;

        try {
            preparedResultSet = getConnection().prepareStatement(query);
            resultSet = preparedResultSet.executeQuery();
            while (resultSet.next())
                return new GroupDTO(id, resultSet.getString(2), resultSet.getString(3));
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
        String query = "SELECT COUNT(GROUP_ID) FROM `GROUPS` ";
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
    public boolean exists(GroupDTO dto) {
        String query = "SELECT GROUP_ID FROM `GROUPS` WHERE GROUP_ID = " + dto.getId();
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
}
