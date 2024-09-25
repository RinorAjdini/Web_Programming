package mk.ukim.finki.wp.june2022.g1.config;

import org.springframework.context.annotation.Configuration;
/**
 * This class is used to configure user login on path '/login' and logout on path '/logout'.
 * The only public page in the application should be '/'.
 * All other pages should be visible only for the authenticated users.
 * Furthermore, in the "list.html" template, the 'Edit', 'Delete', 'Add' buttons should only be
 * visible for a user with role 'ROLE_SYSADMIN'.
 * <p>
 * For login the users from the database should be used.
 */
@Configuration
public class SecurityConfig {


  
}
