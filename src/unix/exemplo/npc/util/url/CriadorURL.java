package unix.exemplo.npc.util.url;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class CriadorURL {

    private HttpURLConnection web;

    public CriadorURL(String site) throws IOException {
        web = getConnection(site);

    }

    public HttpURLConnection getConexao() {
        return web;
    }



    private HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "UnixCF");

        return connection;
    }

}
