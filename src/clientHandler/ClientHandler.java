package clientHandler;

import gui.ClientWrap;
import gui.Login;

public class ClientHandler {


    private static Login login;
    private static ClientWrap cw;

    private boolean OSRSLoaded;

    public ClientHandler() {
        setOSRSrunning();
    }

    private boolean checkOSRS() {
        return true;
    }

    private void setOSRSrunning() {
        OSRSLoaded = checkOSRS();
    }

    public boolean isOSRSLoaded() {
        return OSRSLoaded;
    }


    public void launchLogin() {
        login = new Login();
        login.loadPanel();

    }


}
