package com.peaceandcode.quizapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaceandcode.quizapp.constant.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "user_account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    private final static int passCodeLength = 10;

    private static String randomGameCode(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, passCodeLength).toUpperCase();
    }

    @Id
    @GeneratedValue(generator = "user_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",initialValue = 0)
    private Long id;
    @NotBlank(message = "Username can't be blank")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{4,20}$", message = "Invalid username")
    private String username;
    @NotBlank
    @JsonIgnore
    private String password;
    @NotBlank
    @Size(min = passCodeLength, max = passCodeLength)
    @Column(unique = true)
    private String gameCode = randomGameCode();
    @NotBlank
    private Role role;
    Boolean active = true;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return active ;
    }
}
