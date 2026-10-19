package org.psframework.logging;

// Imports
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.fusesource.jansi.Ansi;

public class LoggerEncoder extends PatternLayoutEncoder {
    /**
     * The main function to colorize the logging event.
     * @param event The logging event.
     * @return The formatted message.
     */
    @Override
    public byte[] encode(ILoggingEvent event) {
        return colorize(event).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Formats the logging message.
     * @param event The event retrieved from the logger.
     * @return The formatted message.
     */
    private String colorize(ILoggingEvent event) {
        String timestamp = Ansi.ansi().fg(Ansi.Color.WHITE).a("[" + DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("Asia/Tokyo")).format(Instant.ofEpochMilli(event.getTimeStamp())) + "]").reset().toString();
        String levelColor = switch (event.getLevel().toInt()) {
            case Level.INFO_INT -> Ansi.ansi().fg(Ansi.Color.GREEN).toString();
            case Level.WARN_INT -> Ansi.ansi().fg(Ansi.Color.YELLOW).toString();
            case Level.ERROR_INT -> Ansi.ansi().fg(Ansi.Color.RED).toString();
            case Level.DEBUG_INT -> Ansi.ansi().fg(Ansi.Color.MAGENTA).toString();
            case Level.TRACE_INT -> Ansi.ansi().fg(Ansi.Color.BLACK).toString();
            default -> Ansi.ansi().fg(Ansi.Color.DEFAULT).toString();
        };

        String level = levelColor + "[" + event.getLevel() + "]" + Ansi.ansi().reset();
        String msg = Ansi.ansi().fg(Ansi.Color.DEFAULT).a(" " + event.getFormattedMessage()).reset().toString();
        return timestamp + level + msg + System.lineSeparator();
    }
}