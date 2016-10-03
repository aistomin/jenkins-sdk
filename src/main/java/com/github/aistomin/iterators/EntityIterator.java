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
package com.github.aistomin.iterators;

import java.util.Iterator;

/**
 * Decorator for iterator of class K to use it as a decorator of type T.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @param <T> Target class.
 * @param <S> Source class.
 * @since 0.1
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

    @Override
    public final boolean hasNext() {
        return this.keys.hasNext();
    }

    @Override
    public final T next() {
        return this.transformer.transform(this.keys.next());
    }

    @Override
    public final void remove() {
        this.keys.remove();
    }
}
