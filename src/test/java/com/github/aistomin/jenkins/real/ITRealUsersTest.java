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
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().users().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<people>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<fullName>Integration Test</fullName>"),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</people>"), new IsEqual<>(true)
        );
    }

    /**
     * Can iterate through Jenkins' users.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterateThroughUsers() throws Exception {
        final Iterator<User> iterator = new TestJenkins().users().iterator();
        MatcherAssert.assertThat(
            iterator.next().username(), new IsEqual<>("\"system_builder")
        );
        MatcherAssert.assertThat(
            iterator.next().username(), new IsEqual<>("admin")
        );
    }

    /**
     * Can find user by username.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByUsername() throws Exception {
        final String username = "integration";
        final Iterator<User> users = new TestJenkins().users()
            .findByUsername(username);
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(true));
        MatcherAssert.assertThat(
            users.next().username(), new IsEqual<>(username)
        );
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(false));
    }

    /**
     * Can find user by full user name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByFullName() throws Exception {
        final String name = "Integration Test";
        final Iterator<User> users = new TestJenkins().users()
            .findByFullName(name);
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(true));
        MatcherAssert.assertThat(
            users.next().fullName(), new IsEqual<>(name)
        );
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(false));
    }

    /**
     * Can find user by user's email.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByEmail() throws Exception {
        final String email = "andrej.istomin.ikeen@gmail.com";
        final Iterator<User> users = new TestJenkins().users()
            .findByEmail(email);
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(true));
        MatcherAssert.assertThat(
            users.next().email(), new IsEqual<>(email)
        );
        MatcherAssert.assertThat(users.hasNext(), new IsEqual<>(false));
    }
}
