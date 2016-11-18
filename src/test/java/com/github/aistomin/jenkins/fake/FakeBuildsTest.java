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

import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.xml.XmlString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeBuilds class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuildsTest {

    /**
     * Can create fake instances providing nothing.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithDefaultCtor() throws Exception {
        final FakeBuilds builds = new FakeBuilds();
        MatcherAssert.assertThat(builds.xml(), new IsInstanceOf(String.class));
        final Iterator<Build> iterator = builds.iterator();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(true)
        );
        iterator.next();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(true)
        );
        iterator.next();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can read fake builds' XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeBuilds(
            new XmlString("<builds><build>#1</build></builds>")
        ).xml();
        MatcherAssert.assertThat(
            xml.startsWith("<builds>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<build>#1</build>"),
            new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</builds>"), new IsEqual<>(true)
        );
    }

    /**
     * Can iterate through builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final List<Build> builds = new ArrayList<>(2);
        builds.add(new FakeBuild());
        builds.add(new FakeBuild());
        final Iterator<Build> iterator = new FakeBuilds(builds).iterator();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsEqual<>(builds.get(0))
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsEqual<>(builds.get(1))
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can find by number.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByNumber() throws Exception {
        final List<Build> builds = new ArrayList<>(2);
        builds.add(
            new FakeBuild(
                new XmlString("<build><displayName>#1</displayName></build>"),
                new DefaultBuildActions()
            )
        );
        final String number = "#2";
        builds.add(
            new FakeBuild(
                new XmlString("<build><displayName>#2</displayName></build>"),
                new DefaultBuildActions()
            )
        );
        final Iterator<Build> found = new FakeBuilds(builds)
            .findByNumber(number);
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            found.next().number(), new IsEqual<>(number)
        );
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can find by Git revision.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByGitRevision() throws Exception {
        final List<Build> builds = new ArrayList<>(2);
        builds.add(
            new FakeBuild(
                new XmlString(
                    String.format(
                        "%s%s", "<build><action><lastBuiltRevision><SHA1>123",
                        "</SHA1></lastBuiltRevision></action></build>"
                    )
                ),
                new DefaultBuildActions()
            )
        );
        final String rev = "456";
        builds.add(
            new FakeBuild(
                new XmlString(
                    String.format(
                        "%s%s%s", "<build><action><lastBuiltRevision>",
                        "<SHA1>456</SHA1>",
                        "</lastBuiltRevision></action></build>"
                    )
                ),
                new DefaultBuildActions()
            )
        );
        final Iterator<Build> found = new FakeBuilds(builds)
            .findByGitRevision(rev);
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            found.next().details().gitRevision(), new IsEqual<>(rev)
        );
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can read last unsuccessful build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadLastUnsuccessfulBuild() throws Exception {
        final List<Build> builds = new ArrayList<>(2);
        builds.add(build(BuildResult.SUCCESS));
        final Build aborted = build(BuildResult.ABORTED);
        builds.add(aborted);
        MatcherAssert.assertThat(
            new FakeBuilds(builds).lastUnsuccessful(),
            new IsEqual<>(aborted)
        );
    }

    /**
     * Can read last successful build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadLastSuccessfulBuild() throws Exception {
        final List<Build> builds = new ArrayList<>(3);
        builds.add(build(BuildResult.FAILURE));
        final Build successful = build(BuildResult.SUCCESS);
        builds.add(successful);
        builds.add(build(BuildResult.ABORTED));
        MatcherAssert.assertThat(
            new FakeBuilds(builds).lastSuccessful(),
            new IsEqual<>(successful)
        );
    }

    /**
     * Can read last failed build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadLastFailedBuild() throws Exception {
        final List<Build> builds = new ArrayList<>(3);
        final Build failed = build(BuildResult.FAILURE);
        builds.add(failed);
        builds.add(build(BuildResult.SUCCESS));
        builds.add(build(BuildResult.ABORTED));
        MatcherAssert.assertThat(
            new FakeBuilds(builds).lastFailed(), new IsEqual<>(failed)
        );
    }

    /**
     * Can read last stable build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadLastStableBuild() throws Exception {
        final List<Build> builds = new ArrayList<>(3);
        builds.add(build(BuildResult.FAILURE));
        final Build successful = build(BuildResult.SUCCESS);
        builds.add(successful);
        builds.add(build(BuildResult.ABORTED));
        MatcherAssert.assertThat(
            new FakeBuilds(builds).lastStable(),
            new IsEqual<>(successful)
        );
    }

    /**
     * Create build with the specified result.
     *
     * @param result Build result.
     * @return Build.
     */
    private static Build build(final BuildResult result) {
        return new FakeBuild(
            new XmlString(
                String.format(
                    "<build><result>%s</result></build>",
                    result.name()
                )
            ),
            new DefaultBuildActions()
        );
    }
}
