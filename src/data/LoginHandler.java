package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginHandler {

    private static Properties accountProperties;

    public LoginHandler() {
        setupProperties();
    }

    public String getUsername() {
        return accountProperties.getProperty("username");
    }

    public String getPassword() {
        return accountProperties.getProperty("password");
    }

    public String getBankPin() {
        return accountProperties.getProperty("bank_pin");
    }

    public String getClientType() { //desktop/mobile/rsps
        return accountProperties.getProperty("client_type");
    }

    public String getClientName() { //"Old School RuneScape" or "BlueStacks" etc
        return accountProperties.getProperty("client_name");
    }

    private void setupProperties() {

        accountProperties = new Properties();
        try {
            InputStream input = new FileInputStream("./autocyan.properties");
            accountProperties.load(input);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
