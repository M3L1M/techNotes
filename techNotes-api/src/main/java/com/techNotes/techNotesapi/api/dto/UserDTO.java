package com.techNotes.techNotesapi.api.dto;

import java.util.List;

import com.techNotes.techNotesapi.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Integer id;
	private String username;
	private String password;
	private String active;
	private String role;
}