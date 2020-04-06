package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OwnRepo {

    //Autowire Jdbc
    private JdbcTemplate jdbcTemplate;

    public OwnRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Trade> findAllTrades() {
        return jdbcTemplate.query("select * from table_trade", new BeanPropertyRowMapper<>(Trade.class));
    }

    public Optional<Trade> findById(Long id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from table_trade where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Trade.class)));
    }

    public Trade save(Trade trade) {
        // getMaxId from trade
        int maxId = Optional.ofNullable(jdbcTemplate.queryForObject("Select Max(id) from table_trade", Integer.class)).orElse(0);
        trade.setId(maxId + 1L);
        if (jdbcTemplate.update("insert into table_trade values ( ?, ?,?,?,?)", preparedStatement -> {
            preparedStatement.setLong(1, trade.getId());
            preparedStatement.setString(2, trade.getAccount());
            preparedStatement.setString(3, trade.getSecurity());
            preparedStatement.setLong(4, trade.getQuantity());
            preparedStatement.setString(5, trade.getStatus());
        }) == 0) {
            throw new RuntimeException("Something went wrong");
        }
        ;
        return trade;
    }

    public int[] saveAll(List<Trade> trades) {
        String sql = "insert into table_trade values( :id, :account, :security, :quantity,:status)";
        int maxId = Optional.ofNullable(jdbcTemplate.queryForObject("Select Max(id) from table_trade", Integer.class)).orElse(0);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(trades));

    }


}
