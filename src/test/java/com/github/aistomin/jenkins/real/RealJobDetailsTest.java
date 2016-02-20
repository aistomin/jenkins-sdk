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
import com.github.aistomin.xml.Xml1Resource;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test for RealJobDetails.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobDetailsTest {

    /**
     * Can read job details.
     *
     * @throws Exception If something is wrong.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final JobDetails details = new RealJobDetails(
            new Xml1Resource("job.xml")
        );
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
