package unix.exemplo.npc.util.skin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;

import unix.exemplo.npc.modulo.ModuloNPC;
import unix.exemplo.npc.modulo.ModuloNPC.SalvarSkin;
import unix.exemplo.npc.util.url.CriadorURL;

public class ModuloSkin {
	
	public String nome;
	
	public Property retorno(String nome_skin) {
		this.nome = nome_skin;
		return SetarSkin(nome_skin);
	}

	
	
	 private Property SetarSkin(String skin) {
           
		 if (!ModuloNPC.ExisteSalvarSkin(skin)) {
               String UUID = pegarUUID(skin);
               pegarSkin(UUID);
           }
           
       SalvarSkin datanpc = ModuloNPC.getSalvarSkin(skin);
       return  new Property(datanpc.name, datanpc.value, datanpc.signature);  }


   private void pegarSkin(String UUID) {

       try {
           System.out.println(UUID);
           CriadorURL conectar = new CriadorURL("https://sessionserver.mojang.com/session/minecraft/profile/" + UUID + "?unsigned=false");

           SalvarSkin salvar = new SalvarSkin();
           
           if (conectar.getConexao().getResponseCode() != 200)  {
        	   
        	   //Salvar STEVE
        	   
        	   salvar.name = "textures";
               salvar.signature = "eyJ0aW1lc3RhbXAiOjE1MzI2MzEyNDM4NzYsInByb2ZpbGVJZCI6Ijg2NjdiYTcxYjg1YTQwMDRhZjU0NDU3YTk3MzRlZWQ3IiwicHJvZmlsZU5hbWUiOiJTdGV2ZSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGMxYzc3Y2U4ZTU0OTI1YWI1ODEyNTQ0NmVjNTNiMGNkZDNkMGNhM2RiMjczZWI5MDhkNTQ4Mjc4N2VmNDAxNiJ9LCJDQVBFIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTUzY2FjOGI3NzlmZTQxMzgzZTY3NWVlMmI4NjA3MWE3MTY1OGYyMTgwZjU2ZmJjZThhYTMxNWVhNzBlMmVkNiJ9fX0";
               salvar.value = "REDt56mRD0xvQiFiuBWc86ctRjvFzeGQJHlKIWhG9UPbTjEvABWEFjvy/HQH5wY2n82F7ksI8yfRLyMuCRokfunAvL9/frYbLcv9sfb+oNkEfC3+livmowgnH7HWKahOToCqy4ylGuR5Ys+mEoHYm1iOYUbHP8UzhPydgWO/Iul/x8hM+60I6eACStFywyj6IPAJPjA1h5oJgSrCuuhy9T9q2MgHjHbag+c8r+a68Z7dP7EFdkN5ib7QxckH23KALk46QOeo0UsXOoof0vbRX7tTsUuBeKmYUUEQC83VCeMRwGPGgBx6iar9uBoVkr5h9VAuzS9RJ7hrHxMSXPn7UgzJJrWx/+VqAGFa6gnSa0+etBTIzRZgqi5ommPNIY2L29WgXQeF6Nv4tq9DkZrc+dW00cyKKepuC46jU0u7T6BGq20n2CJL8LkKJZZSQ7SF3CYk478izyI2M0U0WC+2HCpZ0Hw3BYMk9JefNbjrtu0ul4MlUkRyIME2nE9TLgJxBwmZKuaYFOY7RDDYuiFDQ5XA3ziekGG/WBVzASoDGz/LBgjnSTSjXPmMfmZnFW53vEDJ/mvs2fRGLgOBoIQg8z1bp1BFYize6mE4vdCIii0UEl0qXmnOpo5vBYs8yldB44XLbMLrOXZfn6L/OdHHO+VRh9WUiPx5wXLFKvAxhY4=";
        	   
               ModuloNPC.FazerBackupSkin(nome, salvar);
               
        	   return;        	   
           }

               BufferedReader ler = new BufferedReader(new InputStreamReader(conectar.getConexao().getInputStream()));

               JsonObject textureProperty = new JsonParser().parse(ler.readLine()).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();

               salvar.name = textureProperty.get("name").getAsString();
               salvar.signature = textureProperty.get("signature").getAsString();
               salvar.value = textureProperty.get("value").getAsString();
               
               ModuloNPC.FazerBackupSkin(nome, salvar);

               ler.close();
               conectar.getConexao().disconnect();

           



       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   
   private String pegarUUID(String nome){

       try {
           CriadorURL conectar = new CriadorURL("https://api.mojang.com/users/profiles/minecraft/" + nome);

           if (conectar.getConexao().getResponseCode() == 200) {
               BufferedReader ler = new BufferedReader(new InputStreamReader(conectar.getConexao().getInputStream()));

               String valor_uuid = new JsonParser().parse(ler.readLine()).getAsJsonObject().get("id").getAsString();

               ler.close();
               conectar.getConexao().disconnect();

               return valor_uuid;
           } else
               return "8667ba71b85a4004af54457a9734eed7";


       } catch (IOException e) {
           return "8667ba71b85a4004af54457a9734eed7";
       }

   }
}
