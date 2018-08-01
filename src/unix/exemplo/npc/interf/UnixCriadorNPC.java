package unix.exemplo.npc.interf;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

public interface UnixCriadorNPC {

	public int CalcularMathHelper(double valor);
	public void Aparecer(Player jogador, int entityID, UUID uuid ,GameProfile gameprofile, int x, int y, int z, byte Yam, byte Pitch);
	public void TabList(Player jogador, int entityID, GameProfile gameprofile, boolean remover);
}
