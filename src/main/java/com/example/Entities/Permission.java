/**
 * 
 */
package com.example.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="permissions")
public class Permission
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column(length=1024)
	private String description;
	@ManyToMany(mappedBy="permissions")
	private List<Authority> authorities;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
}
