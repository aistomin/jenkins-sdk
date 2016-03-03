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

import com.github.aistomin.xml.XmlString;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeUser class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeUserTest {

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXml() throws Exception {
        final String xml = "<user><id>integration</id></user>";
        MatcherAssert.assertThat(
            new FakeUser(new XmlString(xml)).xml(), new IsEqual<String>(xml)
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
        MatcherAssert.assertThat(
            new FakeUser(username).username(), new IsEqual<String>(username)
        );
    }

    /**
     * Can read Jenkins' users XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeUser().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<user>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<id>integration</id>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</user>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read user's username.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUsername() throws Exception {
        MatcherAssert.assertThat(
            new FakeUser().username(), new IsInstanceOf(String.class)
        );
    }

    /**
     * Can read user's full name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadFullName() throws Exception {
        MatcherAssert.assertThat(
            new FakeUser().fullName(), new IsInstanceOf(String.class)
        );
    }

    /**
     * Can read user's email.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadEmail() throws Exception {
        MatcherAssert.assertThat(
            new FakeUser().email(), new IsInstanceOf(String.class)
        );
    }

    /**
     * Can read user's profile URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        MatcherAssert.assertThat(
            new FakeUser().url().startsWith("http://"),
            new IsEqual<Boolean>(true)
        );
    }
}
