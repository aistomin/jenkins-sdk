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
package org.rising.jenkins.real;

import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.Jenkins;
import org.rising.jenkins.Jobs;

/**
 * Jenkins instance.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealJenkins implements Jenkins {

    /**
     * All the jobs of this Jenkins instance.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     * @todo: Let's implement this method and solve Issue #11.
     */
    public Jobs jobs() throws Exception {
        throw new NotImplementedException(
            String.format(
                "The method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }
}
