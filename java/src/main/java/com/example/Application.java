package com.example;

import com.example.mqtt.MB2MQTTClient;
import generated.*;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class Application {

    private static final String CONFIG_FILE = "application.properties";
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, MqttException {
        LOG.info("======== Starting mb2-plugin-template ========");
        LOG.info("Loading config");
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                properties.load(stream);
            }
        } else {
            try (FileOutputStream stream = new FileOutputStream(file)) {
                LOG.info("No config found, creating from default");
                properties.load(Application.class.getResourceAsStream("/" + CONFIG_FILE));
                properties.store(stream, null);
            }
        }

        LOG.info("Reading properties");
        String uri = "tcp://localhost:" + Integer.parseInt(properties.getProperty("mqtt.port"));
        String topic = properties.getProperty("mqtt.topic");

        LOG.info("Registering event callbacks");
        MB2MQTTClient client = new MB2MQTTClient();
        client.register(Application::onClientBeginEvent, ClientBeginEvent.class);
        client.register(Application::onClientConnectEvent, ClientConnectEvent.class);
        client.register(Application::onClientDisconnectEvent, ClientDisconnectEvent.class);
        client.register(Application::onClientSpawnedEvent, ClientSpawnedEvent.class);
        client.register(Application::onClientUserinfoChangedEvent, ClientUserinfoChangedEvent.class);
        client.register(Application::onInitGameEvent, InitGameEvent.class);
        client.register(Application::onKillEvent, KillEvent.class);
        client.register(Application::onSayEvent, SayEvent.class);
        client.register(Application::onShutdownGameEvent, ShutdownGameEvent.class);

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

    static void onShutdownGameEvent(ShutdownGameEvent event) {
        LOG.info("onShutdownGameEvent");
    }

}
