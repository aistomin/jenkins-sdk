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

import org.apache.commons.lang3.NotImplementedException;
import org.rising.jenkins.Users;

/**
 * Fake Jenkins' users for tests.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeUsers implements Users {

    /**
     * Return XML content that was set in ctor.
     * @return XML string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #18.
     */
    public String xml() throws Exception {
        throw new NotImplementedException(
            String.format(
                "Method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }
}
