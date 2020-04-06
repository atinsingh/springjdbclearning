package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {


    private JdbcTemplate template;


    public TestService(JdbcTemplate template) {
        this.template = template;
    }

    //JPA


    public void doSomeQuery() {
        List<Map<String, Object>> maps = template.queryForList("Select * from table_trade LIMIT 10 ");
        System.out.println(maps);
    }

    public void batchUpdate() {
//
//        template.batchUpdate("", 20, new ParameterizedPreparedStatementSetter<Trade>() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement, Trade o) throws SQLException {
//
//            }
//        });
    }

    /**
     *
     * Trade
     *  order table
     *  will have entry of everytrade your doing along with
     *  timestamp.
     *  you can't execute a trade without order
     *  in a order you can have more than 1 trade,
     *  buy and sell in same order
     */


}


// ORM -->  Hibernate
// JPA --> Java Persistent API
