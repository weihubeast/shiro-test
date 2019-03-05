package com.baidu.shiro.session;

import com.sun.xml.internal.ws.developer.Serialization;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * @Author: WH
 * @Date: 2019/3/5 15:54
 * @Version 1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        //获取sessionid
        Serializable sessionid = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof  WebSessionKey) {
            request = ((WebSessionKey)sessionKey).getServletRequest();
        }
        if (request != null && sessionid != null) {
            Session session = (Session) request.getAttribute(sessionid.toString());
            if (session != null) {
                return  session;
            }
        }
        //从redis中取出session
        Session session = super.retrieveSession(sessionKey);
        if (request != null && sessionid != null) {
            request.setAttribute(sessionid.toString(), session);
        }


        return  session;
    }


}
