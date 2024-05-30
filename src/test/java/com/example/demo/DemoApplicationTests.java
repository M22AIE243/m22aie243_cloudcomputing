package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
/**
package com.example.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyListHandler extends BaseTypeHandler<List<Long>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        OracleConnection oracleConnection = ps.getConnection().unwrap(OracleConnection.class);
        ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("MY_LIST", oracleConnection);

        Long[] arrayData = parameter.toArray(new Long[0]);
        ARRAY oracleArray = new ARRAY(descriptor, oracleConnection, arrayData);

        ps.setArray(i, oracleArray);
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }

    private List<Long> toList(java.sql.Array sqlArray) throws SQLException {
        if (sqlArray == null) {
            return null;
        }
        Long[] array = (Long[]) sqlArray.getArray();
        List<Long> list = new ArrayList<>(array.length);
        for (Long item : array) {
            list.add(item);
        }
        return list;
    }
}
package com.example.config;

import com.example.typehandler.MyListHandler;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.getTypeHandlerRegistry().register(MyListHandler.class);
            }
        };
    }
}
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.mapper.MyMapper">

    <select id="callMyStoredProcedure" statementType="CALLABLE">
        {call my_stor_proc(
            #{id, mode=IN, jdbcType=NUMERIC},
            #{bigintParam, mode=IN, jdbcType=NUMERIC},
            #{blobParam, mode=IN, jdbcType=BLOB},
            #{list, mode=IN, typeHandler=com.example.typehandler.MyListHandler},
            #{outCursor, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet}
        )}
    </select>

</mapper>
package com.example.service;

import com.example.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private MyMapper myMapper;

    public void executeStoredProcedure(Long id, Long bigintParam, byte[] blobParam, List<Long> list) {
        ResultSet resultSet = null;
        try {
            myMapper.callMyStoredProcedure(id, bigintParam, blobParam, list, resultSet);
            // Process the ResultSet as needed
            if (resultSet != null) {
                while (resultSet.next()) {
                    // Handle the result set data here
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

**/
