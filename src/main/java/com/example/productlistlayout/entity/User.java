package com.example.productlistlayout.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Size(max = 255)
    @NotBlank(message="Invalid name!")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotBlank(message="Invalid surname!")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Size(max = 11)
    @NotBlank(message="Type your PESEL number")
    @Pattern(regexp="^[0-9]{11}$", message="Please provide a valid PESEL number!")
    @Column(name = "pesel", nullable = false, length = 11)
    private String pesel;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Size(max = 1)
    @Column(name = "gender", length = 1)
    private String gender;

    @Size(max = 255)
    @Email(message = "Please enter a valid e-mail address!")
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 255)
    @NotBlank(message = "Invalid login!")
    @Column(name = "login", nullable = false)
    private String login;

    @Size(max = 255)
    @NotBlank(message = "Invalid password!")
    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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