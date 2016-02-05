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

import org.junit.Assert;
import org.junit.Test;

/**
 * Integration tests for RealUser class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ITRealUserTest {

    /**
     * First username in test users iterator.
     */
    private static final transient String USERNAME = "\"system_builder";

    /**
     * Can read user's username.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUsername() throws Exception {
        Assert.assertEquals(
            ITRealUserTest.USERNAME,
            new TestJenkins().users().iterator().next().username()
        );
    }

    /**
     * Can read Jenkins' user XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new TestJenkins().users().iterator().next().xml();
        Assert.assertTrue(xml.startsWith("<user>"));
        Assert.assertTrue(
            xml.contains(String.format("<id>%s</id>", ITRealUserTest.USERNAME))
        );
        Assert.assertTrue(xml.endsWith("</user>"));
    }
}
