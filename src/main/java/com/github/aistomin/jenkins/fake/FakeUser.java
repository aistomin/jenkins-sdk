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
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' user for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeUser implements User {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "user.xml";

    /**
     * Fake username format.
     */
    private static final String FORMAT = "user%s";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Username that should be returned in username() method.
     */
    private final transient String identifier;

    /**
     * Default ctor.
     */
    public FakeUser() {
        this(
            new XmlResource(FakeUser.RESOURCE),
            String.format(FakeUser.FORMAT, System.currentTimeMillis())
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeUser(final Xml xml) {
        this(xml, String.format(FakeUser.FORMAT, System.currentTimeMillis()));
    }

    /**
     * Secondary ctor.
     *
     * @param username Username that should be returned in username() method.
     */
    public FakeUser(final String username) {
        this(new XmlResource(FakeUser.RESOURCE), username);
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param username Username that should be returned in username() method.
     */
    public FakeUser(final Xml xml, final String username) {
        this.content = xml;
        this.identifier = username;
    }

    /**
     * Fake username.
     *
     * @return Username.
     * @throws Exception If something goes wrong.
     */
    public String username() throws Exception {
        return this.identifier;
    }

    /**
     * Fake user's full name.
     *
     * @return User's full name.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #73.
     */
    public String fullName() throws Exception {
        throw new NotImplementedException(
            String.format(
                "fullName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake user's email.
     *
     * @return User's email.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #74.
     */
    public String email() throws Exception {
        throw new NotImplementedException(
            String.format(
                "email() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake user's URL.
     *
     * @return URL string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #75.
     */
    public String url() throws Exception {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake user's description.
     *
     * @return Description string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #76.
     */
    public String description() throws Exception {
        throw new NotImplementedException(
            String.format(
                "description() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake user's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
