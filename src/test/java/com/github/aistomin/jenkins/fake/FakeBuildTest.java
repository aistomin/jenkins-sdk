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

import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.real.RealBuildDetails;
import com.github.aistomin.xml.XmlString;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeBuild class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (1000 lines)
 * @todo: Let's re-work this test and solve issue #286.
 */
public final class FakeBuildTest {

    /**
     * Can create fake instances with default ctor.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithDefaultCtor() throws Exception {
        final FakeBuild build = new FakeBuild();
        MatcherAssert.assertThat(build.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(
            build.number(), new IsInstanceOf(String.class)
        );
        MatcherAssert.assertThat(
            build.result(), new IsEqual<BuildResult>(BuildResult.SUCCESS)
        );
        MatcherAssert.assertThat(build.date(), new IsInstanceOf(Date.class));
        MatcherAssert.assertThat(
            build.details(), new IsInstanceOf(RealBuildDetails.class)
        );
        MatcherAssert.assertThat(
            build.url().startsWith("http://"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read fake build's XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeBuild(
            new XmlString("<build><displayName>#1</displayName></build>")
        ).xml();
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
     * Can read fake build's number.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadNumber() throws Exception {
        final String number = "#666";
        MatcherAssert.assertThat(
            new FakeBuild(number).number(), new IsEqual<String>(number)
        );
    }

    /**
     * Can read fake build's result.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadResult() throws Exception {
        final BuildResult result = BuildResult.FAILURE;
        MatcherAssert.assertThat(
            new FakeBuild(result).result(), new IsEqual<BuildResult>(result)
        );
    }

    /**
     * Can read fake build's date.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadDate() throws Exception {
        final Date date = new Date();
        MatcherAssert.assertThat(
            new FakeBuild(date).date(), new IsEqual<Date>(date)
        );
    }

    /**
     * Can read fake build's details.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        final BuildDetails details = new RealBuildDetails(
            new XmlString("<build></build>")
        );
        MatcherAssert.assertThat(
            new FakeBuild(details).details(), new IsEqual<BuildDetails>(details)
        );
    }

    /**
     * Can read fake build's URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        final URL url = new URL("http", "localhost", 8080, "/test");
        MatcherAssert.assertThat(
            new FakeBuild(url).url(),
            new IsEqual<String>("http://localhost:8080/test")
        );
    }

    /**
     * Can emulate delete/cancel actions.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanEmulateActions() throws Exception {
        final List<String> calls = new ArrayList<String>(2);
        final Runnable runnable = new Runnable() {
            public void run() {
                calls.add("Deleted!!!");
            }
        };
        final FakeBuild build = new FakeBuild(runnable, runnable);
        build.delete();
        MatcherAssert.assertThat(calls.size(), new IsEqual<Integer>(1));
        build.cancel();
        MatcherAssert.assertThat(calls.size(), new IsEqual<Integer>(2));
    }
}
