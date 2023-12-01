package org.group12.model.IDFactory;

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
            // Use the computeIfAbsent method to get the existing instance from the map
            // or create a new one if it doesn't exist.
            return type.cast(instances.computeIfAbsent(type, key -> {
            // Use the computeIfAbsent method to get the existing instance from the map
            // or create a new one if it doesn't exist.
                try {
                    // Use reflection to create a new instance of the specified class
                    return key.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    // If there's an error creating the instance, wrap it in a RuntimeException
                    throw new RuntimeException("Error creating instance of " + key.getName(), e);
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
