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

import java.util.Date;

/**
 * Jenkins' job's build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Build extends ApiObject {

    /**
     * Build's number.
     *
     * @return Build number.
     * @throws Exception If error occurred.
     */
    String number() throws Exception;

    /**
     * Build's result.
     *
     * @return Build's result.
     * @throws Exception If error occurred.
     */
    BuildResult result() throws Exception;

    /**
     * Build's date.
     *
     * @return Build's date.
     * @throws Exception If error occurred.
     */
    Date date() throws Exception;

    /**
     * Build's URL.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     */
    String url() throws Exception;

    /**
     * Build's details.
     *
     * @return Build's details.
     * @throws Exception If error occurred.
     */
    BuildDetails details() throws Exception;

    /**
     * Delete build.
     *
     * @throws Exception If error occurred.
     */
    void delete() throws Exception;
}
