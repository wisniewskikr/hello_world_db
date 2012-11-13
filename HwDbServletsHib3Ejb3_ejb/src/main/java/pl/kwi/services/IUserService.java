package pl.kwi.services;

import java.util.List;

import javax.ejb.Local;

import pl.kwi.entities.UserEntity;

@Local
public interface IUserService {

	public Long createUser(UserEntity user);

	public UserEntity readUser(Long id);

	public void updateUser(UserEntity user);

	public void deleteUser(UserEntity user);

	public List<UserEntity> getUserList();

}