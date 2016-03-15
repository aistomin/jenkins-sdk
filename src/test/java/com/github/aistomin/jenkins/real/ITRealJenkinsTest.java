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

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Integration tests for RealJenkins class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealJenkinsTest {

    /**
     * Can get Jenkins' XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<hudson>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains(
                "<displayName>test-different-builds-job</displayName>"
            ), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</hudson>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can list Jenkins' jobs.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListJobs() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs(), new IsInstanceOf(RealJobs.class)
        );
    }

    /**
     * Can list Jenkins' users.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListUsers() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().users(), new IsInstanceOf(RealUsers.class)
        );
    }

    /**
     * Can read Jenkins' version.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadVersion() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().version(), new IsEqual<String>("1.609.1")
        );
    }
}
