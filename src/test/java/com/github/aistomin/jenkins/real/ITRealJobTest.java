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

import com.github.aistomin.jenkins.JobDetails;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Tests for RealJob.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealJobTest {

    /**
     * Can get job's XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<job>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains(
                "<displayName>test-different-builds-job</displayName>"
            ), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</job>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can list job's builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListBuilds() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next().builds(),
            new IsInstanceOf(RealBuilds.class)
        );
    }

    /**
     * Can read job's details.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadJobDetails() throws Exception {
        final JobDetails details = new TestJenkins().jobs().iterator().next()
            .details();
        MatcherAssert.assertThat(
            details.displayName(),
            new IsEqual<String>("test-different-builds-job")
        );
        MatcherAssert.assertThat(
            details.description(),
            new IsEqual<String>("This job we use for testing builds.")
        );
        MatcherAssert.assertThat(
            details.buildable(), new IsEqual<Boolean>(true)
        );
    }
}
