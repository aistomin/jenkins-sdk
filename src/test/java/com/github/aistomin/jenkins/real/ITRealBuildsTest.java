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

import com.github.aistomin.jenkins.Build;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration tests for RealBuilds class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealBuildsTest {

    /**
     * Can get Jenkins' job builds XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().builds()
            .xml();
        Assert.assertTrue(xml.startsWith("<builds>"));
        Assert.assertTrue(
            xml.contains("<fullDisplayName>test-different-builds-job #3")
        );
        Assert.assertTrue(xml.endsWith("</builds>"));
    }

    /**
     * Can iterate through builds.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final Iterator<Build> builds = new TestJenkins().jobs().iterator()
            .next().builds().iterator();
        Assert.assertEquals("#1", builds.next().number());
        Assert.assertEquals("#2", builds.next().number());
        Assert.assertEquals("#3", builds.next().number());
    }
}
