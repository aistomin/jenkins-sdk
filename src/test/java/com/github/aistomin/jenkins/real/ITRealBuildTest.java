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

import com.github.aistomin.jenkins.BuildDetails;
import java.util.Date;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
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
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().builds()
            .iterator().next().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<build>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<displayName>#1</displayName>"),
            new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</build>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read build's number.
     *
     * @throws Exception If something happened.
     */
    @Test
    public void testCanReadNumber() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next().builds().iterator()
                .next().number(), new IsEqual<String>("#1")
        );
    }

    /**
     * Can read build's details.
     *
     * @throws Exception If something happened.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new TestJenkins().jobs().iterator().next()
            .builds().iterator().next().details();
        MatcherAssert.assertThat(
            details.fullDisplayName(),
            new IsEqual<String>("test-different-builds-job #1")
        );
    }

    /**
     * Can read build's url.
     *
     * @throws Exception If something happened.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next()
                .builds().iterator().next().url()
                .endsWith("job/test-different-builds-job/1/"),
            new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read build's date.
     *
     * @throws Exception If something happened.
     */
    @Test
    public void testCanReadDate() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next()
                .builds().iterator().next().date(),
            new IsInstanceOf(Date.class)
        );
    }
}
