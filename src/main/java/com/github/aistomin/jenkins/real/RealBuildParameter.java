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

import com.github.aistomin.jenkins.BuildParameter;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins build parameter.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class RealBuildParameter implements BuildParameter {

    /**
     * Build Parameter's value.
     *
     * @return Build parameter's value.
     * @todo: Let's implement this method and solve issue #281.
     */
    public String value() {
        throw new NotImplementedException(
            String.format(
                "%s.value() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Build parameter's name.
     *
     * @return Build parameter's name.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve issue #281.
     */
    public String name() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.name() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Build parameter's type.
     *
     * @return Build parameter's type.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve issue #281.
     */
    public String type() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.type() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Build parameter's description.
     *
     * @return Build parameter's description.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve issue #281.
     */
    public String description() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.description() is not implemented.", this.getClass()
            )
        );
    }
}
