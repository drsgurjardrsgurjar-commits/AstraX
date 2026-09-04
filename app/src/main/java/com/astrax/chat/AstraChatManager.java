package com.astrax.chat;

import android.content.Context;
import androidx.annotation.WorkerThread;

/**
 * AstraChatManager - manages ID-to-ID chat and local radar (Wi-Fi Direct / BLE).
 * This class provides placeholders and high-level methods. Integrate with your
 * private signaling servers or local transport layers.
 *
 * All visible strings must keep AstraX branding only.
 */
public class AstraChatManager {

    private final Context ctx;

    public AstraChatManager(Context ctx) {
        this.ctx = ctx;
    }

    public void connectToId(String astraId) {
        // TODO: implement WebSocket or signal channel to AstraX infra
    }

    @WorkerThread
    public void sendMessage(String astraId, String message) {
        // TODO: send message to server or peer
    }

    public void startLocalRadar() {
        // TODO: implement Wi-Fi Direct / BLE mesh integration under the AstraX name
        // Example: discoverNearbyPeers(), connectPeer(), exchangeMessages()
    }

    public void stopLocalRadar() {
        // TODO
    }
}
