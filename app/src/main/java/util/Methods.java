package util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by coderzlab on 16/7/15.
 */
public class Methods {




    public static boolean saveserviceCBID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("serviceCBID",cif).commit();
    }
    public static String getserviceCBID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("serviceCBID", null);
    }

    public static boolean saveEmployeeAPIid(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("EmployeeAPIid",cif).commit();
    }
    public static String getEmployeeAPIid(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("EmployeeAPIid", null);
    }

    public static boolean saveLocationText(Context context, String LocationText){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("LocationText",LocationText).commit();
    }
    public static String getLocationText(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("LocationText", null);
    }

    public static boolean saveGENDER(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("GENDER",cif).commit();
    }
    public static String getGENDER(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("GENDER", null);
    }

    public static boolean saveBOARDNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("BOARDNAME",cif).commit();
    }
    public static String getBOARDNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("BOARDNAME", null);
    }
















    public static boolean saveMOTHERNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MOTHERNAME",cif).commit();
    }
    public static String getMOTHERNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MOTHERNAME", null);
    }


    public static boolean saveFATHERNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("FATHERNAME",cif).commit();
    }
    public static String getFATHERNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("FATHERNAME", null);
    }


    public static boolean saveFATHERCONTACT(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("FATHERCONTACT",cif).commit();
    }
    public static String getFATHERCONTACT(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("FATHERCONTACT", null);
    }



    public static boolean saveMOTHERCONTACT(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MOTHERCONTACT",cif).commit();
    }
    public static String getMOTHERCONTACT(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MOTHERCONTACT", null);
    }


    public static boolean saveREGISNO(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("saveREGISNO",cif).commit();
    }
    public static String getREGISNO(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("saveREGISNO", null);
    }





    public static boolean saveSTUDENTID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("saveSTUDENTID",cif).commit();
    }
    public static String getSTUDENTID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("saveSTUDENTID", null);
    }





    public static boolean saveCOUNT(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("COUNT",cif).commit();
    }
    public static String getCOUNT(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("COUNT", null);
    }



    public static boolean saveNAME(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("NAME",cif).commit();
    }
    public static String getNAME(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("NAME", null);
    }

    public static boolean saveMOBILE(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("MOBILE",cif).commit();
    }
    public static String getMOBILE(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("MOBILE", null);
    }
    public static boolean saveUSERID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("userid",cif).commit();
    }
    public static String getUSERID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("userid", null);
    }







    public static boolean saveEMAIL(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("EMAIL",cif).commit();
    }
    public static String getEMAIL(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("EMAIL", null);
    }







    public static boolean saveCITY(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("CITY",cif).commit();
    }
    public static String getCITY(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("CITY", null);
    }


    public static boolean saveADDRESS1(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("ADDRESS1",cif).commit();
    }
    public static String getADDRESS1(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("ADDRESS1", null);
    }


    public static boolean saveSTATE(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("STATE",cif).commit();
    }
    public static String getSTATE(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("STATE", null);
    }


    public static boolean savePIN(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("PIN",cif).commit();
    }
    public static String getPIN(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("PIN", null);
    }







    public static boolean saveDID(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("ID",cif).commit();
    }
    public static String getDID(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("ID", null);
    }





    public static boolean saveDMOBILE(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("DMOBILE",cif).commit();
    }
    public static String getDMOBILE(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("DMOBILE", null);
    }
    public static boolean saveDEMAIL(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("DEMAIL",cif).commit();
    }
    public static String getDEMAIL(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("DEMAIL", null);
    }
    public static boolean saveDPASSWORD(Context context, String cif){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.edit().putString("DPASSWORD",cif).commit();
    }
    public static String getDPASSWORD(Context context){
        SharedPreferences sharedPreferences=getPreferances(context);
        return sharedPreferences.getString("DPASSWORD", null);
    }
















    public  static SharedPreferences getPreferances(Context context){
       return context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);

    }
}
