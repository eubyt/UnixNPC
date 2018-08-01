package unix.exemplo.npc.util;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import unix.exemplo.npc.modulo.ModuloNPC;
import unix.exemplo.npc.util.skin.ModuloSkin;

public abstract class SistemaUnixNPC {

	
	protected int AleatorioEntityID() { return (int)Math.ceil(Math.random() * 1000) + 2000; }
	
	protected GameProfile CriarGameProfile(String nome) { return new GameProfile(UUID.randomUUID(), nome); }
	
	protected GameProfile CriarGameProfile(String nome, String skin) {
		GameProfile gameprofile = CriarGameProfile(nome);
		gameprofile.getProperties().put("textures", new ModuloSkin().retorno(skin));
		return gameprofile;
	}
	
	protected UUID getUUID(GameProfile uuid) { return uuid.getId(); }

	protected int FixLocation(double valor){
        return ModuloNPC.npcpacket().CalcularMathHelper(valor);
    }

	protected byte FixRotation(float valor){
        return (byte) ((int) (valor * 256.0F / 360.0F));
    }
}
