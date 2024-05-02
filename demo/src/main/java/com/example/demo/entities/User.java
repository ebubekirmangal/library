package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "tc_num",unique = true)
    private String tcNum;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch =FetchType.EAGER)
    @JoinTable(name = "userRoles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_roles")
    private Set<UserRole> userRoles;

    @Column(name= "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email",unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;

    @OneToMany(mappedBy = "user")
    private List<Delivery> deliveries;

    @Column(name = "is_action_take")
    private Boolean isActionTake;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO: Roller
        return null;
    }

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
