package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    private TestService service;
    private OwnRepo repo;

    public DemoApplication(TestService service, OwnRepo repo) {
        this.service = service;
        this.repo = repo;
    }

    public static void main(String[] args) {
     ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

//        NamedParameterJdbcTemplate template = context.getBean("namedParameterJdbcTemplate",NamedParameterJdbcTemplate.class);
////        Integer integer = template.queryForObject("SELECT COUNT(*) from TABLE_INSTRUCTOR", Integer.class);
////        System.out.println(integer);
//////        SqlParameterSource source
////                = new BeanPropertySqlParameterSource();
////
////        )
//
////      int rows =   template.update("INSERT INTO table_trade values ( ?,?,?,?,? )"
////                ,new Object[]{10,"PersonalTrade","FaceBook",1000,"BUY"});
//        Map<String,Object> param = new HashMap<>();
//        param.put("quantity",4000);
//        param.put("id",10);
//        int rows = template.update("UPDATE table_trade SET quantity = :quantity where id =  :id ",param );
//        System.out.println(rows);
//
//        Trade trade = new Trade(20L,"Business","GOOG",3000L,"ACTIVE","BUY");
//
//        String sql = "INSERT INTO table_trade VALUES (:id , :account, :security, :quantity, :status)";
//        SqlParameterSource source = new BeanPropertySqlParameterSource(trade);
//        System.out.println(template.update(sql,source));
//
//        //Batch -- more than 1
//
////        List<Trade> trades = new ArrayList<>();
////        for( int i =0 ; i <1_000_000; i++) {
////            trades.add(new Trade(i + 100L, "Business", "Dummmy" + i,
////                    (long) Math.random() * 10000, Math.random() > 0.5 ? "BUY" : "SELL", "ACTIVE"));
////
////        }
//      //  SqlParameterSource [] data =   SqlParameterSourceUtils.createBatch(trades);
//
//       // template.batchUpdate(sql,data);
//
//        int count = template.queryForObject("SELECT COUNT(0) from table_trade",new HashMap<>(), Integer.class);
//
//        System.out.println(count);
//
//        // all these records, i want to filter all BUY with QUANTIY> 5000
//
//
//
//        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
//        Map<String, Object> map= jdbcTemplate.queryForMap("SELECT * FROM table_instructor");
//        System.out.println(map);
//
//       Trade data = jdbcTemplate.queryForObject("SELECT * FROM TABLE_TRADE WHERE ID = 10 ", (resultSet, i)->{
//            Trade tr = new Trade();
//            tr.setId(resultSet.getLong("id"));
//            tr.setAccount(resultSet.getString("Account"));
//            return tr;
//        });
//
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("Select * from table_trade LIMIT 10 ");
//
//        System.out.println(maps);

        DemoApplication application =
                context.getBean(DemoApplication.class);
        // application.service.doSomeQuery();
        application.repo.save(new Trade(1L, "Facebook", "sell", 2000L, "BUY", "TEST"));

        List<Trade> trades = Arrays.asList(
                new Trade(2L, "GOOG", "sell", 2000L, "BUY", "TEST"),
                new Trade(1L, "DOX", "sell", 2000L, "BUY", "TEST")
        );
        application.repo.saveAll(trades);

        // System.out.println(application.service.simpleJDBC());

    }

}
