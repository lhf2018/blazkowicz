package com.bupt.blazkowicz.configuration.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSON;
import com.bupt.ApplicationTest;
import com.bupt.blazkowicz.domain.share.bridge.SequenceInfService;
import com.bupt.blazkowicz.domain.share.bridge.enums.SequenceType;

/**
 * @author lhf2018
 * @date 2023/1/1 1:16
 */
public class MapperTest extends ApplicationTest {
    // 注入数据源对象
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceInfService sequenceInfService;

    @Test
    public void datasourceTest() throws SQLException {
        // 获取数据库连接对象
        Connection connection = dataSource.getConnection();
        // 判断连接对象是否为空
        System.out.println(connection != null);
        connection.close();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void queryAll() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from risk_control_user");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void queryAll2() {
        Long sequenceId = sequenceInfService.nextSequenceId(SequenceType.RULE_ID);
        Long newSequenceId = sequenceInfService.nextSequenceId(SequenceType.RULE_ID);
        System.out.println(newSequenceId);
        Assert.assertEquals((long)newSequenceId, sequenceId + 1);
    }

}
