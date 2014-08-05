package com.weidays.parentsbank.server.common.util;

import java.util.HashMap;
import java.util.List;

public class MenuUtil {

	private MenuUtil(){
		
	}
	
	public  static String getUserMenu(List menuList) {	
		String str_menu = "";
		for (int i = 0; i < menuList.size(); i++) {
			HashMap temp = (HashMap)menuList.get(i);
			
			String no_icon = "\'\'";
			String icon = (String) temp.get("ICON_NAME");
			if (icon.equals("-"))
			{
				icon = no_icon;
			}

			str_menu = str_menu+"d.add("+temp.get("PRIVILEGE_ID")+","
				+ temp.get("PARENT_PRIVILEGEID")+",'"+temp.get("PRIVILEGE_NAME")
				+ "','javascript:openMenu(" + temp.get("URL")+","+temp.get("TITLE")+
				")','" + temp.get("PRIVILEGE_NAME")+"',"+no_icon+","+icon+"," +icon +");";
		}

		return str_menu;
	}
}
