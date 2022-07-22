package com.example.staff_project.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String prefix = "S";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(CAST((SUBSTRING(staff_id, 2)) AS DECIMAL(5, 0))) FROM staff");

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1) + 1;
                String generatedId = prefix + id.toString();
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
