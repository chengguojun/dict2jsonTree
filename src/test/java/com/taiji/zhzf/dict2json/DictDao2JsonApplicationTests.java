package com.taiji.zhzf.dict2json;

import com.taiji.zhzf.dict2json.service.DictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DictDao2JsonApplicationTests {

    @Autowired
    private DictService dictService;
    @Test
    void contextLoads() {

        dictService.findAll();


    }

}
