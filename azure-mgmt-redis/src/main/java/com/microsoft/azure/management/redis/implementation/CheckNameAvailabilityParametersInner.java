/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.redis.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parameters body to pass for  name availability check.
 */
public class CheckNameAvailabilityParametersInner {
    /**
     * Resource name.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * Resource type.
     */
    @JsonProperty(value = "type")
    private String type;

    /**
     * Get the name value.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name value.
     *
     * @param name the name value to set
     * @return the CheckNameAvailabilityParametersInner object itself.
     */
    public CheckNameAvailabilityParametersInner withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the type value.
     *
     * @return the type value
     */
    public String type() {
        return this.type;
    }

    /**
     * Set the type value.
     *
     * @param type the type value to set
     * @return the CheckNameAvailabilityParametersInner object itself.
     */
    public CheckNameAvailabilityParametersInner withType(String type) {
        this.type = type;
        return this;
    }

}