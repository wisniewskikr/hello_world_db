package pl.kwi.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.kwi.entities.UserEntity;
import pl.kwi.test.guice.modules.UserServiceTestModule;

import com.google.inject.Guice;

public class UserServiceTest {
	
	private UserService service;

	@Before
	public void setUp() throws Exception {
		service = Guice.createInjector(new UserServiceTestModule()).getInstance(UserService.class);
	}

	@Test
	public void testCreateUser() throws Exception {
		
		UserEntity user = new UserEntity();
		user.setName("Name1");
		
		user = service.createUser(user);
		
		assertEquals(Long.valueOf(1), user.getId());
		
	}

	@Test
	public void testReadUser() throws Exception {
		
		UserEntity user = service.readUser(1L);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals("Name1", user.getName());
		
	}

	@Test
	public void testUpdateUser() throws Exception {
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setName("Name1");
		
		user = service.updateUser(user);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals("Name1", user.getName());
		
	}

	@Test
	public void testGetUsers() throws Exception {
		
		List<UserEntity> users = service.getUsers();
		
		assertEquals(1, users.size());
		
		UserEntity user = users.get(0);
		
		assertEquals(Long.valueOf(1), user.getId());
		assertEquals("Name1", user.getName());
		
	}

}
