package com.mikemik44.discordchatformat;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TSChat extends JavaPlugin implements Listener {

	public static String getPattern(String pattern, String text) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group();
		}
		return "";
	}

	private static String finds(String msg, String symbolS) {
		if (msg.length() < symbolS.length()) {
			return "";
		}
		for (int i = 0; i < symbolS.length(); i++) {
			if (msg.charAt(i) != symbolS.charAt(i)) {
				return "";
			}
		}
		return symbolS;
	}

	private static String doit(String pattern, String endPattern, String msg) {
		String text = "";
		if (!finds(msg, pattern).isEmpty()) {
//			msg = msg.replace(pattern, "");
			for (int i = pattern.length(); i < msg.length(); i++) {
				if (!finds(msg.substring(i), endPattern).isEmpty()) {
					return msg.substring(pattern.length(), i);
				}
			}
		}
		return "";
	}

	public static String convert(Player p, String msg) {
		int i = 0;
		String lsym = "";
		while (i < msg.length()) {
			
			String te = doit("__", "__", msg.substring(i));
			if (!te.isEmpty()) {
				msg = msg.replace("__" + te + "__", convert(p,lsym + "§n",te) + "§r");
				continue;
			}
			te = doit("***", "***", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("***" + te + "***", convert(p, lsym +"§l§o",te) + "§r");
				continue;
			}
			te = doit("**", "**", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("**" + te + "**", convert(p, lsym +"§l",te) + "§r");
				continue;
			}
			te = doit("*", "*", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("*" + te + "*", convert(p,lsym +"§o",te) + "§r");
				continue;
			}
			te = doit("||", "||", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("||" + te + "||", convert(p, lsym +"§k",te) + "§r");
				continue;
			}
			te = doit("~~", "~~", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("~~" + te + "~~", convert(p,lsym +"§m", te)  + "§r");
				continue;
			}
			i++;
		}
		return msg;
	}

	public static String convert(Player p, String prevSigns, String msg) {
		int i = 0;
		String lsym = prevSigns;
		while (i < msg.length()) {
			
			String te = doit("__", "__", msg.substring(i));
			if (!te.isEmpty()) {
				msg = msg.replace("__" + te + "__", convert(p,lsym + "§n",te) + "§r");
				continue;
			}
			te = doit("***", "***", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("***" + te + "***", convert(p, lsym +"§l§o",te) + "§r");
				continue;
			}
			
			te = doit("**", "**", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("**" + te + "**", convert(p, lsym +"§l",te) + "§r");
				continue;
			}
			te = doit("*", "*", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("*" + te + "*", convert(p,lsym +"§o",te) + "§r");
				continue;
			}
			te = doit("||", "||", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("||" + te + "||", convert(p, lsym +"§k",te) + "§r");
				continue;
			}
			te = doit("~~", "~~", msg.substring(i));
			if (te.isEmpty()) {
			} else {
				msg = msg.replace("~~" + te + "~~", convert(p,lsym +"§m", te)  + "§r");
				continue;
			}
			i++;
		}
		return lsym + msg;
	}

	public static void main(String[] args) {
		System.out.println(convert(null, "__wow__"));
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		e.setMessage(convert(e.getPlayer(), e.getMessage()));
	}

}
