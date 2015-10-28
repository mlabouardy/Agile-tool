package com.bordeaux.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.bordeaux.entity.Role;
import com.bordeaux.service.RoleService;

public class RoleConverter implements Converter<String, Role>{

	@Autowired
	private RoleService roleService;
	
	@Override
	public Role convert(String source) {
		return roleService.findById(source);
	}
	
}