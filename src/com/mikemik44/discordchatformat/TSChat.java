package com.mikemik44.discordchatformat;

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

	public static String convert(Player p, String msg) {
		String pat = "__\\*\\*\\*[^\\*\\*\\*__]*\\*\\*\\*__";
		String t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§n§l§o" + t.substring(5, t.length() - 5) + "§r");
			t = getPattern(pat, msg);
		}
		pat = "__\\*\\*[^\\*\\*__]*\\*\\*__";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§n§l" + t.substring(4, t.length() - 4) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "__\\*[^\\*_]*\\*__";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§n§o" + t.substring(3, t.length() - 3) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "\\|\\|[^\\|\\|]*\\|\\|";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("|", "\\|"), "§k" + t.substring(2, t.length() - 2) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "~~[^~~]*~~";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t, "§m" + t.substring(2, t.length() - 2) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "\\*\\*\\*[^\\*\\*\\*]*\\*\\*\\*";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§l§o" + t.substring(3, t.length() - 3) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "\\*\\*[^\\*\\*]*\\*\\*";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§l" + t.substring(2, t.length() - 2) + "§r");
			t = getPattern(pat, msg);

		}

		pat = "\\*[^\\*]*\\*";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§o" + t.substring(1, t.length() - 1) + "§r");
			t = getPattern(pat, msg);

		}
		pat = "_[^_]*_";
		t = getPattern(pat, msg);
		while (!t.isEmpty()) {
			msg = msg.replaceFirst(t.replace("*", "\\*"), "§n" + t.substring(1, t.length() - 1) + "§r");
			t = getPattern(pat, msg);

		}
		return msg;
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
