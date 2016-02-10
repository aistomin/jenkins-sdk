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
 * Integration tests for RealBuild class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealBuildTest {

    /**
     * Can get Jenkins' job build XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().builds()
            .iterator().next().xml();
        Assert.assertTrue(xml.startsWith("<build>"));
        Assert.assertTrue(xml.contains("<displayName>#1</displayName>"));
        Assert.assertTrue(xml.endsWith("</build>"));
    }

    /**
     * Can read build's number.
     *
     * @throws Exception If something happened.
     */
    @Test
    public void testCanReadNumber() throws Exception {
        Assert.assertEquals(
            "#1",
            new TestJenkins().jobs().iterator().next().builds().iterator()
                .next().number()
        );
    }
}
