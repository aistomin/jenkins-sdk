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
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
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
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().builds()
            .xml();
        MatcherAssert.assertThat(
            xml.startsWith("<builds>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<fullDisplayName>test-different-builds-job #3"),
            new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</builds>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can iterate through builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final Iterator<Build> builds = new TestJenkins().jobs().iterator()
            .next().builds().iterator();
        MatcherAssert.assertThat(
            builds.next().number(), new IsEqual<String>("#1")
        );
        MatcherAssert.assertThat(
            builds.next().number(), new IsEqual<String>("#2")
        );
    }

    /**
     * Can get last successful build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastSuccessful() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastSuccessful();
        MatcherAssert.assertThat(
            last.details().fullDisplayName(),
            new IsEqual<String>("test-different-builds-job #3")
        );
    }

    /**
     * Can get last stable build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastStable() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastStable();
        MatcherAssert.assertThat(
            last.details().displayName(), new IsEqual<String>("#3")
        );
    }

    /**
     * Can get last stable build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastFailed() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastFailed();
        MatcherAssert.assertThat(
            last.details().fullDisplayName(),
            new IsEqual<String>("test-different-builds-job #2")
        );
    }
}
