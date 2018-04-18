package nl.fontys.kwetter.annotations;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Log whenever this method is called to Sentry.io
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target( {METHOD, TYPE})
public @interface Logged {

}
