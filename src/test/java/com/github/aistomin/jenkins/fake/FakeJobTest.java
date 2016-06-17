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

import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for FakeJob class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJobTest {

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithDefaultCtor() throws Exception {
        final FakeJob job = new FakeJob();
        MatcherAssert.assertThat(
            job.xml(), new IsEqual<>(FakeJobTest.xml().content())
        );
        final String name = "test-different-builds-job";
        MatcherAssert.assertThat(job.name(), new IsEqual<>(name));
        final JobDetails details = job.details();
        MatcherAssert.assertThat(details.displayName(), new IsEqual<>(name));
        MatcherAssert.assertThat(details.buildable(), new IsEqual<>(true));
        MatcherAssert.assertThat(
            details.description(),
            new IsEqual<>("This job we use for testing builds.")
        );
        MatcherAssert.assertThat(
            job.url(),
            new IsEqual<>(
                String.format(
                    "https://cisdk-istomin.rhcloud.com/%s",
                    "job/test-different-builds-job/"
                )
            )
        );
    }

    /**
     * Can trigger Fake job.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanTrigger() throws Exception {
        final List<String> calls = new ArrayList<>(1);
        new FakeJob(
            FakeJobTest.xml(),
            new Runnable() {
                public void run() {
                    calls.add("called!!!");
                }
            }
        ).trigger();
        MatcherAssert.assertThat(calls.size(), new IsEqual<>(1));
    }

    /**
     * Get default job XML.
     * @return Xml.
     */
    private static Xml xml() {
        return new XmlResource("job.xml");
    }
}
