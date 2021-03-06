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
import com.github.aistomin.iterators.EntityIterator;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.jenkins.User;
import com.github.aistomin.jenkins.Users;
import com.jcabi.xml.XMLDocument;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Jenkins' users.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
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

    @Override
    public Iterator<User> iterator() throws Exception {
        final List<String> ids = new XMLDocument(this.xml())
            .xpath("//id/text()");
        Collections.sort(ids, String.CASE_INSENSITIVE_ORDER);
        return new EntityIterator<>(
            ids.iterator(), new RealUser.Transformer(this.request(), this.creds)
        );
    }

    @Override
    public Iterator<User> findByUsername(
        final String username
    ) throws Exception {
        return this.findBy("id", username);
    }

    @Override
    public Iterator<User> findByEmail(final String email) throws Exception {
        return new EntityIterator<>(
            RealUsers.parseUsers(
                new PostRequest(
                    this.url(
                        String.format(
                            "&xpath=people/user/user[%s]&wrapper=users",
                            String.format(
                                "property[address='%s']",
                                RealUsers.encode(email)
                            )
                        )
                    ), this.creds.headers()
                ).execute()
            ).iterator(), new RealUser.Transformer(this.request(), this.creds)
        );
    }

    @Override
    public Iterator<User> findByFullName(final String name) throws Exception {
        return this.findBy("fullName", name);
    }

    @Override
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Find user.
     *
     * @param field Search parameter's field.
     * @param value Search parameter's value.
     * @return Users.
     * @throws Exception If something goes wrong.
     */
    private Iterator<User> findBy(
        final String field, final String value
    ) throws Exception {
        return new EntityIterator<>(
            RealUsers.parseUsers(
                new PostRequest(
                    this.url(
                        String.format(
                            "&xpath=people/user/user[%s='%s']&wrapper=users",
                            field, RealUsers.encode(value)
                        )
                    ), this.creds.headers()
                ).execute()
            ).iterator(), new RealUser.Transformer(this.request(), this.creds)
        );
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

    /**
     * Create full Jenkins API URL.
     * @param path URL path.
     * @return URL string.
     */
    private String url(final String path) {
        return String.format("%s%s", this.request(), path);
    }

    /**
     * Parse users from XML.
     *
     * @param xml XML string.
     * @return Parsed users names.
     * @throws Exception If something goes wrong.
     */
    private static List<String> parseUsers(final String xml) throws Exception {
        final List<String> users = new XMLDocument(xml).xpath(
            "//user/id/text()"
        );
        Collections.sort(users, String.CASE_INSENSITIVE_ORDER);
        return users;
    }

    /**
     * Encode string.
     *
     * @param str String.
     * @return Encoded string.
     * @throws Exception If something goes wrong.
     */
    private static String encode(final String str) throws Exception {
        return URLEncoder.encode(str, "UTF-8");
    }
}
