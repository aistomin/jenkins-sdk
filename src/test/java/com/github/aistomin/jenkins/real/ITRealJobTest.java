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
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.jenkins.JobParameter;
import java.util.Iterator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Ignore;
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
     * Delay for build start. We use to wait when Jenkins really starts the
     * build.
     */
    private static final Integer DELAY = 10000;

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

    /**
     * Can read job's URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        final Iterator<Job> jobs = new TestJenkins().jobs().iterator();
        jobs.next();
        MatcherAssert.assertThat(
            jobs.next().url(),
            new IsEqual<Object>(
                "https://cisdk-istomin.rhcloud.com/job/test-disabled-job/"
            )
        );
    }

    /**
     * Can read job's parameters.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadParameters() throws Exception {
        final Iterator<Job> jobs = new TestJenkins().jobs().findByName(
            "test-parametrised-job"
        );
        final Iterator<JobParameter> params = jobs.next().parameters();
        MatcherAssert.assertThat(params.hasNext(), new IsEqual<Boolean>(true));
        MatcherAssert.assertThat(
            params.next().name(), new IsEqual<String>("stringParameter")
        );
        MatcherAssert.assertThat(params.hasNext(), new IsEqual<Boolean>(true));
        MatcherAssert.assertThat(
            params.next().name(), new IsEqual<String>("boolParam")
        );
        MatcherAssert.assertThat(params.hasNext(), new IsEqual<Boolean>(false));
    }

    /**
     * Can trigger job.
     *
     * @throws Exception If something goes wrong.
     * @todo: Let's solve Issue #235 and remove ignore annotation.
     */
    @Ignore
    @Test
    public void testCanTrigger() throws Exception {
        final Job job = new TestJenkins().jobs().findByName(
            "test-x-job-trigger"
        ).next();
        final int before = ITRealJobTest.count(job.builds().iterator());
        job.trigger();
        Thread.sleep(ITRealJobTest.DELAY);
        MatcherAssert.assertThat(
            before == ITRealJobTest.count(job.builds().iterator()),
            new IsEqual<Boolean>(false)
        );
        final Iterator<Build> iterator = job.builds().iterator();
        while (iterator.hasNext()) {
            iterator.next().delete();
        }
    }

    /**
     * Count of iterator elements.
     * @param iterator Iterator.
     * @return Count.
     */
    private static int count(final Iterator<Build> iterator) {
        int result = 0;
        while (iterator.hasNext()) {
            result += 1;
            iterator.next();
        }
        return result;
    }
}
