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
package com.github.aistomin.jenkins.real;

import com.github.aistomin.jenkins.User;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration tests for RealUsers class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealUsersTest {

    /**
     * Can read Jenkins' users XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new TestJenkins().users().xml();
        Assert.assertTrue(xml.startsWith("<people>"));
        Assert.assertTrue(
            xml.contains("<fullName>Integration Test</fullName>")
        );
        Assert.assertTrue(xml.endsWith("</people>"));
    }

    /**
     * Can iterate through Jenkins' users.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterateThroughUsers() throws Exception {
        final Iterator<User> iterator = new TestJenkins().users().iterator();
        Assert.assertEquals("\"system_builder", iterator.next().username());
        Assert.assertEquals("admin", iterator.next().username());
        Assert.assertEquals("integration", iterator.next().username());
    }
}
