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
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
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
        MatcherAssert.assertThat(
            build.xml(), new IsEqual<>(xml().content())
        );
        final String number = "#1";
        MatcherAssert.assertThat(
            build.number(), new IsEqual<>(number)
        );
        MatcherAssert.assertThat(
            build.result(), new IsEqual<>(BuildResult.SUCCESS)
        );
        MatcherAssert.assertThat(build.date(), new IsInstanceOf(Date.class));
        final BuildDetails details = build.details();
        MatcherAssert.assertThat(
            details.displayName(), new IsEqual<>(number)
        );
        MatcherAssert.assertThat(
            details.fullDisplayName(),
            new IsEqual<>("test-different-builds-job #1")
        );
        MatcherAssert.assertThat(
            build.url().startsWith("https://"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            build.details().gitRevision(),
            new IsEqual<>("3d21ea7072da134395eedbc7a07bf0f00cfabf97")
        );
    }

    /**
     * Can emulate delete/cancel actions.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanEmulateActions() throws Exception {
        final List<String> calls = new ArrayList<>(2);
        final Runnable runnable = new Runnable() {
            public void run() {
                calls.add("Deleted!!!");
            }
        };
        final FakeBuild build = new FakeBuild(xml(), runnable, runnable);
        build.delete();
        MatcherAssert.assertThat(calls.size(), new IsEqual<>(1));
        build.cancel();
        MatcherAssert.assertThat(calls.size(), new IsEqual<>(2));
    }

    /**
     * Create default build XML.
     *
     * @return Xml.
     */
    private static Xml xml() {
        return new XmlResource("build.xml");
    }
}
