package users;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;


@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	
	
	/*
	 * This is a custom formatter, which is necessary for parsing LocalDate 
	 * when it is used as query parameter
	 * 
	 * Source:https://github.com/britter/localdate-query-parameter-example/blob/master/src/main/java/com/github/britter/localdatequeryparameter/Application.java#L34-L47 
	 */
	@Bean
	  public Formatter<LocalDate> localDateFormatter() {
	    return new Formatter<LocalDate>() {
	      @Override
	      public LocalDate parse(String text, Locale locale) throws ParseException {
	        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
	      }

	      @Override
	      public String print(LocalDate object, Locale locale) {
	        return DateTimeFormatter.ISO_DATE.format(object);
	      }
	    };
	  }
}
