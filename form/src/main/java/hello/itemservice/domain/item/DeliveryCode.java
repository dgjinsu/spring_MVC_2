package hello.itemservice.domain.item;

import lombok.Data;

/**
 * FAST:빠른배송
 * NORMAL:일반배송
 * SLOW:느린배송
 */

@Data
public class DeliveryCode {
    private String code;
    private String displayName;

    public DeliveryCode(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
