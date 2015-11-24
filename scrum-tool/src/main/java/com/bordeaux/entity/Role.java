package com.bordeaux.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.bordeaux.entity.user.User;

@Entity
public class Role {

	public enum RoleType {
		
		PRODUCT("ROLE_PRODUCT_OWNER"), MASTER("ROLE_SCRUM_MASTER"), TEAM("ROLE_SCRUM_TEAM");
		
		private static Role productOwner;
		private static Role scrumMaster;
		private static Role scrumTeam;
		
		private final String text;

		private RoleType(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
		
		// pour avoir la meme r�f�rence qui va etre utilis� par tous les users 
		public Role getRole(){
			if (this.text.equals(RoleType.PRODUCT.text)){
				if (productOwner == null) productOwner = new Role(RoleType.PRODUCT);
				return productOwner;
			}
			else if (this.text.equals(RoleType.MASTER.text)){
				if (scrumMaster == null) scrumMaster = new Role(RoleType.MASTER);
				return scrumMaster;
			}
			else if (this.text.equals(RoleType.TEAM.text)){
				if (scrumTeam == null) scrumTeam = new Role(RoleType.TEAM);
				return scrumTeam;
			}
			return null;
		}
	}
	
	@Id @GeneratedValue
	private int id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(targetEntity=User.class,mappedBy="role")
	private List<User> users;
	
	public Role() {}
	
	public Role(RoleType roleType) {
		this.name = roleType.toString();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
