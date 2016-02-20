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
 * Jenkins' job's builds.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Builds extends ApiObject {

    /**
     * Builds iterator.
     *
     * @return Builds iterator.
     * @throws Exception If something goes wrong.
     */
    Iterator<Build> iterator() throws Exception;

    /**
     * Last successful build.
     *
     * @return Last successful build.
     * @throws Exception If something goes wrong.
     */
    Build lastSuccessful() throws Exception;

    /**
     * Last failed build.
     *
     * @return Last failed build.
     * @throws Exception If something goes wrong.
     */
    Build lastFailed() throws Exception;

    /**
     * Last stable build.
     *
     * @return Last stable build.
     * @throws Exception If something goes wrong.
     */
    Build lastStable() throws Exception;

    /**
     * Last unstable build.
     *
     * @return Last unstable build.
     * @throws Exception If something goes wrong.
     */
    Build lastUnstable() throws Exception;

    /**
     * Find build by number.
     *
     * @param number Build's number.
     * @return Build.
     * @throws Exception If something goes wrong.
     */
    Build find(String number) throws Exception;
}
