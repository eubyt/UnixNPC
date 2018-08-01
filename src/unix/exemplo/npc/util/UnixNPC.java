package unix.exemplo.npc.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import unix.exemplo.npc.interf.UnixCriadorNPC;
import unix.exemplo.npc.modulo.ModuloNPC;

public class UnixNPC extends SistemaUnixNPC {
	
	private int entityID;
	private GameProfile gameProfile;
	private UnixCriadorNPC npc;
	
	public UnixNPC(String nome, String skin) {
	   criar(nome, skin, AleatorioEntityID());
	}

	
	public UnixNPC(String nome, String skin, int entityID) {
		criar(nome, skin, entityID);
	}
	
	private void criar(String nome, String skin, int entityID) {
		this.entityID = entityID;
		this.gameProfile = CriarGameProfile(nome, skin);
		System.out.println("NOME: " + gameProfile.getName());
		System.out.println("UUID: " + gameProfile.getId());
		this.npc = ModuloNPC.npcpacket();
	}
	
	public void spawn(Player jogador, Location localizacao) {
		System.out.println("APARECE UUID: "+ gameProfile.getId());
		npc.Aparecer(jogador, entityID, getUUID(this.gameProfile), gameProfile ,FixLocation(localizacao.getX()),FixLocation(localizacao.getY()), FixLocation(localizacao.getZ()), FixRotation(localizacao.getYaw()), FixRotation(localizacao.getPitch()));
	}
	
}
