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

import com.github.aistomin.http.PostRequest;
import com.github.aistomin.iterators.EntityIterator;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.Jobs;
import com.jcabi.xml.XMLDocument;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' jobs.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobs implements Jobs {

    /**
     * API URL.
     */
    private final transient String api;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param url API URL.
     * @param credentials Jenkins credentials.
     */
    public RealJobs(final String url, final Credentials credentials) {
        this.api = url;
        this.creds = credentials;
    }

    /**
     * Jenkins jobs iterator.
     *
     * @return Jobs iterator.
     * @throws Exception If error occurred.
     */
    public Iterator<Job> iterator() throws Exception {
        final List<String> jobs = new XMLDocument(this.xml())
            .xpath("//job/displayName/text()");
        Collections.sort(jobs, String.CASE_INSENSITIVE_ORDER);
        return new EntityIterator<Job, String>(
            jobs.iterator(), new RealJob.Transformer(this.api, this.creds)
        );
    }

    /**
     * Find by Jenkins' job name.
     *
     * @param name Job's name.
     * @return Job.
     * @todo: Let's implement this method and solve Issue #31.
     */
    public Job findByName(final String name) {
        throw new NotImplementedException(
            String.format(
                "findByName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jobs' node XML content.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Creates API URL to request Jenkins' jobs data.
     *
     * @return URL string.
     */
    private String request() {
        return String.format(
            "%s%s", this.api, "&xpath=hudson/job&wrapper=jobs"
        );
    }
}
