package api.req;

import lombok.Data;

/**
 * @author lhf2018
 * @date 2022/10/29 15:43
 */
@Data
public class RiskPreventionReq {
    private String businessIdentity;
    private String preventionPointType;
    private String ruleName;
}
