package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.DTOBase;

import java.sql.Connection;

public interface IRepository<TDTO extends DTOBase> {

    String databaseUrl = "jdbc:mysql://localhost:3306/utpdatabase";
    String username = "root";
    String password = "root";

    Connection getConnection();

    void add(TDTO dto);

    void update(TDTO dto);

    void addOrUpdate(TDTO dto);

    void delete(TDTO dto);

    TDTO findById(int id);

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    int getCount();

    boolean exists(TDTO dto);
}