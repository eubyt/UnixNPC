package unix.exemplo.npc.util;

import java.lang.reflect.Field;

public abstract class UtilField {
	
	   public void setValor(Object obj,String nome,Object valor){
	        try{
	            Field field = obj.getClass().getDeclaredField(nome);
	            field.setAccessible(true);
	            field.set(obj, valor);
	        }catch(Exception e){}
	    }



	    public Object getValor(Object obj,String nome){
	        try{
	            Field field = obj.getClass().getDeclaredField(nome);
	            field.setAccessible(true);
	            return field.get(obj);
	        }catch(Exception e){}
	        return null;
	    }

}
