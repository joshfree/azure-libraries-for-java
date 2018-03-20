/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.apimanagement;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for IdentityProviderType.
 */
public final class IdentityProviderType extends ExpandableStringEnum<IdentityProviderType> {
    /** Static value facebook for IdentityProviderType. */
    public static final IdentityProviderType FACEBOOK = fromString("facebook");

    /** Static value google for IdentityProviderType. */
    public static final IdentityProviderType GOOGLE = fromString("google");

    /** Static value microsoft for IdentityProviderType. */
    public static final IdentityProviderType MICROSOFT = fromString("microsoft");

    /** Static value twitter for IdentityProviderType. */
    public static final IdentityProviderType TWITTER = fromString("twitter");

    /** Static value aad for IdentityProviderType. */
    public static final IdentityProviderType AAD = fromString("aad");

    /** Static value aadB2C for IdentityProviderType. */
    public static final IdentityProviderType AAD_B2C = fromString("aadB2C");

    /**
     * Creates or finds a IdentityProviderType from its string representation.
     * @param name a name to look for
     * @return the corresponding IdentityProviderType
     */
    @JsonCreator
    public static IdentityProviderType fromString(String name) {
        return fromString(name, IdentityProviderType.class);
    }

    /**
     * @return known IdentityProviderType values
     */
    public static Collection<IdentityProviderType> values() {
        return values(IdentityProviderType.class);
    }
}