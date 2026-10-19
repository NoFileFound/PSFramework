package org.psframework.network;

// Imports
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PSPacket {
    /**
     * Returns the packet's id.
     * @return The packet's id.
     */
    int id();

    /**
     * Returns if the packet is disabled.
     * @return True if the packet is disabled or else False.
     */
    boolean disabled() default false;
}