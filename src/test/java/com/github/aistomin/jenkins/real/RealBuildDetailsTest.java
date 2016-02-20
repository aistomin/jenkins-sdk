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
import com.github.aistomin.xml.Xml1Resource;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Integration tests for RealBuildDetails class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuildDetailsTest {

    /**
     * Can read build's details.
     *
     * @throws Exception If something is not OK.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new RealBuildDetails(
            new Xml1Resource("build.xml")
        );
        MatcherAssert.assertThat(
            details.fullDisplayName(),
            new IsEqual<String>("test-different-builds-job #1")
        );
        MatcherAssert.assertThat(
            details.displayName(), new IsEqual<String>("#1")
        );
        MatcherAssert.assertThat(
            details.estimated(), new IsEqual<Long>(Long.parseLong("389"))
        );
        MatcherAssert.assertThat(
            details.duration(), new IsEqual<Long>(Long.parseLong("687"))
        );
        MatcherAssert.assertThat(
            details.building(), new IsEqual<Boolean>(false)
        );
        MatcherAssert.assertThat(details.queue(), new IsEqual<Long>(1L));
    }
}
