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

import com.github.aistomin.jenkins.Job;
import com.github.aistomin.xml.XMLString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for FakeJobs class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJobsTest {

    /**
     * Can create fake jobs using fake XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXML() throws Exception {
        final String xml = "<jobs></jobs>";
        Assert.assertEquals(xml, new FakeJobs(new XMLString(xml)).xml());
    }

    /**
     * Can create fake jobs using fake list of jobs.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithJobs() throws Exception {
        final List<Job> expected = new ArrayList<Job>(2);
        expected.add(new FakeJob());
        expected.add(new FakeJob());
        final Iterator<Job> got = new FakeJobs(expected).iterator();
        Assert.assertEquals(expected.get(0), got.next());
        Assert.assertEquals(expected.get(1), got.next());
        Assert.assertFalse(got.hasNext());
    }

    /**
     * Can read fake jobs' XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXML() throws Exception {
        final String xml = new FakeJobs().xml();
        Assert.assertTrue(
            xml.contains("<displayName>test-different-builds-job</displayName>")
        );
        Assert.assertTrue(
            xml.contains("<displayName>test-disabled-job</displayName>")
        );
    }

    /**
     * Can iterate through jobs.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanIterate() throws Exception {
        final Iterator<Job> iterator = new FakeJobs().iterator();
        Assert.assertNotNull(iterator.next());
        Assert.assertNotNull(iterator.next());
        Assert.assertNotNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    /**
     * Can find job by name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanFindByName() throws Exception {
        final List<Job> jobs = new ArrayList<Job>(2);
        jobs.add(new FakeJob("job1"));
        final String name = "job2";
        jobs.add(new FakeJob(name));
        final Iterator<Job> found = new FakeJobs(jobs).findByName(name);
        Assert.assertEquals(name, found.next().name());
        Assert.assertFalse(found.hasNext());
    }
}
