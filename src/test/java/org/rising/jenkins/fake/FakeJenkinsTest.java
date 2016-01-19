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
     * Can create fake instances using different constructors.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructors() throws Exception {
        final Jenkins def = new FakeJenkins();
        Assert.assertNotNull(def.jobs());
        Assert.assertNotNull(def.users());
        final Users users = new FakeUsers();
        final Jenkins secondaryone = new FakeJenkins(users);
        Assert.assertNotNull(secondaryone.jobs());
        Assert.assertEquals(users, secondaryone.users());
        final Jobs jobs = new FakeJobs();
        final Jenkins secondarytwo = new FakeJenkins(jobs);
        Assert.assertNotNull(secondarytwo.users());
        Assert.assertEquals(jobs, secondarytwo.jobs());
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
}
