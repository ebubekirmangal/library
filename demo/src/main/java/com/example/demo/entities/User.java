package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails { // UserDetails Spring security'e ait bir entity olduğunu SecurityContext'e bildirir.
    @Id
    @Column(name = "tc_num",unique = true)
    private String tcNum;

    private String email;

    private String password;

    @Column(name= "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "user")
    private List<Delivery> deliveries;

    @Column(name = "is_action_take")
    private Boolean isActionTake;

    //Role enumunu bir tabloya bağlayıp tablo ve user tablosu arasında birebir ilişki kurulur.bir userın bir rolü olur.
    //Burada many to many ilişkisi kurduk.
    //Bir userın alabileceği rolleri bir tabloda liste şeklinde tuttuk.
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @JoinTable(name="authorities",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities; //Bir user bir role alsın istersek private Role authorities yazarız.
    @Override
    public String getUsername() {
        return email;
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
