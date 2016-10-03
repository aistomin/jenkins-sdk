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

    @Override
    public Iterator<Job> iterator() throws Exception {
        return new EntityIterator<>(
            RealJobs.parseJobs(this.xml()).iterator(),
            new RealJob.Transformer(this.api, this.creds)
        );
    }

    @Override
    public Iterator<Job> findByName(final String name) throws Exception {
        return new EntityIterator<>(
            RealJobs.parseJobs(
                new PostRequest(
                    this.url(
                        String.format(
                            "&xpath=hudson/job[displayName='%s']&wrapper=jobs",
                            name
                        )
                    ), this.creds.headers()
                ).execute()
            ).iterator(), new RealJob.Transformer(this.api, this.creds)
        );
    }

    @Override
    public String xml() throws Exception {
        return new PostRequest(
            this.url("&xpath=hudson/job&wrapper=jobs"), this.creds.headers()
        ).execute();
    }

    /**
     * Create full Jenkins API URL.
     * @param path URL path.
     * @return URL string.
     */
    private String url(final String path) {
        return String.format("%s%s", this.api, path);
    }

    /**
     * Parse jobs from XML.
     *
     * @param xml XML string.
     * @return Parsed job names.
     * @throws Exception If something goes wrong.
     */
    private static List<String> parseJobs(final String xml) throws Exception {
        final List<String> jobs = new XMLDocument(xml).xpath(
            "//job/displayName/text()"
        );
        Collections.sort(jobs, String.CASE_INSENSITIVE_ORDER);
        return jobs;
    }
}
