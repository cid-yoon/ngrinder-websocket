package com.cid.util;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class TWSClient
{
    protected Session conn = null;

    public Boolean connect(String url)
    {
        try {
            conn = ContainerProvider.getWebSocketContainer().connectToServer(this, new URI(url));
            conn.setMaxTextMessageBufferSize(16777216);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("connected");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("disconnected");
    }

    public void disconnect() {
        try {
            if (conn.isOpen()) {
                conn.close();
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}