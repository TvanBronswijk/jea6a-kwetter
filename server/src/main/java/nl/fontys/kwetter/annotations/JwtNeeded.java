package nl.fontys.kwetter.annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * This method or class requires a JWT in the authorization header
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target( {TYPE, METHOD})
public @interface JwtNeeded {
}
