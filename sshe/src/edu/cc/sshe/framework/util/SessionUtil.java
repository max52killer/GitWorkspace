package edu.cc.sshe.framework.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.cc.sshe.framework.bean.SessionInfo;
import edu.cc.sshe.framework.bean.TreeNode;

public class SessionUtil {
	
	private static final String SESSION_INFO_KEY = "sshe_session_info";

	public static void saveSessionInfo(HttpSession session,SessionInfo sessionInfo) {
		session.setAttribute(SESSION_INFO_KEY, sessionInfo);
	}

	public static SessionInfo getSessionInfo(HttpSession session) {
		return (SessionInfo)session.getAttribute(SESSION_INFO_KEY);
		
	}
	
	public static List<TreeNode> getMenuData(HttpSession session) {
		SessionInfo info = getSessionInfo(session);
		if(info != null) {
			return info.getMenu();
		}
		return null;
	}

	public static List<String> getActions(HttpSession session) {
		SessionInfo info = getSessionInfo(session);
		if(info != null) {
			return info.getActions();
		}
		return null;
	}
}
