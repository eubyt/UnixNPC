package unix.exemplo.npc.modulo;

import java.util.HashMap;

import unix.exemplo.npc.interf.UnixCriadorNPC;
import unix.exemplo.npc.packet.v1_8_R3;

public class ModuloNPC {

	private static HashMap<String, SalvarSkin> backup_skin = new HashMap<>();
	private static UnixCriadorNPC npc_packet = new v1_8_R3();
	
	public static void FazerBackupSkin(String nome, SalvarSkin salvar) {
		backup_skin.put(nome, salvar);
	}
	
	public static UnixCriadorNPC npcpacket() { return npc_packet; }
	
	
	public static SalvarSkin getSalvarSkin(String nome) {
		return backup_skin.get(nome);
	}
	
	public static boolean ExisteSalvarSkin(String nome) {
		return backup_skin.containsKey(nome);
	}
	
	public static class SalvarSkin {
		
		public static String name, value, signature;
		
	}
	
}
