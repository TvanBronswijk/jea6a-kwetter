package nl.fontys.kwetter.annotations.handler;

import nl.fontys.kwetter.annotations.Logged;
import nl.fontys.kwetter.logging.SentryHandler;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Logged
@Interceptor
public class LoggingHandler {

    @AroundInvoke
    public Object Log(InvocationContext context) {
        Method method = context.getMethod();
        SentryHandler.log(method.getDeclaringClass() + "." + method.getName() + "() called.");
        try {
            return context.proceed();
        } catch (Exception e) {
            return null;
        }
    }
}
