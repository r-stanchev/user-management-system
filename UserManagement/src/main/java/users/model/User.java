package users.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


/*
 * User is the POJO that represents the data that is persisted to the database
 */
@Data
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private Long id;
	
	
	@Pattern(regexp = "^[a-zA-Z]+(([' -][a-zA-Z ])?[a-zA-Z]*)*$", message = "A name must contain at least 1 letter."
			+ " Only spaces, apostrophes and dashes are allowed")
	@Column(name = "first_name")
	private  String firstName;
	
	
	@Pattern(regexp = "^[a-zA-Z]+(([' -][a-zA-Z ])?[a-zA-Z]*)*$", message = "A name must contain at least 1 letter."
			+ " Only spaces, apostrophes and dashes are allowed")
	@Column(name = "last_name")
	private  String lastName;
	
	
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",
						message = "Not a valid email address")
	@Column(name = "email", unique = true)
	private  String email;
	
	
	@NotNull(message = "Please select a date")
	@DateTimeFormat(pattern = "mm-dd-yyyy")
	@Past(message = "The date must be in the past")
	@Column(name = "date_of_birth")
	private  LocalDate dateOfBirth = LocalDate.of(2000,1,1); 
	
}
