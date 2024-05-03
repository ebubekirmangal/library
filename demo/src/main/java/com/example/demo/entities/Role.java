package com.example.demo.entities;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority { //Rolleri standart hale getirmek için geliştirilmiş bir interface
    //User tablosunun içerisinde SimpleGrantedAuthority kullansaydık direkt String rol verebilirdik.Birden fazla
    //rolü olması için ve kendimizin ürettiği roller olsun istediğimiz için kullandık.
    ROLE_EMPLOYEE("EMPLOYEE"),
    ROLE_VISITOR("VISITOR");
    private String value;

    Role(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    @Override
    public String getAuthority() {
        return name();
    } //Enumun name'ini döner.

}
