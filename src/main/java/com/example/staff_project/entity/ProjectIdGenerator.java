package com.example.staff_project.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String prefix = "P";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(CAST((SUBSTRING(project_id, 2)) AS DECIMAL(5, 0))) FROM project");

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1) < 1001 ? 1001 : resultSet.getInt(1) + 1;
                String generatedId = prefix + id.toString();
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
