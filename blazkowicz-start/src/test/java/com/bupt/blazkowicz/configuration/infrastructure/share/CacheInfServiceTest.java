package com.bupt.blazkowicz.configuration.infrastructure.share;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bupt.ApplicationTest;
import com.bupt.blazkowicz.infrastructure.share.inf.CacheInfService;

/**
 * @author lhf2018
 * @date 2023/4/5 21:16
 */
public class CacheInfServiceTest extends ApplicationTest {
    @Autowired
    private CacheInfService cacheInfService;

    @Test
    public void get() {
        Object age = cacheInfService.get("name");
        Assert.assertNull(age);
    }

    @Test
    public void set() {
        cacheInfService.put("age", "41", 100);
        String age = cacheInfService.get("age");
        System.out.println(age);
        Assert.assertEquals("41", age);
    }

}
