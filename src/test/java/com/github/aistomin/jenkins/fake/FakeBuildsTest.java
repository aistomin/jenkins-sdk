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
 * Test for FakeBuilds class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeBuildsTest {

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXML() throws Exception {
        final String xml = "<builds><build>#1</build></builds>";
        Assert.assertEquals(
            xml, new FakeBuilds(new XMLString(xml)).xml()
        );
    }

    /**
     * Can read fake builds' XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new FakeBuilds().xml();
        Assert.assertTrue(xml.startsWith("<builds>"));
        Assert.assertTrue(xml.contains("test-different-builds-job #3"));
        Assert.assertTrue(xml.endsWith("</builds>"));
    }
}
