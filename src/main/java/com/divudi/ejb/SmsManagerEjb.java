/*
 * Open Hospital Management Information System
 * Dr M H B Ariyaratne
 * buddhika.ari@gmail.com
 */
package com.divudi.ejb;

import com.divudi.entity.Sms;
import com.divudi.entity.UserPreference;
import com.divudi.facade.EmailFacade;
import com.divudi.facade.SmsFacade;
import com.divudi.facade.UserPreferenceFacade;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.TemporalType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Buddhika
 */
@Stateless
public class SmsManagerEjb {
    @EJB
    private EmailFacade emailFacade;
    @EJB
    UserPreferenceFacade userPreferenceFacade;
    @EJB
    SmsFacade smsFacade;

    @SuppressWarnings("unused")
    @Schedule(second = "19", minute = "*/5", hour = "*", persistent = false)
    
    public void myTimer() {
        sendSmsAwaitingToSendInDatabase();
    }

    public UserPreference findApplicationPreference() {
        String jpql;
        Map m = new HashMap();
        jpql = "select p "
                + " from UserPreference p "
                + " where p.institution is null "
                + " and p.department is null "
                + " and p.webUser is null "
                + " order by p.id desc";
        UserPreference currentPreference = userPreferenceFacade.findFirstByJpql(jpql);
        if (currentPreference == null) {
            currentPreference = new UserPreference();
            userPreferenceFacade.create(currentPreference);
        }
        currentPreference.setWebUser(null);
        currentPreference.setDepartment(null);
        currentPreference.setInstitution(null);
        return currentPreference;
    }

    private void sendSmsAwaitingToSendInDatabase() {
        UserPreference pf = findApplicationPreference();
        String j = "Select e from Sms e where e.pending=true and e.retired=false and e.createdAt>:d";
        Map m = new HashMap();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        m.put("d", c.getTime());
        List<Sms> smses = getSmsFacade().findByJpql(j, m, TemporalType.DATE);
        for (Sms e : smses) {
            e.setSentSuccessfully(Boolean.TRUE);
            e.setPending(false);
            getSmsFacade().edit(e);
            boolean sent = sendSmsByApplicationPreference(e.getReceipientNumber(), e.getSendingMessage(), pf);
            e.setSentSuccessfully(sent);
            e.setSentAt(new Date());
            getSmsFacade().edit(e);
        }

    }

    public String executePost(String targetURL, Map<String, String> parameters) {
        HttpURLConnection connection = null;
        StringBuilder urlParameters = new StringBuilder();

        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                try {
                    urlParameters.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append("&");
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(SmsManagerEjb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            urlParameters.setLength(urlParameters.length() - 1); // Remove the last "&"
            targetURL += "?" + urlParameters.toString();
        }

        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            try ( DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(targetURL);
                wr.flush();
            }

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line).append('\r');
            }
            rd.close();
            return response.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public boolean sendSms(String number, String message, String username, String password, String sendingAlias) {

        //System.out.println("number = " + number);
        //System.out.println("message = " + message);
        //System.out.println("username = " + username);
        Map<String, String> m = new HashMap();
        m.put("userName", username);
        m.put("password", password);
        m.put("userAlias", sendingAlias);
        m.put("number", number);
        m.put("message", message);

        String res = executePost("http://localhost:8080/sms/faces/index.xhtml", m);
        if (res == null) {
            return false;
        } else if (res.toUpperCase().contains("200")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean sendSmsByApplicationPreference(String number, String message, UserPreference pf) {
        Map<String, String> m = new HashMap();
        m.put(pf.getSmsUsernameParameterName(), pf.getSmsUsername());
        m.put(pf.getSmsPasswordParameterName(), pf.getSmsPassword());
        if (pf.getSmsUserAliasParameterName() != null && !pf.getSmsUserAliasParameterName().trim().equals("")) {
            m.put(pf.getSmsUserAliasParameterName(), pf.getSmsUserAlias());
        }
        m.put(pf.getSmsPhoneNumberParameterName(), number);
        m.put(pf.getSmsMessageParameterName(), message);

        String res = executePost(pf.getSmsUrl(), m);
        if (res == null) {
            return false;
        } else if (res.toUpperCase().contains("200")) {
            return true;
        } else {
            return false;
        }

    }

    public SmsFacade getSmsFacade() {
        return smsFacade;
    }

}
