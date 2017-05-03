/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 * @author jgonzalezc
 */
public class FCom {
    private static boolean debug = false;
    public static void printDebug(String error) {
        if (debug)
            System.out.println(error);
    }
    
    
    public static String ToFilterOperator(String operator,String campo,String valor)
    {
        if (operator.toLowerCase().equals("eq")){
            if(valor.equals("true") || valor.equals("false"))
                return campo+" = "+valor;
            else if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') = '"+valor+"'";
            else
                return campo+" = '"+valor+"'";
        }else if(operator.toLowerCase().equals("neq")){            
            if(valor.equals("true") || valor.equals("false"))
                return campo+" != "+valor;
            else if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') != '"+valor+"'";
            else
                return campo+" != '"+valor+"'";
        }else if(operator.toLowerCase().equals("gte"))
            if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') >= '"+valor+"'";
            else
                return campo+" >= '"+valor+"'";
        else if(operator.toLowerCase().equals("gt"))
            if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') > '"+valor+"'";
            else
                return campo+" > '"+valor+"'";
        else if(operator.toLowerCase().equals("lte"))
            if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') <= '"+valor+"'";
            else
                return campo+" <= '"+valor+"'";
        else if(operator.toLowerCase().equals("lt"))
            if(valor.matches("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"))
                return "DATE_FORMAT("+campo+",'%Y-%m-%d') < '"+valor+"'";
            else
                return campo+" < '"+valor+"'";
        else if(operator.toLowerCase().equals("startswith"))
            return campo+" like '"+valor+"%'";
        else if(operator.toLowerCase().equals("endswith"))
            return campo+" like '%"+valor+"'";
        else if(operator.toLowerCase().equals("contains"))
            return campo+" like '%"+valor+"%'";
        else if(operator.toLowerCase().equals("doesnotcontain"))
            return campo+" not like '%"+valor+"%'";
        else if(operator.toLowerCase().equals("isnull"))
            return campo+" is null";
        else if(operator.toLowerCase().equals("nisnull"))
            return campo+" is not null";
        else if(operator.toLowerCase().equals("isempty"))
            return campo+"=''";
        else if(operator.toLowerCase().equals("nisempty"))
            return campo+"!=''";
        else if(operator.toLowerCase().equals("in"))
            return campo+" in ("+valor+")";
        else
            return "";
    }

    public static String completarIzquierda(String str, int size,String caracter){
        String temp="";
        for (int i=(size-str.length());i>0;--i )
            temp=temp+caracter;
        return temp+str;
    }
    public static String completarDerecha(String str, int size,String caracter){
        String temp="";
        for (int i=(size-str.length());i>0;--i )
            temp=temp+caracter;
        return str+temp;
    }
    
    public static String encrypt(String cadena,String cadenaEncriptacion) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(cadenaEncriptacion); 
        return s.encrypt(cadena); 
    } 
    
    public static String decrypt(String cadena,String cadenaEncriptacion) { 
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor(); 
        s.setPassword(cadenaEncriptacion); 
        String devuelve = ""; 
        try { 
            devuelve = s.decrypt(cadena); 
        } catch (Exception e) { 
            System.out.print(e.getMessage());
        } 
        return devuelve; 
    }
    /**
     * Sirve para limpiar datos de sql injection
     * @param cadena Cadena a escribir en consola
     */
    public static String injectionFree(String cadena) {
        cadena = cadena.replaceAll("'", "");
        cadena = cadena.replaceAll("\"", "");
        cadena = cadena.replaceAll("--", "");
        return cadena;
    }
}
