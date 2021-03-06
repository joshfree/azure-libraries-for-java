/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.management.storage.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.management.resources.fluentcore.model.implementation.CreatableUpdatableImpl;
import com.microsoft.azure.management.storage.ImmutabilityPolicy;
import com.microsoft.azure.management.storage.ImmutabilityPolicyState;
import rx.Observable;

@LangDefinition
class ImmutabilityPolicyImpl extends CreatableUpdatableImpl<ImmutabilityPolicy, ImmutabilityPolicyInner, ImmutabilityPolicyImpl> implements ImmutabilityPolicy, ImmutabilityPolicy.Definition, ImmutabilityPolicy.Update {
    private final StorageManager manager;
    private String resourceGroupName;
    private String accountName;
    private String containerName;
    private String cifMatch;
    private int cimmutabilityPeriodSinceCreationInDays;
    private String uifMatch;
    private int uimmutabilityPeriodSinceCreationInDays;

    ImmutabilityPolicyImpl(String name, StorageManager manager) {
        super(name, new ImmutabilityPolicyInner());
        this.manager = manager;
        // Set resource name
        this.containerName = name;
        //
    }

    ImmutabilityPolicyImpl(ImmutabilityPolicyInner inner, StorageManager manager) {
        super(inner.name(), inner);
        this.manager = manager;
        // Set resource name
        this.containerName = inner.name();
        // set resource ancestor and positional variables
        this.resourceGroupName = IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups");
        this.accountName = IdParsingUtils.getValueFromIdByName(inner.id(), "storageAccounts");
        this.containerName = IdParsingUtils.getValueFromIdByName(inner.id(), "containers");
        //
    }

    @Override
    public StorageManager manager() {
        return this.manager;
    }

    @Override
    public Observable<ImmutabilityPolicy> createResourceAsync() {
        BlobContainersInner client = this.manager().inner().blobContainers();
        return client.createOrUpdateImmutabilityPolicyAsync(this.resourceGroupName, this.accountName, this.containerName, this.cimmutabilityPeriodSinceCreationInDays, this.cifMatch)
                .map(innerToFluentMap(this));
    }

    @Override
    public Observable<ImmutabilityPolicy> updateResourceAsync() {
        BlobContainersInner client = this.manager().inner().blobContainers();
        return client.createOrUpdateImmutabilityPolicyAsync(this.resourceGroupName, this.accountName, this.containerName, this.uimmutabilityPeriodSinceCreationInDays, this.uifMatch)
                .map(innerToFluentMap(this));
    }

    @Override
    protected Observable<ImmutabilityPolicyInner> getInnerAsync() {
        BlobContainersInner client = this.manager().inner().blobContainers();
        return client.getImmutabilityPolicyAsync(this.resourceGroupName, this.accountName, this.containerName);
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }


    @Override
    public String etag() {
        return this.inner().etag();
    }

    @Override
    public String id() {
        return this.inner().id();
    }

    @Override
    public int immutabilityPeriodSinceCreationInDays() {
        return this.inner().immutabilityPeriodSinceCreationInDays();
    }

    @Override
    public String name() {
        return this.inner().name();
    }

    @Override
    public ImmutabilityPolicyState state() {
        return this.inner().state();
    }

    @Override
    public String type() {
        return this.inner().type();
    }

    @Override
    public ImmutabilityPolicyImpl withExistingContainer(String resourceGroupName, String accountName, String containerName) {
        this.resourceGroupName = resourceGroupName;
        this.accountName = accountName;
        this.containerName = containerName;
        return this;
    }

    @Override
    public ImmutabilityPolicyImpl withIfMatch(String ifMatch) {
        if (isInCreateMode()) {
            this.cifMatch = ifMatch;
        } else {
            this.uifMatch = ifMatch;
        }
        return this;
    }

    @Override
    public ImmutabilityPolicyImpl withImmutabilityPeriodSinceCreationInDays(int immutabilityPeriodSinceCreationInDays) {
        if (isInCreateMode()) {
            this.cimmutabilityPeriodSinceCreationInDays = immutabilityPeriodSinceCreationInDays;
        } else {
            this.uimmutabilityPeriodSinceCreationInDays = immutabilityPeriodSinceCreationInDays;
        }
        return this;
    }

}