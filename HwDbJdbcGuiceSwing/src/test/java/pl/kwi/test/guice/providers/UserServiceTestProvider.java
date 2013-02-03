package pl.kwi.test.guice.providers;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;

import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

import com.google.inject.Provider;

public class UserServiceTestProvider implements Provider<UserService> {

	@Override
	public UserService get() {
		
		UserService mock = null;
		
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setName("Name1");
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		users.add(user);
		
		try {

			mock = Mockito.mock(UserService.class);
			Mockito.when(mock.createUser(Mockito.any(UserEntity.class))).thenReturn(user);
			Mockito.when(mock.readUser(Mockito.anyLong())).thenReturn(user);
			Mockito.when(mock.updateUser(Mockito.any(UserEntity.class))).thenReturn(user);
			Mockito.when(mock.getUsers()).thenReturn(users);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mock;
	}

}
