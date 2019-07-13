package pl.skowrondariusz.TransportApplication.security.controller;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.rules.ExternalResource;

import javax.mail.internet.MimeMessage;

public class SmtpServerRule extends ExternalResource {

    private GreenMail smtpServer;
    private int port;

    public SmtpServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        smtpServer = new GreenMail(new ServerSetup(port, null, "smtp"));
        smtpServer.start();
//        smtpServer.waitForIncomingEmail(5000,1);
    }

    public MimeMessage[] getMessages() {
        return smtpServer.getReceivedMessages();
    }

    @Override
    protected void after() {
        super.after();
        smtpServer.stop();
    }
}