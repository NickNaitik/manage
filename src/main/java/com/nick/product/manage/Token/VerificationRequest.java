package com.nick.product.manage.Token;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class VerificationRequest {

    private String email;
    private String supplier_id;
    private String code;
}
