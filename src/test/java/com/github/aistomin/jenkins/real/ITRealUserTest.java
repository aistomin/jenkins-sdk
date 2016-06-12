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
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Integration tests for RealUser class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealUserTest {

    /**
     * First username in test users iterator.
     */
    private static final transient String USERNAME = "\"system_builder";

    /**
     * Can read user's username.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUsername() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().users().iterator().next().username(),
            new IsEqual<>(ITRealUserTest.USERNAME)
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
            new TestJenkins().users().iterator().next().fullName(),
            new IsEqual<>(ITRealUserTest.USERNAME)
        );
    }

    /**
     * Can read user's email.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadEmail() throws Exception {
        final Iterator<User> users = new TestJenkins().users().iterator();
        users.next();
        MatcherAssert.assertThat(
            users.next().email(), new IsEqual<>("changeme@changeme.com")
        );
    }

    /**
     * Can read user's URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().users().iterator().next().url(),
            new IsEqual<>(
                "https://cisdk-istomin.rhcloud.com/user/%22system_builder"
            )
        );
    }

    /**
     * Can read user's account description.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadDescription() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().users().iterator().next().description(),
            new IsEqual<>("System user")
        );
    }

    /**
     * Can read Jenkins' user XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().users().iterator().next().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<user>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains(String.format("<id>%s</id>", ITRealUserTest.USERNAME)),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</user>"), new IsEqual<>(true)
        );
    }
}
