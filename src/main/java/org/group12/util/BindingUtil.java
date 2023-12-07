package org.group12.util;

import javafx.beans.WeakListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Utility class for mapping content between two observable lists.
 */
public class BindingUtil {

    /**
     * Maps the content from the source observable list to the mapped observable list using the provided mapper function.
     *
     * @param mapped the mapped observable list
     * @param source the source observable list
     * @param mapper the mapping function
     * @param <E>    the type of elements in the source list
     * @param <F>    the type of elements in the mapped list
     */
    public static <E, F> void mapContent(ObservableList<F> mapped, ObservableList<? extends E> source,
                                         Function<? super E, ? extends F> mapper) {
        map(mapped, source, mapper);
    }

    /**
     * Maps the content from the source observable list to the mapped observable list using the provided mapper function.
     * This method is used internally by the public {@link #mapContent(ObservableList, ObservableList, Function)} method.
     *
     * @param mapped the mapped observable list
     * @param source the source observable list
     * @param mapper the mapping function
     * @param <E>    the type of elements in the source list
     * @param <F>    the type of elements in the mapped list
     * @return an object representing the mapping between the two lists
     */
    private static <E, F> Object map(ObservableList<F> mapped, ObservableList<? extends E> source,
                                     Function<? super E, ? extends F> mapper) {
        final ListContentMapping<E, F> contentMapping = new ListContentMapping<E, F>(mapped, mapper);
        mapped.setAll(source.stream().map(mapper).collect(toList()));
        source.removeListener(contentMapping);
        source.addListener(contentMapping);
        return contentMapping;
    }

    /**
     * A helper class that maps the content between two lists and listens for changes in the source list.
     *
     * @param <E> the type of elements in the source list
     * @param <F> the type of elements in the mapped list
     */
    private static class ListContentMapping<E, F> implements ListChangeListener<E>, WeakListener {
        private final WeakReference<List<F>> mappedRef;
        private final Function<? super E, ? extends F> mapper;

        /**
         * Constructs a new ListContentMapping object with the provided mapped list and mapping function.
         *
         * @param mapped the mapped list
         * @param mapper the mapping function
         */
        public ListContentMapping(List<F> mapped, Function<? super E, ? extends F> mapper) {
            this.mappedRef = new WeakReference<List<F>>(mapped);
            this.mapper = mapper;
        }

        /**
         * Called when the source list has changed. Updates the mapped list accordingly.
         *
         * @param change the change event containing information about the change in the source list
         */
        @Override
        public void onChanged(Change<? extends E> change) {
            final List<F> mapped = mappedRef.get();
            if (mapped == null) {
                change.getList().removeListener(this);
            } else {
                while (change.next()) {
                    if (change.wasPermutated()) {
                        mapped.subList(change.getFrom(), change.getTo()).clear();
                        mapped.addAll(change.getFrom(), change.getList().subList(change.getFrom(), change.getTo())
                                .stream().map(mapper).collect(toList()));
                    } else {
                        if (change.wasRemoved()) {
                            mapped.subList(change.getFrom(), change.getFrom() + change.getRemovedSize()).clear();
                        }
                        if (change.wasAdded()) {
                            mapped.addAll(change.getFrom(), change.getAddedSubList()
                                    .stream().map(mapper).collect(toList()));
                        }
                    }
                }
            }
        }

        /**
         * Checks if the mapped list has been garbage collected.
         *
         * @return true if the mapped list has been garbage collected, false otherwise
         */
        @Override
        public boolean wasGarbageCollected() {
            return mappedRef.get() == null;
        }

        /**
         * Computes the hash code of the ListContentMapping object based on the mapped list.
         *
         * @return the hash code of the ListContentMapping object
         */
        @Override
        public int hashCode() {
            final List<F> list = mappedRef.get();
            return (list == null) ? 0 : list.hashCode();
        }

        /**
         * Checks if the ListContentMapping object is equal to the specified objectin terms of the mapped list.
         *
         * @param obj the object to compare
         * @return true if the ListContentMapping object is equal to the specified object, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            final List<F> mapped1 = mappedRef.get();
            if (mapped1 == null) {
                return false;
            }

            if (obj instanceof ListContentMapping) {
                final ListContentMapping<?, ?> other = (ListContentMapping<?, ?>) obj;
                final List<?> mapped2 = other.mappedRef.get();
                return mapped1 == mapped2;
            }
            return false;
        }
    }
}
