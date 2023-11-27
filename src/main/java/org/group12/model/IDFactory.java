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
        protected abstract String getPrefix();

        protected abstract AtomicLong getCounter();

        protected abstract String getObjectType();


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