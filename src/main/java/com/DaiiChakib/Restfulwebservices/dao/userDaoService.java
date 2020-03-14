package com.DaiiChakib.Restfulwebservices.dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.DaiiChakib.Restfulwebservices.Models.User;

@Component
public class userDaoService {
	private static ArrayList<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "chakib", new Date()));
		users.add(new User(2, "chokri", new Date()));
		users.add(new User(3, "Mohamed", new Date()));
	}
	
	public ArrayList<User> findAll()
	{
		return this.users;
	}
	
	public User save(User user)
	{	if (user.getId() == null)
			{user.setId(users.size()+1);}
		this.users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{ 	
		for(User user : users)
			if(user.getId().equals(id))
			{
				return user;
			}
		return null;
	}
	
	public User deleteById(int id){
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if(user.getId() == id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
		
	} 
}
