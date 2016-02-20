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
package com.github.aistomin.jenkins;

import java.util.Iterator;

/**
 * Jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Job extends ApiObject1 {

    /**
     * Job name.
     *
     * @return Job name.
     * @throws Exception If something goes wrong.
     */
    String name() throws Exception;

    /**
     * Job details.
     *
     * @return Job details.
     * @throws Exception If something goes wrong.
     */
    JobDetails details() throws Exception;

    /**
     * Job URL.
     *
     * @return URL string.
     * @throws Exception If something goes wrong.
     */
    String url() throws Exception;

    /**
     * Job builds.
     *
     * @return Builds.
     * @throws Exception If reading builds is not successful.
     */
    Builds builds() throws Exception;

    /**
     * Job parameters.
     *
     * @return Job's parameters.
     * @throws Exception If something goes wrong.
     */
    Iterator<JobParameter> parameters() throws Exception;
}
