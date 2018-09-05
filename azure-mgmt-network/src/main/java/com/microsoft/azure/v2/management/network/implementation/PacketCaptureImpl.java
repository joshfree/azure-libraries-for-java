/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */
package com.microsoft.azure.v2.management.network.implementation;

import com.microsoft.azure.management.apigeneration.LangDefinition;
import com.microsoft.azure.v2.management.network.NetworkWatcher;
import com.microsoft.azure.v2.management.network.PCFilter;
import com.microsoft.azure.v2.management.network.PacketCapture;
import com.microsoft.azure.v2.management.network.PacketCaptureFilter;
import com.microsoft.azure.v2.management.network.PacketCaptureStatus;
import com.microsoft.azure.v2.management.network.PacketCaptureStorageLocation;
import com.microsoft.azure.v2.management.network.ProvisioningState;
import com.microsoft.azure.v2.management.resources.fluentcore.model.implementation.CreatableUpdatableImpl;
import com.microsoft.azure.v2.management.resources.fluentcore.utils.Utils;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for Packet Capture and its create and update interfaces.
 */
@LangDefinition
public class PacketCaptureImpl extends
        CreatableUpdatableImpl<PacketCapture, PacketCaptureResultInner, PacketCaptureImpl>
    implements
        PacketCapture,
        PacketCapture.Definition {
    private final PacketCapturesInner client;
    private final PacketCaptureInner createParameters;
    private final NetworkWatcher parent;

    PacketCaptureImpl(String name, NetworkWatcherImpl parent, PacketCaptureResultInner innerObject,
                                PacketCapturesInner client) {
        super(name, innerObject);
        this.client = client;
        this.parent = parent;
        this.createParameters = new PacketCaptureInner();
    }

    @Override
    protected Maybe<PacketCaptureResultInner> getInnerAsync() {
        return this.client.getAsync(parent.resourceGroupName(), parent.name(), name());
    }

    @Override
    public void stop() {
        stopAsync().blockingAwait();
    }

    @Override
    public Completable stopAsync() {
        return this.client.stopAsync(parent.resourceGroupName(), parent.name(), name());
    }

    @Override
    public PacketCaptureStatus getStatus() {
        return getStatusAsync().blockingLast();
    }

    @Override
    public Observable<PacketCaptureStatus> getStatusAsync() {
        return this.client.getStatusAsync(parent.resourceGroupName(), parent.name(), name())
                .map(inner -> (PacketCaptureStatus) new PacketCaptureStatusImpl(inner))
                .toObservable();
    }

    @Override
    public PacketCaptureImpl withTarget(String target) {
        createParameters.withTarget(target);
        return this;
    }

    @Override
    public PacketCaptureImpl withStorageAccountId(String storageId) {
        PacketCaptureStorageLocation storageLocation = createParameters.storageLocation();
        if (storageLocation == null) {
            storageLocation = new PacketCaptureStorageLocation();
        }
        createParameters.withStorageLocation(storageLocation.withStorageId(storageId));
        return this;
    }

    @Override
    public DefinitionStages.WithCreate withStoragePath(String storagePath) {
        createParameters.storageLocation().withStoragePath(storagePath);
        return this;
    }

    @Override
    public PacketCaptureImpl withFilePath(String filePath) {
        PacketCaptureStorageLocation storageLocation = createParameters.storageLocation();
        if (storageLocation == null) {
            storageLocation = new PacketCaptureStorageLocation();
        }
        createParameters.withStorageLocation(storageLocation.withFilePath(filePath));
        return this;
    }

    @Override
    public PacketCaptureImpl withBytesToCapturePerPacket(int bytesToCapturePerPacket) {
        createParameters.withBytesToCapturePerPacket(bytesToCapturePerPacket);
        return this;
    }

    @Override
    public PacketCaptureImpl withTotalBytesPerSession(int totalBytesPerSession) {
        createParameters.withTotalBytesPerSession(totalBytesPerSession);
        return this;
    }

    @Override
    public PacketCaptureImpl withTimeLimitInSeconds(int timeLimitInSeconds) {
        createParameters.withTimeLimitInSeconds(timeLimitInSeconds);
        return this;
    }

    @Override
    public PCFilter.Definition<DefinitionStages.WithCreate> definePacketCaptureFilter() {
        return new PCFilterImpl(new PacketCaptureFilter(),  this);
    }

    void attachPCFilter(PCFilterImpl pcFilter) {
        if (createParameters.filters() == null) {
            createParameters.withFilters(new ArrayList<PacketCaptureFilter>());
        }
        createParameters.filters().add(pcFilter.inner());
    }

    @Override
    public boolean isInCreateMode() {
        return this.inner().id() == null;
    }

    @Override
    public Observable<PacketCapture> createResourceAsync() {
        return this.client.createAsync(parent.resourceGroupName(), parent.name(), this.name(), createParameters)
                .map(innerToFluentMap(this))
                .toObservable();
    }

    @Override
    public String id() {
        return inner().id();
    }

    @Override
    public String targetId() {
        return inner().target();
    }

    @Override
    public int bytesToCapturePerPacket() {
        return Utils.toPrimitiveInt(inner().bytesToCapturePerPacket());
    }

    @Override
    public int totalBytesPerSession() {
        return Utils.toPrimitiveInt(inner().totalBytesPerSession());
    }

    @Override
    public int timeLimitInSeconds() {
        return Utils.toPrimitiveInt(inner().timeLimitInSeconds());
    }

    @Override
    public PacketCaptureStorageLocation storageLocation() {
        return inner().storageLocation();
    }

    @Override
    public List<PacketCaptureFilter> filters() {
        return inner().filters();
    }

    @Override
    public ProvisioningState provisioningState() {
        return inner().provisioningState();
    }
}
