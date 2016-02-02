/**
 * Copyright (c) 2016, Istomin Andrei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rising.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Decorator for iterator of class K to use it as a decorator of type T.
 *
 * @param <T> Target class.
 * @param <S> Source class.
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class EntityIterator<T, S> implements Iterator<T> {

    /**
     * Source iterator.
     */
    private final transient Iterator<S> keys;

    /**
     * Transformation object.
     */
    private final transient Transformation<T, S> transformer;

    /**
     * Ctor.
     * @param iterator Source iterator.
     * @param transformation Transformation object.
     */
    public EntityIterator(
        final Iterator<S> iterator, final Transformation<T, S> transformation
    ) {
        this.keys = iterator;
        this.transformer = transformation;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return If the iteration has more elements
     */
    public final boolean hasNext() {
        return this.keys.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return The next element in the iteration
     * @throws NoSuchElementException If the iteration has no more elements
     */
    public final T next() {
        return this.transformer.transform(this.keys.next());
    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws UnsupportedOperationException If the {@code remove}
     *  operation is not supported by this iterator
     * @throws IllegalStateException If the {@code next} method has not
     *  yet been called, or the {@code remove} method has already
     *  been called after the last call to the {@code next} method
     */
    public final void remove() {
        this.keys.remove();
    }
}
