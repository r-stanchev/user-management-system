package users.model;

import lombok.Data;


/*
 * SortCriteria is the POJO with which we capture the chosen sort criteria
 * and sort the list of users
 */
@Data
public class SortCriteria {
	
	private String sortBy;
	private String order;
}
