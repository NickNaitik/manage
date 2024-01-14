package com.nick.product.manage.Entity;

import com.nick.product.manage.Token.Token;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "supplier")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Supplier implements UserDetails {

    @Id
    private String supplier_uid;
    private String master_uid;
    private String supplier_name;
    private String supplier_email;
    private String supplier_password;
    private String supplier_mobile;
    private Integer supplier_customers;


    public Supplier(String master_uid, String supplier_uid, String supplier_name, String supplier_email, String supplier_password, String supplier_mobile) {
        this.master_uid = master_uid;
        this.supplier_uid = supplier_uid;
        this.supplier_name = supplier_name;
        this.supplier_email = supplier_email;
        this.supplier_password = supplier_password;
        this.supplier_mobile = supplier_mobile;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "master_uid='" + master_uid + '\'' +
                ", supplier_uid='" + supplier_uid + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_email='" + supplier_email + '\'' +
                ", supplier_mobile='" + supplier_mobile + '\'' +
                ", supplier_customers=" + supplier_customers +
                '}';
    }

    @OneToMany(mappedBy = "supplier")
    private List<Token> tokens;

    public void setToken(Token token) {
        this.tokens.add(token);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return supplier_password;
    }

    @Override
    public String getUsername() {
        return supplier_uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
