package nl.fontys.kwetter.interceptor.handler;

import nl.fontys.kwetter.interceptor.Logged;
import nl.fontys.kwetter.logging.SentryHandler;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

@Logged
@Interceptor
public class LoggingHandler {

    @AroundInvoke
    public Object Log(InvocationContext ctx) throws Exception {
        Method method = ctx.getMethod();
        SentryHandler.log(method.getDeclaringClass() + "." + method.getName() + "() called.");
        try {
            return ctx.proceed();
        } catch (Exception e) {
            return null;
        }
    }
}
