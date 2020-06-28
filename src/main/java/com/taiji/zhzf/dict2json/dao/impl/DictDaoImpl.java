package com.taiji.zhzf.dict2json.dao.impl;

import com.taiji.zhzf.dict2json.dao.DictDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DictDaoImpl implements DictDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findAll(String tableName) {
        String sql = "select * from "+tableName;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
