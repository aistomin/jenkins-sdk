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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.http.PostRequest;
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
     * Users' details request.
     */
    private final transient String request;

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
        this.request = String.format(
            "%s/%s", url,
            "asynchPeople/api/xml?depth=3"
        );
        this.creds = credentials;
    }

    /**
     * List all Jenkins users.
     *
     * @return List of users.
     * @throws Exception If error occurred.
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public List<User> list() throws Exception {
        final List<String> ids = new XMLDocument(
            new PostRequest(
                String.format(
                    "%s%s", this.request,
                    "&xpath=people/user/user/id&wrapper=usernames"
                ), this.creds.headers()
            ).execute()
        ).xpath("//id/text()");
        Collections.sort(ids, String.CASE_INSENSITIVE_ORDER);
        final List<User> result = new ArrayList<User>(ids.size());
        for (final String username : ids) {
            result.add(new RealUser(username, this.request, this.creds));
        }
        return result;
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
     * @return List of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #69.
     */
    public List<User> findByEmail(final String email) {
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
     * @return List of users who match the criteria.
     * @todo: Let's implement this method and solve Issue #70.
     */
    public List<User> findByFullName(final String name) {
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
        return new PostRequest(this.request, this.creds.headers()).execute();
    }
}
