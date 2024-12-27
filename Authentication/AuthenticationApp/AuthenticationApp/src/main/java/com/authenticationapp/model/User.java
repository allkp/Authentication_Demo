package com.authenticationapp.model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})

public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5340848913178280329L;


	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(min = 3, max = 120)
	@Column(nullable = false)
    private String username;
	
	@NotBlank
	@Size(min = 8, max = 120)
	@Column(nullable = false)
    private String password;
	
	@Email
	@NotBlank
	@Size(min = 11, max = 120)
	@Column(nullable = false, unique = true)
	private String email;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;
	
	private boolean enabled = true;
	private boolean accountExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	
	public enum Role{
		ROLE_USER,
		ROLE_ADMIN,
		ROLE_MODERATOR
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
