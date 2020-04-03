package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
     ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        NamedParameterJdbcTemplate template = context.getBean("namedParameterJdbcTemplate",NamedParameterJdbcTemplate.class);
//        Integer integer = template.queryForObject("SELECT COUNT(*) from TABLE_INSTRUCTOR", Integer.class);
//        System.out.println(integer);
////        SqlParameterSource source
//                = new BeanPropertySqlParameterSource();
//
//        )

//      int rows =   template.update("INSERT INTO table_trade values ( ?,?,?,?,? )"
//                ,new Object[]{10,"PersonalTrade","FaceBook",1000,"BUY"});
        Map<String,Object> param = new HashMap<>();
        param.put("quantity",4000);
        param.put("id",10);
        int rows = template.update("UPDATE table_trade SET quantity = :quantity where id =  :id ",param );
        System.out.println(rows);

        Trade trade = new Trade(20L,"Business","GOOG",3000L,"ACTIVE","BUY");

        String sql = "INSERT INTO table_trade VALUES (:id , :account, :security, :quantity, :status)";
        SqlParameterSource source = new BeanPropertySqlParameterSource(trade);
        System.out.println(template.update(sql,source));

        //Batch -- more than 1

        List<Trade> trades = new ArrayList<>();
        for( int i =0 ; i <1_000_000; i++) {
            trades.add(new Trade(i + 100L, "Business", "Dummmy" + i,
                    (long) Math.random() * 10000, Math.random() > 0.5 ? "BUY" : "SELL", "ACTIVE"));

        }
        SqlParameterSource [] data =   SqlParameterSourceUtils.createBatch(trades);

        template.batchUpdate(sql,data);

        int count = template.queryForObject("SELECT COUNT(0) from table_trade",new HashMap<>(), Integer.class);

        System.out.println(count);
        // all these records, i want to filter all BUY with QUANTIY> 5000

    }

}
