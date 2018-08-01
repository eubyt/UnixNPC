package unix.exemplo.npc.packet;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;
import unix.exemplo.npc.Carregar;
import unix.exemplo.npc.interf.UnixCriadorNPC;
import unix.exemplo.npc.util.UtilField;

public class v1_8_R3 extends UtilField implements UnixCriadorNPC {
	
	@Override
	public int CalcularMathHelper(double valor) { return (int)MathHelper.floor(valor * 32.0D); }


	@Override
	public void Aparecer(Player jogador, int entityID, UUID uuid, GameProfile gameprofile,int x, int y, int z, byte Yam, byte Pitch) {

		
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();

        setValor(packet, "a", entityID);
        setValor(packet, "b", uuid);
        setValor(packet, "c", x);
        setValor(packet, "d", y);
        setValor(packet, "e", z);
        setValor(packet, "f", Yam);
        setValor(packet, "g", Pitch);
        setValor(packet, "h", 0);
        
        DataWatcher w = new DataWatcher(null);
        w.a(6,(float)20);
        w.a(10,(byte)127);

        setValor(packet, "i", w);

        TabList(jogador, entityID, gameprofile, false);
        
        sendPacket(packet, jogador);
        
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(Carregar.plugin, new Runnable() {
            @Override
            public void run() {
            	TabList(jogador, entityID, gameprofile, true);
            	
            }
        },  20);
    
        
	}
	
	@Override
	public void TabList(Player jogador, int entityID, GameProfile gameprofile, boolean remover) {
		
		    PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
		    
	        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameprofile, 1, EnumGamemode.NOT_SET, CraftChatMessage.fromString("§8[NPC] " + entityID)[0]);
	       
	        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValor(packet, "b");
	        players.add(data);

	        if (remover)
	            setValor(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
	        else
	        	setValor(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
	        	
	        setValor(packet, "b", players);
	        
	        sendPacket(packet, jogador);
		
	}
	
	
	  private void sendPacket(Packet<?> packet, Player jogador){
	        ((CraftPlayer)jogador).getHandle().playerConnection.sendPacket(packet);
	    }



}
