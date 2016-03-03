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
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        iterator.next();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        iterator.next();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(false)
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
            xml.startsWith("<builds>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains("<build>#1</build>"),
            new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</builds>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can iterate through builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final List<Build> builds = new ArrayList<Build>(2);
        builds.add(new FakeBuild());
        builds.add(new FakeBuild());
        final Iterator<Build> iterator = new FakeBuilds(builds).iterator();
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsEqual<Build>(builds.get(0))
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            iterator.next(), new IsEqual<Build>(builds.get(1))
        );
        MatcherAssert.assertThat(
            iterator.hasNext(), new IsEqual<Boolean>(false)
        );
    }

    /**
     * Can find by number.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByNumber() throws Exception {
        final List<Build> builds = new ArrayList<Build>(2);
        builds.add(new FakeBuild("#1"));
        final String number = "#2";
        builds.add(new FakeBuild(number));
        final Iterator<Build> found = new FakeBuilds(builds)
            .findByNumber(number);
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            found.next().number(), new IsEqual<String>(number)
        );
        MatcherAssert.assertThat(
            found.hasNext(), new IsEqual<Boolean>(false)
        );
    }
}
