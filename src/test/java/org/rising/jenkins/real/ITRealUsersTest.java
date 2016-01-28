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

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.rising.jenkins.User;

/**
 * Integration tests for RealUsers class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ITRealUsersTest {

    /**
     * Can read Jenkins' users XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new TestJenkins().users().xml();
        Assert.assertTrue(xml.startsWith("<people>"));
        Assert.assertTrue(
            xml.contains("<fullName>Integration Test</fullName>")
        );
        Assert.assertTrue(xml.endsWith("</people>"));
    }

    /**
     * Can list Jenkins' users.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListUsers() throws Exception {
        final List<User> users = new TestJenkins().users().list();
        Assert.assertEquals("\"system_builder", users.get(0).username());
        Assert.assertEquals("system_builder", users.get(1).username());
        Assert.assertEquals("admin", users.get(2).username());
    }
}
