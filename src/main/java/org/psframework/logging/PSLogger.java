package org.psframework.logging;

// Imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

public final class PSLogger {
    private static final StackWalker WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    private final Logger logger;

    /**
     * Creates a new instance of the PS logger.
     */
    public PSLogger() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        String className = WALKER.walk(frames -> frames.map(StackWalker.StackFrame::getDeclaringClass).filter(type -> type != PSLogger.class).findFirst().map(Class::getName).orElse("Application"));
        this.logger = LoggerFactory.getLogger(className);
    }

    /**
     * Sends an info message.
     * @param message The message to send.
     * @param args The message's arguments to send.
     */
    public void info(String message, Object... args) {
        this.logger.info(message, args);
    }

    /**
     * Sends a warn message.
     * @param message The message to send.
     * @param args The message's arguments to send.
     */
    public void warn(String message, Object... args) {
        this.logger.warn(message, args);
    }

    /**
     * Sends an error message.
     * @param message The message to send.
     * @param args The message's arguments to send.
     */
    public void error(String message, Object... args) {
        this.logger.error(message, args);
    }

    /**
     * Sends a debug message.
     * @param message The message to send.
     * @param args The message's arguments to send.
     */
    public void debug(String message, Object... args) {
        this.logger.debug(message, args);
    }

    /**
     * Sends a trace message.
     * @param message The message to send.
     * @param args The message's arguments to send.
     */
    public void trace(String message, Object... args) {
        this.logger.trace(message, args);
    }
}