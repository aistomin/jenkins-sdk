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
package org.rising.jenkins.real;

import com.jcabi.xml.XMLDocument;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.http.PostRequest;
import org.rising.iterators.EntityIterator;
import org.rising.jenkins.Credentials;
import org.rising.jenkins.User;
import org.rising.jenkins.Users;

/**
 * Jenkins' users.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealUsers implements Users {

    /**
     * Base Jenkins URL.
     */
    private final transient String base;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param url Base Jenkins URL.
     * @param credentials Jenkins credentials.
     */
    public RealUsers(final String url, final Credentials credentials) {
        this.base = url;
        this.creds = credentials;
    }

    /**
     * Build iterator to run through existing users.
     *
     * @return Users iterator.
     * @throws Exception If error occurred.
     */
    public Iterator<User> iterator() throws Exception {
        final List<String> ids = new XMLDocument(this.xml())
            .xpath("//id/text()");
        Collections.sort(ids, String.CASE_INSENSITIVE_ORDER);
        return new EntityIterator<User, String>(
            ids.iterator(), new RealUser.Transformer(this.request(), this.creds)
        );
    }

    /**
     * Find by Jenkins' user name.
     *
     * @param username Username(aka ID).
     * @return User.
     * @todo: Let's implement this method and solve Issue #68.
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
     * Find by Jenkins' user email.
     *
     * @param email Username(aka ID).
     * @return Iterator of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #69.
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
     * Find by Jenkins' user full name.
     *
     * @param name Full name or part of it.
     * @return Iterator of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #70.
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
     * Users XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Creates API URL to request Jenkins' users data.
     *
     * @return URL string.
     */
    private String request() {
        return String.format(
            "%s/%s", this.base,
            "asynchPeople/api/xml?depth=3"
        );
    }
}
