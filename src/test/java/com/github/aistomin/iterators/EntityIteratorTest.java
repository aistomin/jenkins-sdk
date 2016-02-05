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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for EntityIterator class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class EntityIteratorTest {

    /**
     * Can iterate through.
     *
     * @throws Exception If error occurred.
     */
    @Test
    public void testCanIterate() throws Exception {
        final List<String> paths = new ArrayList<String>(2);
        paths.add("/home/user1/test1.txt");
        paths.add("/home/user1/test2.txt");
        final Iterator<File> iterator = new EntityIterator<File, String>(
            paths.iterator(),
            new Transformation<File, String>() {
                public File transform(final String path) {
                    return new File(path);
                }
            }
        );
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(paths.get(0), iterator.next().getAbsolutePath());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(paths.get(1), iterator.next().getAbsolutePath());
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Can remove.
     *
     * @throws Exception If error occurred.
     */
    @Test
    public void testCanRemove() throws Exception {
        final List<String> paths = new ArrayList<String>(2);
        paths.add("/home/user1/test3.txt");
        paths.add("/home/user1/test4.txt");
        final Iterator<File> iterator = new EntityIterator<File, String>(
            paths.iterator(),
            new Transformation<File, String>() {
                public File transform(final String path) {
                    return new File(path);
                }
            }
        );
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        iterator.remove();
        Assert.assertEquals(1, paths.size());
        Assert.assertTrue(paths.get(0).endsWith("test4.txt"));
    }
}
