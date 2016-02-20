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

import com.github.aistomin.jenkins.Jenkins;
import com.github.aistomin.jenkins.Jobs;
import com.github.aistomin.jenkins.Users;
import com.github.aistomin.xml.Xml1String;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeJenkins class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJenkinsTest {

    /**
     * Can create fake instances using default constructor.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testDefaultConstructor() throws Exception {
        final Jenkins jenkins = new FakeJenkins();
        MatcherAssert.assertThat(
            jenkins.jobs(), new IsInstanceOf(FakeJobs.class)
        );
        MatcherAssert.assertThat(
            jenkins.users(), new IsInstanceOf(FakeUsers.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(
            jenkins.xml().startsWith("<hudson>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            jenkins.xml().contains(
                "https://cisdk-istomin.rhcloud.com/job/test-disabled-job/"
            ), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            jenkins.xml().endsWith("</hudson>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can create fake instances providing only Users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithUsers() throws Exception {
        final Users users = new FakeUsers();
        final Jenkins jenkins = new FakeJenkins(users);
        MatcherAssert.assertThat(
            jenkins.jobs(), new IsInstanceOf(FakeJobs.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(jenkins.users(), new IsEqual<Users>(users));
    }

    /**
     * Can create fake instances providing only Jobs.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithJobs() throws Exception {
        final Jobs jobs = new FakeJobs();
        final Jenkins jenkins = new FakeJenkins(jobs);
        MatcherAssert.assertThat(
            jenkins.users(), new IsInstanceOf(FakeUsers.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(jenkins.jobs(), new IsEqual<Jobs>(jobs));
    }

    /**
     * Can create fake instances providing only XML.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithXml() throws Exception {
        final String xml = "<jenkins></jenkins>";
        final Jenkins jenkins = new FakeJenkins(new Xml1String(xml));
        MatcherAssert.assertThat(
            jenkins.jobs(), new IsInstanceOf(FakeJobs.class)
        );
        MatcherAssert.assertThat(
            jenkins.users(), new IsInstanceOf(FakeUsers.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsEqual<String>(xml));
    }

    /**
     * Can list Jenkins' jobs.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListJobs() throws Exception {
        final Jobs jobs = new FakeJobs();
        MatcherAssert.assertThat(
            new FakeJenkins(
                jobs, new FakeUsers(), new Xml1String("<test>test</test>")
            ).jobs(), new IsEqual<Jobs>(jobs)
        );
    }

    /**
     * Can list Jenkins' users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListUsers() throws Exception {
        final Users users = new FakeUsers();
        MatcherAssert.assertThat(
            new FakeJenkins(
                new FakeJobs(), users, new Xml1String("<jen>jen</jen>")
            ).users(), new IsEqual<Users>(users)
        );
    }

    /**
     * Can read Jenkins' XML.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = "<hudson></hudson>";
        MatcherAssert.assertThat(
            new FakeJenkins(
                new FakeJobs(), new FakeUsers(), new Xml1String(xml)
            ).xml(), new IsEqual<String>(xml)
        );
    }
}
