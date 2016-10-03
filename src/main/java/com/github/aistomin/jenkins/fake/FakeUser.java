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

/**
 * Fake Jenkins' user for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeUser implements User {

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Default ctor.
     */
    public FakeUser() {
        this(new XmlResource("user.xml"));
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeUser(final Xml xml) {
        this.content = xml;
    }

    @Override
    public String username() throws Exception {
        return this.content.field("//user/id/text()");
    }

    @Override
    public String fullName() throws Exception {
        return this.content.field("//user/fullName/text()");
    }

    @Override
    public String email() throws Exception {
        return this.content.field("//user/property/address/text()");
    }

    @Override
    public String url() throws Exception {
        return this.content.field("//user/absoluteUrl/text()");
    }

    @Override
    public String description() throws Exception {
        return this.content.field("//user/description/text()");
    }

    @Override
    public String xml() throws Exception {
        return this.content.content();
    }
}
