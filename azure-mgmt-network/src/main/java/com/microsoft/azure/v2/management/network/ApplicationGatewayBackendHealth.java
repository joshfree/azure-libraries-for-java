/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.v2.management.network;

import java.util.Map;

import com.microsoft.azure.management.apigeneration.Fluent;
import com.microsoft.azure.v2.management.network.implementation.ApplicationGatewayBackendHealthPoolInner;
import com.microsoft.azure.v2.management.resources.fluentcore.arm.models.HasName;
import com.microsoft.azure.v2.management.resources.fluentcore.arm.models.HasParent;
import com.microsoft.azure.v2.management.resources.fluentcore.model.HasInner;
import com.microsoft.rest.v2.annotations.Beta;

/**
 * A client-side representation of the health information of an application gateway backend.
 */
@Fluent
@Beta(since = "V1_4_0")
public interface ApplicationGatewayBackendHealth extends
    HasInner<ApplicationGatewayBackendHealthPoolInner>,
    HasName,
    HasParent<ApplicationGateway> {

    /**
     * @return the application gateway backend address pool that is health information pertains to
     */
    ApplicationGatewayBackend backend();

    /**
     * @return the health information about each associated backend HTTP settings configuration, indexed by its name
     */
    Map<String, ApplicationGatewayBackendHttpConfigurationHealth> httpConfigurationHealths();
}