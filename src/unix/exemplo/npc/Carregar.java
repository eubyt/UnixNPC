package unix.exemplo.npc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import unix.exemplo.npc.util.UnixNPC;

public class Carregar extends JavaPlugin implements Listener {
	
	public static Plugin plugin;
	
	public void onEnable() {
		
		this.plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {
		
	}
	
	
	@EventHandler
	public void Entrar(PlayerJoinEvent event) {
		
		UnixNPC npc = new UnixNPC("UnixCF", "UnixCF");
		npc.spawn(event.getPlayer(), event.getPlayer().getLocation());
	}

}
