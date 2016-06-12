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
import com.github.aistomin.jenkins.BuildParameter;
import com.github.aistomin.xml.XmlResource;
import java.util.Iterator;
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
public final class ITRealBuildDetailsTest {

    /**
     * Can read build's details.
     *
     * @throws Exception If something is not OK.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new RealBuildDetails(
            new XmlResource("build.xml")
        );
        MatcherAssert.assertThat(
            details.fullDisplayName(),
            new IsEqual<>("test-different-builds-job #1")
        );
        MatcherAssert.assertThat(
            details.displayName(), new IsEqual<>("#1")
        );
        MatcherAssert.assertThat(
            details.estimated(), new IsEqual<>(Long.parseLong("389"))
        );
        MatcherAssert.assertThat(
            details.duration(), new IsEqual<>(Long.parseLong("687"))
        );
        MatcherAssert.assertThat(
            details.building(), new IsEqual<>(false)
        );
        MatcherAssert.assertThat(details.queue(), new IsEqual<>(1L));
    }

    /**
     * Can read build's parameters.
     *
     * @throws Exception If something is not OK.
     */
    @Test
    public void testCanReadParameters() throws Exception {
        final Iterator<BuildParameter> parameters = new TestJenkins().jobs()
            .findByName("test-parametrised-job").next().builds()
            .findByNumber("#4").next().details().parameters();
        final BuildParameter parameter = parameters.next();
        MatcherAssert.assertThat(
            parameter.name(), new IsEqual<>("niceParameter")
        );
        MatcherAssert.assertThat(
            parameter.value(), new IsEqual<>("some_value")
        );
        MatcherAssert.assertThat(
            parameters.hasNext(), new IsEqual<>(false)
        );
    }
}
