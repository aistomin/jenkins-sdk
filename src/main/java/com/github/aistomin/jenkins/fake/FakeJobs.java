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
import com.github.aistomin.jenkins.Jobs;
import com.github.aistomin.xml.XML;
import com.github.aistomin.xml.XMLResource;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' jobs for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJobs implements Jobs {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "jobs.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Fake jobs list that will be returned in iterator() method.
     */
    private final transient List<Job> iterable;

    /**
     * Default ctor.
     */
    public FakeJobs() {
        this(
            new XMLResource(
                FakeJobs.RESOURCE
            ), Arrays.asList(
                (Job) new FakeJob(), new FakeJob(), new FakeJob()
            )
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJobs(final XML xml) {
        this(
            xml, Arrays.asList(
                (Job) new FakeJob(), new FakeJob(), new FakeJob()
            )
        );
    }

    /**
     * Secondary ctor.
     *
     * @param jobs Fake jobs list that will be returned in iterator() method.
     */
    public FakeJobs(final List<Job> jobs) {
        this(
            new XMLResource(
                FakeJobs.RESOURCE
            ), jobs
        );
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param jobs Fake jobs list that will be returned in iterator() method.
     */
    public FakeJobs(final XML xml, final List<Job> jobs) {
        this.content = xml;
        this.iterable = jobs;
    }

    /**
     * Return jobs iterator that was set via ctor.
     *
     * @return Iterator of jobs.
     * @throws Exception If error occurred.
     */
    public Iterator<Job> iterator() throws Exception {
        return this.iterable.iterator();
    }

    /**
     * Find by Jenkins' job name.
     *
     * @param name Job's name.
     * @return Job.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #33.
     */
    public Iterator<Job> findByName(final String name) throws Exception {
        throw new NotImplementedException(
            String.format(
                "findByName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Return XML content that was set in ctor.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
