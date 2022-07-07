package com.example;

import com.templars_server.util.mqtt.MBMqttClient;
import com.templars_server.util.settings.Settings;
import generated.*;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, MqttException {
        LOG.info("======== Starting mb2-plugin-template ========");
        LOG.info("Loading settings");
        Settings settings = new Settings();
        settings.load("application.properties");

        LOG.info("Reading properties");
        String uri = "tcp://localhost:" + settings.getInt("mqtt.port");
        String topic = settings.get("mqtt.topic");

        LOG.info("Registering event callbacks");
        MBMqttClient client = new MBMqttClient();
        client.putEventListener(Application::onClientBeginEvent, ClientBeginEvent.class);
        client.putEventListener(Application::onClientConnectEvent, ClientConnectEvent.class);
        client.putEventListener(Application::onClientDisconnectEvent, ClientDisconnectEvent.class);
        client.putEventListener(Application::onClientSpawnedEvent, ClientSpawnedEvent.class);
        client.putEventListener(Application::onClientUserinfoChangedEvent, ClientUserinfoChangedEvent.class);
        client.putEventListener(Application::onInitGameEvent, InitGameEvent.class);
        client.putEventListener(Application::onKillEvent, KillEvent.class);
        client.putEventListener(Application::onSayEvent, SayEvent.class);
        client.putEventListener(Application::onAdminSayEvent, AdminSayEvent.class);
        client.putEventListener(Application::onServerInitializationEvent, ServerInitializationEvent.class);
        client.putEventListener(Application::onSendingGameReportEvent, SendingGameReportEvent.class);
        client.putEventListener(Application::onShutdownGameEvent, ShutdownGameEvent.class);
        client.putEventListener(Application::onFragLimitHitEvent, FragLimitHitEvent.class);

        LOG.info("Connecting to MQTT broker");
        client.connect(uri, topic);
    }

    static void onClientBeginEvent(ClientBeginEvent event) {
        LOG.info("onClientBeginEvent");
    }

    static void onClientConnectEvent(ClientConnectEvent event) {
        LOG.info("onClientConnectEvent");
    }

    static void onClientDisconnectEvent(ClientDisconnectEvent event) {
        LOG.info("onClientDisconnectEvent");
    }

    static void onClientSpawnedEvent(ClientSpawnedEvent event) {
        LOG.info("onClientSpawnedEvent");
    }

    static void onClientUserinfoChangedEvent(ClientUserinfoChangedEvent event) {
        LOG.info("onClientUserinfoChangedEvent");
    }

    static void onInitGameEvent(InitGameEvent event) {
        LOG.info("onInitGameEvent");
    }

    static void onKillEvent(KillEvent event) {
        LOG.info("onKillEvent");
    }

    static void onSayEvent(SayEvent event) {
        LOG.info("onSayEvent");
    }

    static void onAdminSayEvent(AdminSayEvent event) {
        LOG.info("onAdminSayEvent");
    }

    static void onServerInitializationEvent(ServerInitializationEvent event) {
        LOG.info("onServerInitializationEvent");
    }

    static void onSendingGameReportEvent(SendingGameReportEvent event) {
        LOG.info("onSendingGameReportEvent");
    }

    static void onShutdownGameEvent(ShutdownGameEvent event) {
        LOG.info("onShutdownGameEvent");
    }

    static void onFragLimitHitEvent(FragLimitHitEvent event) {
        LOG.info("onFragLimitHitEvent");
    }

}
