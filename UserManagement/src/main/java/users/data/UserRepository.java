package users.data;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import users.model.User;


/*
 * UserRepository is the interface with which we are able to execute DML-type queries
 */
public interface UserRepository extends JpaRepository<User, Long>{

	public boolean existsByEmail(String email);
	
	@Transactional
	public void deleteByEmail(String email);

	public User findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.firstName=?1, u.lastName=?2 ,u.email=?3, u.dateOfBirth=?4 WHERE u.id=?5")
	public void updateUser(String firstName, String lastName, String email, LocalDate dateOfBirth, Long id);
}
