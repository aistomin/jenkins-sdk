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

import com.github.aistomin.http.PostRequest;
import com.github.aistomin.iterators.Transformation;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.jenkins.User;
import java.net.URLEncoder;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' user.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealUser implements User {

    /**
     * API URL.
     */
    private final transient String api;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * User's username.
     */
    private final transient String identifier;

    /**
     * Ctor.
     *
     * @param username User's username.
     * @param url API URL that returns users' details.
     * @param credentials Jenkins credentials
     */
    public RealUser(
        final String username, final String url, final Credentials credentials
    ) {
        this.api = url;
        this.creds = credentials;
        this.identifier = username;
    }

    /**
     * Username. This name is ID of user that can not be changed.
     *
     * @return Username.
     */
    public String username() {
        return this.identifier;
    }

    /**
     * Jenkins user's full name.
     *
     * @return User's full name.
     * @todo: Let's implement this method and solve Issue #79.
     */
    public String fullName() {
        throw new NotImplementedException(
            String.format(
                "fullName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jenkins user's email.
     *
     * @return User's email.
     * @todo: Let's implement this method and solve Issue #80.
     */
    public String email() {
        throw new NotImplementedException(
            String.format(
                "email() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * User's Jenkins page URL.
     *
     * @return URL string.
     * @todo: Let's implement this method and solve Issue #81.
     */
    public String url() {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jenkins user's description.
     *
     * @return Description string.
     * @todo: Let's implement this method and solve Issue #82.
     */
    public String description() {
        throw new NotImplementedException(
            String.format(
                "description() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jenkins user's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Creates API URL to request Jenkins' user data.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     */
    private String request() throws Exception {
        return String.format(
            "%s%s", this.api,
            String.format(
                "&xpath=people/user/user[id=%s]",
                URLEncoder.encode(
                    String.format("'%s'", this.identifier), "UTF-8"
                )
            )
        );
    }

    /**
     * Transformer for getting user by his id name.
     */
    public static final class Transformer implements
        Transformation<User, String> {

        /**
         * API URL.
         */
        private final transient String api;

        /**
         * Jenkins credentials.
         */
        private final transient Credentials creds;

        /**
         * Ctor.
         * @param url API URL.
         * @param credentials Jenkins credentials.
         */
        public Transformer(final String url, final Credentials credentials) {
            this.api = url;
            this.creds = credentials;
        }

        /**
         * Transform username to user.
         *
         * @param source Source object.
         * @return Target object.
         */
        public User transform(final String source) {
            return new RealUser(source, this.api, this.creds);
        }
    }
}
