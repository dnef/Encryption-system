package testEncrypt;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cryptography {
    static void action(HashMap<String,String> param){
        String str="";
        String outData="";
        if (param.get("data")==null && param.get("in")==null){
            param.put("data","");
        }
        if (param.get("data")!=null){
            str=param.get("data");
            if (param.get("alg").equals("unicode")){
                //при параметре unicode
                outData=unicodeCrypto(str,Integer.parseInt(param.get("key")));
                printData(outData, param.get("out"));
            }else{
                //при параметре shift
                outData=shiftCrypto(str,Integer.parseInt(param.get("key")));
                printData(outData,param.get("out"));
            }
        }else {
            //BufferedReader с существующего FileReader для построчного считывания
            try {
                //удаление\создание файла параметр:-out
                File outFile = new File(param.get("out"));
                if(outFile.exists()){
                outFile.delete();
                outFile.createNewFile();
                }
                //считывание файла параметр: -in
                File file = new File(param.get("in"));
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                str = reader.readLine();
                while (str != null) {
                    if (param.get("alg").equals("unicode")){
                        outData=unicodeCrypto(str,Integer.parseInt(param.get("key")));
                        printData(outData,param.get("out"));
                    }else{
                        outData=shiftCrypto(str,Integer.parseInt(param.get("key")));
                        printData(outData,param.get("out"));
                    }
                    str = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //Сдвиг по английскому алфавиту (SHIFT)
    private static String shiftCrypto(String str,int key) {
        String rez="";
        String sim;
        char[] strArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            strArr[i] = (char) str.charAt(i);
            sim = Character.toString((char) strArr[i]);
            Pattern patlatletter = Pattern.compile("[a-zA-Z]{1}");
            Matcher english = patlatletter.matcher(sim);
            // если символы английского алфавита
            if (english.matches()) {
                if ((strArr[i] >= 'A') && (strArr[i] <= 'Z')) {
                    strArr[i] += key;
                    if (strArr[i] > 'Z') strArr[i] -= 26;
                    if (strArr[i] < 'A') strArr[i] += 26;
                }
                if ((strArr[i] >= 'a') && (strArr[i] <= 'z')) {
                    strArr[i] += key;
                    if (strArr[i] > 'z') strArr[i] -= 26;
                    if (strArr[i] < 'a') strArr[i] += 26;
                }
            }
        }
        rez = String.valueOf(strArr);
        return rez;
    }
    //Сдвиг по таблице ASCII(UNICODE);
    private static String unicodeCrypto(String str,int key) {
        byte[] byteArray = str.getBytes();
        for (int i=0;i<byteArray.length;i++){
            byteArray[i] = (byte) (byteArray[i] + key);
        }
        String rez = new String(byteArray);
        return rez;
    }
    // Вывод данных
    private static void printData(String outData,String out) {
            if (out==null){
            System.out.println(outData);
            }else{
                try(FileWriter writer = new FileWriter(out,true)){
                    writer.write(outData+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
