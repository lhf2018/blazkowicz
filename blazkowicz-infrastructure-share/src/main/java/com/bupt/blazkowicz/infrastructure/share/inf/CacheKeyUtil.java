package com.bupt.blazkowicz.infrastructure.share.inf;

/**
 * @author lhf2018
 */
public final class CacheKeyUtil {

    private static final int DEFAULT_EXPIRE_SECONDS = 3600;

    private CacheKeyUtil() {}

    public static int defaultExpireSeconds() {
        return DEFAULT_EXPIRE_SECONDS;
    }

    public static String ruleKey(Long ruleId) {
        return "rule:" + ruleId;
    }

    public static String rulesKey(String businessIdentity, String preventionType) {
        return "rules:" + businessIdentity + ":" + preventionType;
    }

    public static String disposalKey(String businessIdentity, String preventionType, Long ruleId) {
        return "disposal:" + businessIdentity + ":" + preventionType + ":" + ruleId;
    }
}
