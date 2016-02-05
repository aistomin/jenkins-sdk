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
package com.github.aistomin.jenkins.fake;

import com.github.aistomin.xml.XMLString;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for FakeUser class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeUserTest {

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXML() throws Exception {
        final String xml = "<user><id>integration</id></user>";
        Assert.assertEquals(
            xml, new FakeUser(new XMLString(xml)).xml()
        );
    }

    /**
     * Can create fake instances providing only username.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithUsername() throws Exception {
        final String username = String.format(
            "username%d", new Random().nextInt(1000)
        );
        Assert.assertEquals(username, new FakeUser(username).username());
    }

    /**
     * Can read Jenkins' users XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new FakeUser().xml();
        Assert.assertTrue(xml.startsWith("<user>"));
        Assert.assertTrue(
            xml.contains("<id>integration</id>")
        );
        Assert.assertTrue(xml.endsWith("</user>"));
    }

    /**
     * Can get user's username.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUsername() throws Exception {
        final String username = new FakeUser().username();
        Assert.assertNotNull(username);
        Assert.assertTrue(username.startsWith("user"));
    }
}
