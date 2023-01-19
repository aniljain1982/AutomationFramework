package com.automation.api.gorest;

import lombok.Data;

@Data
public class CreateUserPojo {
	String name;
	String gender;
	String email;
	String status;
}
