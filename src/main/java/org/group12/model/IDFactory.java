package org.group12.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
    /**
    * Represents an abstract factory for generating IDs.
    * This class provides a template for creating ID factories, with some common functionality defined,
    * and some methods left abstract for subclasses to implement.
    **/
public abstract class IDFactory {
        private static final Map<Class<? extends IDFactory>, IDFactory> instances = new HashMap<>();
        protected IDFactory() {}
        protected abstract String getPrefix();

        protected abstract AtomicLong getCounter();

        protected abstract String getObjectType();

        /**
         * Returns the single instance of the specified IDFactory subclass. If the instance is null, it creates a new instance.
         *
         * @param type the Class object representing the IDFactory subclass
         * @return the single instance of the specified IDFactory subclass
         * @throws RuntimeException if an error occurs while creating the new instance
         */
        public static synchronized <T extends IDFactory> T getInstance(Class<T> type) {
            return type.cast(instances.computeIfAbsent(type, key -> {
                try {
                    return key.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));
        }


        /**
         * Generates a new ID by concatenating the prefix and the next number from the counter.
         *
         * @return the generated ID
         */
        public String generateID() {
            return getPrefix() + getCounter().getAndIncrement();
        }

        /**
         * Identifies the type of object based on the prefix.
         *
         * @return the type of object, or "Unknown" if the prefix is not recognized
         */
        public String identifyObjectType() {
            return getObjectType();

        }

    }