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

import com.github.aistomin.jenkins.JobParameter;

/**
 * Jenkins job parameter.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJobParameter implements JobParameter {

    /**
     * Parameter's name.
     */
    private final transient String parameter;

    /**
     * Parameter's type.
     */
    private final transient String clazz;

    /**
     * Parameter's description.
     */
    private final transient String desc;

    /**
     * Ctor.
     * @param name Parameter's name.
     * @param type Parameter's type.
     * @param description Parameter's description.
     */
    public RealJobParameter(
        final String name, final String type, final String description
    ) {
        this.parameter = name;
        this.clazz = type;
        this.desc = description;
    }

    /**
     * Parameter's name.
     *
     * @return Parameter's name.
     */
    public String name() {
        return this.parameter;
    }

    /**
     * Parameter's type.
     *
     * @return Parameter's type.
     */
    public String type() {
        return this.clazz;
    }

    /**
     * Parameter's description.
     *
     * @return Parameter's description.
     */
    public String description() {
        return this.desc;
    }
}
