package nl.fontys.kwetter.logging;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;

import javax.ejb.Singleton;

@Singleton
public class SentryHandler {
    private static SentryClient sentry;

    public static void log(String message) {
        Sentry.init();
        sentry = SentryClientFactory.sentryClient();
        sentry.sendMessage(message);
        System.out.println("Log sent to Sentry.io");
    }

}
