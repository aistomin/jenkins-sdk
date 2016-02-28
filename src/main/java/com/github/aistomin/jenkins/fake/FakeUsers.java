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
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Fake Jenkins' users for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeUsers implements Users {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "users.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * List of users that will be used for iteration and search.
     */
    private final transient List<User> users;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeUsers() throws Exception {
        this(new XmlResource(FakeUsers.RESOURCE), FakeUsers.defaultUsers());
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeUsers(final Xml xml) {
        this(xml, FakeUsers.defaultUsers());
    }

    /**
     * Secondary ctor.
     *
     * @param list List of users that will be used for iteration and search.
     */
    public FakeUsers(final List<User> list) {
        this(new XmlResource(FakeUsers.RESOURCE), list);
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param list List of users that will be used for iteration and search.
     */
    public FakeUsers(final Xml xml, final List<User> list) {
        this.content = xml;
        this.users = list;
    }

    /**
     * Build iterator to run through fake users.
     *
     * @return Users iterator.
     * @throws Exception If error occurred.
     */
    public Iterator<User> iterator() throws Exception {
        return this.users.iterator();
    }

    /**
     * Find fake user by Jenkins' user name.
     *
     * @param username Username(aka ID).
     * @return User.
     * @throws Exception If something goes wrong.
     */
    public Iterator<User> findByUsername(
        final String username
    ) throws Exception {
        final List<User> result = new ArrayList<User>(1);
        for (final User user : this.users) {
            if (username.equals(user.username())) {
                result.add(user);
            }
        }
        return result.iterator();
    }

    /**
     * Find fake user by Jenkins' user email.
     *
     * @param email User's email.
     * @return Iterator of users who match the criteria.
     * @throws Exception If something goes wrong.
     */
    public Iterator<User> findByEmail(final String email) throws Exception {
        final List<User> result = new ArrayList<User>(1);
        for (final User user : this.users) {
            if (email.equals(user.email())) {
                result.add(user);
            }
        }
        return result.iterator();
    }

    /**
     * Find fake user by Jenkins' user full name.
     *
     * @param name Full name or part of it.
     * @return Iterator of users who match the criteria.
     * @throws Exception If something goes wrong.
     */
    public Iterator<User> findByFullName(final String name) throws Exception {
        final List<User> result = new ArrayList<User>(1);
        for (final User user : this.users) {
            if (name.equals(user.fullName())) {
                result.add(user);
            }
        }
        return result.iterator();
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

    /**
     * Default users list.
     *
     * @return List of users.
     */
    private static List<User> defaultUsers() {
        final List<User> users = new ArrayList<User>(2);
        users.add(new FakeUser());
        users.add(new FakeUser());
        return users;
    }
}
