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

import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.Builds;
import org.rising.jenkins.Job;
import org.rising.jenkins.JobDetails;
import org.rising.jenkins.JobParameter;

/**
 * Fake jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJob implements Job {

    /**
     * Fake job details.
     *
     * @return Job details.
     * @todo: Let's implement this method and solve Issue #54.
     */
    public JobDetails details() {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job URL.
     *
     * @return URL string.
     * @todo: Let's implement this method and solve Issue #55.
     */
    public String url() {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job builds.
     *
     * @return Builds.
     * @todo: Let's implement this method and solve Issue #56.
     */
    public Builds builds() {
        throw new NotImplementedException(
            String.format(
                "builds() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job parameters.
     *
     * @return Job's parameters.
     * @todo: Let's implement this method and solve Issue #57.
     */
    public List<JobParameter> parameters() {
        throw new NotImplementedException(
            String.format(
                "parameters() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #58.
     */
    public String xml() throws Exception {
        throw new NotImplementedException(
            String.format(
                "xml() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }
}
