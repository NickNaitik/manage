package com.nick.product.manage.Token;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class VerificationRequest {

    private String email;
    private String supplier_id;
    private String code;
}
