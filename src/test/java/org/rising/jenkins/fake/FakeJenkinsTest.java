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
package org.rising.jenkins.fake;

import org.junit.Assert;
import org.junit.Test;
import org.rising.jenkins.Jenkins;
import org.rising.jenkins.Jobs;
import org.rising.jenkins.Users;

/**
 * Test for FakeJenkins class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJenkinsTest {

    /**
     * Can create fake instances using default constructor.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testDefaultConstructor() throws Exception {
        final Jenkins jenkins = new FakeJenkins();
        Assert.assertNotNull(jenkins.jobs());
        Assert.assertNotNull(jenkins.users());
        Assert.assertNotNull(jenkins.xml());
        Assert.assertTrue(jenkins.xml().startsWith("<hudson>"));
        Assert.assertTrue(
            jenkins.xml().contains(
                "https://cisdk-istomin.rhcloud.com/job/test-disabled-job/"
            )
        );
        Assert.assertTrue(jenkins.xml().endsWith("</hudson>"));
    }

    /**
     * Can create fake instances providing only Users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithUsers() throws Exception {
        final Users users = new FakeUsers();
        final Jenkins jenkins = new FakeJenkins(users);
        Assert.assertNotNull(jenkins.jobs());
        Assert.assertNotNull(jenkins.xml());
        Assert.assertEquals(users, jenkins.users());
    }

    /**
     * Can create fake instances providing only Jobs.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithJobs() throws Exception {
        final Jobs jobs = new FakeJobs();
        final Jenkins jenkins = new FakeJenkins(jobs);
        Assert.assertNotNull(jenkins.users());
        Assert.assertNotNull(jenkins.xml());
        Assert.assertEquals(jobs, jenkins.jobs());
    }

    /**
     * Can create fake instances providing only XML.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithXML() throws Exception {
        final String xml = "<jenkins></jenkins>";
        final Jenkins jenkins = new FakeJenkins(xml);
        Assert.assertNotNull(jenkins.jobs());
        Assert.assertNotNull(jenkins.users());
        Assert.assertEquals(xml, jenkins.xml());
    }

    /**
     * Can list Jenkins' jobs.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListJobs() throws Exception {
        final Jobs jobs = new FakeJobs();
        Assert.assertEquals(
            jobs,
            new FakeJenkins(jobs, new FakeUsers(), "<test>test</test>").jobs()
        );
    }

    /**
     * Can list Jenkins' users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListUsers() throws Exception {
        final Users users = new FakeUsers();
        Assert.assertEquals(
            users,
            new FakeJenkins(new FakeJobs(), users, "<jen>jen</jen>").users()
        );
    }

    /**
     * Can read Jenkins' XML.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = "<hudson></hudson>";
        Assert.assertEquals(
            xml,
            new FakeJenkins(new FakeJobs(), new FakeUsers(), xml).xml()
        );
    }
}
