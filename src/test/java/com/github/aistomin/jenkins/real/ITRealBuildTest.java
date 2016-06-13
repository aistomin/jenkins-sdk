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
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.Job;
import java.util.Date;
import java.util.Iterator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Ignore;
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
     * Delay for build start. We use to wait when Jenkins really starts the
     * build.
     */
    private static final Integer DELAY = 3000;

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
            xml.startsWith("<build>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<displayName>#1</displayName>"),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</build>"), new IsEqual<>(true)
        );
    }

    /**
     * Can read build's number.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadNumber() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next().builds().iterator()
                .next().number(), new IsEqual<>("#1")
        );
    }

    /**
     * Can read build's details.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new TestJenkins().jobs().iterator().next()
            .builds().iterator().next().details();
        MatcherAssert.assertThat(
            details.fullDisplayName(),
            new IsEqual<>("test-different-builds-job #1")
        );
    }

    /**
     * Can read build's url.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next()
                .builds().iterator().next().url()
                .endsWith("job/test-different-builds-job/1/"),
            new IsEqual<>(true)
        );
    }

    /**
     * Can read build's date.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadDate() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next()
                .builds().iterator().next().date(),
            new IsInstanceOf(Date.class)
        );
    }

    /**
     * Can read build's result.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadResult() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().iterator().next()
                .builds().iterator().next().result(),
            new IsEqual<>(BuildResult.SUCCESS)
        );
    }

    /**
     * Can cancel build.
     *
     * @throws Exception If something went wrong.
     * @todo: Let's solve Issue #235 and remove ignore annotation.
     */
    @Ignore
    @Test
    public void testCanCancelBuild() throws Exception {
        final Job job = new TestJenkins().jobs().findByName(
            "test-x-job-trigger"
        ).next();
        ITRealBuildTest.delete(job.builds().iterator());
        job.trigger();
        Thread.sleep(ITRealBuildTest.DELAY);
        final Build build = job.builds().iterator().next();
        build.cancel();
        MatcherAssert.assertThat(
            build.result(), new IsEqual<>(BuildResult.ABORTED)
        );
        ITRealBuildTest.delete(job.builds().iterator());
    }

    /**
     * Can read Git revision that was built in current build.
     *
     * @throws Exception If something went wrong.
     */
    @Test
    public void testCanReadGitRevision() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().jobs().findByName(
                "test-parametrised-job"
            ).next().builds().findByNumber("#4").next().gitRevision(),
            new IsEqual<>("3d21ea7072da134395eedbc7a07bf0f00cfabf97")
        );
    }

    /**
     * Delete all builds.
     *
     * @param builds Builds iterator.
     * @throws Exception If something went wrong.
     */
    private static void delete(final Iterator<Build> builds) throws Exception {
        while (builds.hasNext()) {
            builds.next().delete();
        }
    }
}
