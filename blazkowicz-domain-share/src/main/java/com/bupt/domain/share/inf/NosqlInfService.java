package com.bupt.domain.share.inf;

/**
 * @author lhf2018
 * @date 2022/10/29 15:55
 */
public interface NosqlInfService {
    /**
     * 获取数据库中的值
     * 
     * @param key
     * @return
     */
    String getValue(String key);
}
