package com.nick.product.manage.Token;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRequest {

    private String supplier_Id;
    private String supplier_Password;


}
