package com.gestion_biblio.gestion_biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name="Users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Username")
    private String username;
    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Adresse")
    private String adresse;

    @Column(name = "Numéro Tel")
    private Integer numéroTel;

    @Column(name = "Library Card")
    private String LibraryCard;


    @Column(name = "Role")
    private Role role;

    @JsonBackReference

    @OneToMany(mappedBy = "user")
    private List<Emprunt> emprunts;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getLibraryCard() {
        return LibraryCard;
    }

    public void setLibraryCard(String libraryCard) {
        LibraryCard = libraryCard;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getNuméroTel() {
        return numéroTel;
    }

    public void setNuméroTel(Integer numéroTel) {
        this.numéroTel = numéroTel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
