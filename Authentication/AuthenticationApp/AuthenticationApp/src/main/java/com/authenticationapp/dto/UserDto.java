package com.authenticationapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 120)
	private String username;

	@NotBlank
	@Size(min = 8, max = 120)
	private String password;

	@Email
	@NotBlank
	@Size(min = 11, max = 120)
	private String email;

}
