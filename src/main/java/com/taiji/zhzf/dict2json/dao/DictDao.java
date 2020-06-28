package com.taiji.zhzf.dict2json.dao;

import java.util.List;
import java.util.Map;

public interface DictDao {
    List<Map<String, Object>> findAll(String tableName);
}
