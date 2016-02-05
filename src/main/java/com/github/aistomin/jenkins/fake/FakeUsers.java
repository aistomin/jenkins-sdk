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

import com.github.aistomin.jenkins.User;
import com.github.aistomin.jenkins.Users;
import com.github.aistomin.xml.XML;
import com.github.aistomin.xml.XMLResource;
import java.util.Iterator;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' users for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeUsers implements Users {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "users.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeUsers() throws Exception {
        this(new XMLResource(RESOURCE));
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeUsers(final XML xml) {
        this.content = xml;
    }

    /**
     * Build iterator to run through fake users.
     *
     * @return Users iterator.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #63.
     */
    public Iterator<User> iterator() throws Exception {
        throw new NotImplementedException(
            String.format(
                "iterator() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Find fake user by Jenkins' user name.
     *
     * @param username Username(aka ID).
     * @return User.
     * @todo: Let's implement this method and solve Issue #64.
     */
    public User findByUsername(final String username) {
        throw new NotImplementedException(
            String.format(
                "findByUsername() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Find fake user by Jenkins' user email.
     *
     * @param email Username(aka ID).
     * @return Iterator of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #65.
     */
    public Iterator<User> findByEmail(final String email) {
        throw new NotImplementedException(
            String.format(
                "findByEmail() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Find fake user by Jenkins' user full name.
     *
     * @param name Full name or part of it.
     * @return Iterator of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #66.
     */
    public Iterator<User> findByFullName(final String name) {
        throw new NotImplementedException(
            String.format(
                "findByFullName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Return XML content that was set in ctor.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}