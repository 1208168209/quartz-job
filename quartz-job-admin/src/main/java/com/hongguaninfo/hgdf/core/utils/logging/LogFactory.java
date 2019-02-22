package com.hongguaninfo.hgdf.core.utils.logging;

import java.lang.reflect.Constructor;

public final class LogFactory {

    public static final String MARKER = "HGDF";

    private static Constructor<? extends Log> logConstructor;

    static {
        tryImplementation(new Runnable() {
            public void run() {
                useSlf4jLogging();
            }
        });
        tryImplementation(new Runnable() {
            public void run() {
                useCommonsLogging();
            }
        });
        tryImplementation(new Runnable() {
            public void run() {
                useLog4JLogging();
            }
        });
    }

    private LogFactory() {
        // disable construction
    }

    public static Log getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static Log getLog(String logger) {
        try {
            return logConstructor.newInstance(new Object[] { logger });
        } catch (Exception t) {
            try {
                throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
            } catch (LogException e) {
                // ignore
            }
        }
        return null;
    }

    public static synchronized void useCustomLogging(Class<? extends Log> clazz) {
        setImplementation(clazz);
    }

    public static synchronized void useSlf4jLogging() {
        setImplementation(com.hongguaninfo.hgdf.core.utils.logging.slf4j.Slf4jImpl.class);
    }

    public static synchronized void useCommonsLogging() {
        setImplementation(com.hongguaninfo.hgdf.core.utils.logging.commons.JakartaCommonsLoggingImpl.class);
    }

    public static synchronized void useLog4JLogging() {
        setImplementation(com.hongguaninfo.hgdf.core.utils.logging.log4j.Log4jImpl.class);
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Exception t) {
                // ignore
            }
        }
    }

    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            Constructor<? extends Log> candidate = implClass.getConstructor(new Class[] { String.class });
            Log log = candidate.newInstance(new Object[] { LogFactory.class.getName() });
            log.debug("Logging initialized using '" + implClass + "' adapter.");
            logConstructor = candidate;
        } catch (Exception t) {
            try {
                throw new LogException("Error setting Log implementation.  Cause: " + t, t);
            } catch (LogException e) {
                // e.printStackTrace();
            }
        }
    }

}
