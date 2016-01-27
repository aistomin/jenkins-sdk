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
package org.rising.jenkins.fake;

import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.User;
import org.rising.xml.XML;
import org.rising.xml.XMLResource;

/**
 * Fake Jenkins' user for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeUser implements User {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "user.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeUser() throws Exception {
        this(new XMLResource(RESOURCE));
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeUser(final XML xml) {
        this.content = xml;
    }

    /**
     * Fake username.
     *
     * @return Username.
     * @todo: Let's implement this method and solve Issue #72.
     */
    public String username() {
        throw new NotImplementedException(
            String.format(
                "username() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake user's full name.
     *
     * @return User's full name.
     * @todo: Let's implement this method and solve Issue #73.
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
     * Fake user's email.
     *
     * @return User's email.
     * @todo: Let's implement this method and solve Issue #74.
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
     * Fake user's URL.
     *
     * @return URL string.
     * @todo: Let's implement this method and solve Issue #75.
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
     * Fake user's description.
     *
     * @return Description string.
     * @todo: Let's implement this method and solve Issue #76.
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
     * Fake user's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
