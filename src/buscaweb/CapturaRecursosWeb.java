/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

/**
 *
 * @author Santiago
 */
public class CapturaRecursosWeb {
    private ArrayList<String> listaRecursos = new ArrayList();
    

    public ArrayList<String> carregarRecursos(){
        ArrayList<String> resultado = new ArrayList();
        for (String stringURL: listaRecursos){
            String resposta = "";

            try {
                URL url = new URL(stringURL);
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                connection.setRequestProperty("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        connection.getInputStream(), "UTF-8"));

                String inputLine;

                StringBuffer sb = new StringBuffer();
                while ((inputLine = in.readLine()) != null) sb.append(inputLine+"\n");
                resposta = sb.toString();
                resultado.add(resposta);
                in.close();
            } catch (MalformedURLException ex) {
                resultado.add("");
                ex.printStackTrace();
            } catch (IOException ex) {
                resultado.add("");
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    /**
     * @return the listaRecursos
     */
    public ArrayList<String> getListaRecursos() {
        return listaRecursos;
    }
}
