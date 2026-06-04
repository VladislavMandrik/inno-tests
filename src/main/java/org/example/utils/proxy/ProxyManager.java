package org.example.utils.proxy;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;
import org.example.config.Constants;

import java.util.EnumSet;

public final class ProxyManager {

    private static volatile BrowserMobProxy proxy;
    private static final Object lock = new Object();

    private ProxyManager() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static void start() {
        if (proxy != null) {
            return;
        }

        synchronized (lock) {
            if (proxy != null) {
                return;
            }

            proxy = new BrowserMobProxyServer();
            proxy.setTrustAllServers(true);
            proxy.setHarCaptureTypes(EnumSet.of(
                    CaptureType.REQUEST_HEADERS,
                    CaptureType.RESPONSE_HEADERS
            ));
            proxy.start(0);
        }
    }

    public static BrowserMobProxy getProxyInstance() {
        return proxy;
    }

    public static void stop() {
        synchronized (lock) {
            if (proxy != null) {
                try {
                    proxy.stop();
                } finally {
                    proxy = null;
                }
            }
        }
    }
}