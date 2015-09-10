package com.callfire.api.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;

/**
 * Wrapper around commons-logging with slf4j message format
 */
public class Logger implements Log {

    private Log log;

    public Logger(Class clazz) {
        log = LogFactory.getLog(clazz);
    }

    @Override
    public void debug(Object message) {
        if (isDebugEnabled()) {
            log.debug(message);
        }
    }

    @Override
    public void debug(Object message, Throwable t) {
        if (isDebugEnabled()) {
            log.debug(message, t);
        }
    }

    public void debug(String message, Object... params) {
        if (isDebugEnabled()) {
            message = formatMessage(message, params);
            log.debug(message);
        }
    }

    @Override
    public void error(Object message) {
        if (isErrorEnabled()) {
            log.error(message);
        }
    }

    @Override
    public void error(Object message, Throwable t) {
        if (isErrorEnabled()) {
            log.error(message, t);
        }
    }

    public void error(String message, Object... params) {
        if (isErrorEnabled()) {
            message = formatMessage(message, params);
            log.error(message);
        }
    }

    @Override
    public void fatal(Object message) {
        if (isFatalEnabled()) {
            log.fatal(message);
        }
    }

    @Override
    public void fatal(Object message, Throwable t) {
        if (isFatalEnabled()) {
            log.fatal(message, t);
        }
    }

    public void fatal(String message, Object... params) {
        if (isFatalEnabled()) {
            message = formatMessage(message, params);
            log.fatal(message);
        }
    }

    @Override
    public void info(Object message) {
        if (isInfoEnabled()) {
            log.info(message);
        }
    }

    @Override
    public void info(Object message, Throwable t) {
        if (isInfoEnabled()) {
            log.info(message, t);
        }
    }

    public void info(String message, Object... params) {
        if (isInfoEnabled()) {
            message = formatMessage(message, params);
            log.info(message);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return log.isFatalEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public void trace(Object message) {
        if (isTraceEnabled()) {
            log.trace(message);
        }
    }

    @Override
    public void trace(Object message, Throwable t) {
        if (isTraceEnabled()) {
            log.trace(message, t);
        }
    }

    public void trace(String message, Object... params) {
        if (isTraceEnabled()) {
            message = formatMessage(message, params);
            log.trace(message);
        }
    }

    @Override
    public void warn(Object message) {
        if (isWarnEnabled()) {
            log.warn(message);
        }
    }

    @Override
    public void warn(Object message, Throwable t) {
        if (isWarnEnabled()) {
            log.warn(message, t);
        }
    }

    public void warn(String message, Object... params) {
        if (isWarnEnabled()) {
            message = formatMessage(message, params);
            log.warn(message);
        }
    }

    private String formatMessage(String message, Object[] params) {
        for (Object param : params) {
            message = message.replaceFirst(PLACEHOLDER, Objects.toString(param));
        }
        return message;
    }
}
