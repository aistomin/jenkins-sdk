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
import java.util.Iterator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Integration tests for RealBuilds class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealBuildsTest {

    /**
     * Can get Jenkins' job builds XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().jobs().iterator().next().builds()
            .xml();
        MatcherAssert.assertThat(
            xml.startsWith("<builds>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<fullDisplayName>test-different-builds-job #3"),
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
        final Iterator<Build> builds = new TestJenkins().jobs().iterator()
            .next().builds().iterator();
        MatcherAssert.assertThat(
            builds.next().details().fullDisplayName(),
            new IsEqual<>("test-different-builds-job #1")
        );
        builds.next();
        builds.next();
        MatcherAssert.assertThat(
            builds.hasNext(), new IsEqual<>(false)
        );
    }

    /**
     * Can get last successful build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastSuccessful() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastSuccessful();
        MatcherAssert.assertThat(
            last.details().fullDisplayName(),
            new IsEqual<>("test-different-builds-job #3")
        );
    }

    /**
     * Can get last stable build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastStable() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastStable();
        MatcherAssert.assertThat(
            last.details().displayName(), new IsEqual<>("#3")
        );
    }

    /**
     * Can get last failed build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetLastFailed() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastFailed();
        MatcherAssert.assertThat(
            last.details().fullDisplayName(),
            new IsEqual<>("test-different-builds-job #2")
        );
    }

    /**
     * Can get last unsuccessful build.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanGetUnsuccessfulFailed() throws Exception {
        final Build last = new TestJenkins().jobs().iterator()
            .next().builds().lastUnsuccessful();
        MatcherAssert.assertThat(
            last.details().displayName(), new IsEqual<>("#2")
        );
    }

    /**
     * Can find build by it's number.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByNumber() throws Exception {
        final String number = "#1";
        final Iterator<Build> found = new TestJenkins().jobs().iterator()
            .next().builds().findByNumber(number);
        MatcherAssert.assertThat(found.hasNext(), new IsEqual<>(true));
        MatcherAssert.assertThat(
            found.next().number(), new IsEqual<>(number)
        );
        MatcherAssert.assertThat(found.hasNext(), new IsEqual<>(false));
    }

    /**
     * Can find build by it's Git revision.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByGitRevision() throws Exception {
        final String rev = "3d21ea7072da134395eedbc7a07bf0f00cfabf97";
        final Iterator<Build> found = new TestJenkins().jobs()
            .findByName("test-parametrised-job").next().builds()
            .findByGitRevision(rev);
        MatcherAssert.assertThat(
            found.next().details().gitRevision(), new IsEqual<>(rev)
        );
        MatcherAssert.assertThat(
            found.next().details().gitRevision(), new IsEqual<>(rev)
        );
        MatcherAssert.assertThat(
            found.next().details().gitRevision(), new IsEqual<>(rev)
        );
        MatcherAssert.assertThat(found.hasNext(), new IsEqual<>(false));
    }
}
