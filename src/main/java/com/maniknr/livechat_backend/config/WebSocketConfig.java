package com.maniknr.livechat_backend.config; // 1. The package

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// 2. The Class-Level Annotations
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // 3. The Implementation

    // 4. Method 1: The "Front Door" (Client Connection Endpoint)
    /**
     * Registers the STOMP endpoints, mapping each to a specific URL and
     * enabling SockJS fallback options.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registers the "/ws" endpoint, enabling SockJS fallback options.
        // SockJS is used if the browser doesn't support WebSocket.
        // .setAllowedOriginPatterns("*") allows all origins to connect.
        // For production, you should restrict this to your frontend's domain.
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
    // 5. Method 2: The "Post Office" (Message Routing)
    /**
     * Configures the message broker.
    */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Sets the application destination prefix.
        // Messages sent from clients to destinations starting with "/app"
        // will be routed to @MessageMapping methods in @Controller classes.
        registry.setApplicationDestinationPrefixes("/app");

        // Enables a simple in-memory message broker.
        // Messages will be routed to clients subscribed to destinations
        // starting with "/topic".
        registry.enableSimpleBroker("/topic");
    }
}

