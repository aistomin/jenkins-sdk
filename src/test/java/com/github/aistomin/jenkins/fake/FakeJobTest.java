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

import com.github.aistomin.xml.XMLString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for FakeJob class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJobTest {

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXML() throws Exception {
        final String xml = "<job><id><displayName>test</displayName></job>";
        Assert.assertEquals(
            xml, new FakeJob(new XMLString(xml)).xml()
        );
    }

    /**
     * Can read Fake job's XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new FakeJob().xml();
        Assert.assertTrue(xml.startsWith("<job>"));
        Assert.assertTrue(
            xml.contains("<displayName>test-different-builds-job</displayName>")
        );
        Assert.assertTrue(xml.endsWith("</job>"));
    }
}
