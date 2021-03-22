package testEncrypt;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,String> param = new HashMap<>();
	    param.put("mode","enc");
	    param.put("key","0");
	    param.put("data",null);
	    param.put("in",null);
	    param.put("out",null);
	    param.put("alg","shift");
        for (int i=0;i< args.length;i++){
            switch (args[i]){
                case "-mode":if(ifKeyNull(args[i+1])){break;}
                param.put("mode",args[i+1]);break;
                case "-in":if(ifKeyNull(args[i+1])){break;}
                param.put("in",args[i+1]);break;
                case "-out":if(ifKeyNull(args[i+1])){break;}
                param.put("out",args[i+1]);break;
                case "-key":if(ifKeyNull(args[i+1])){break;}
                try {
                    int key = Integer.parseInt(args[i+1]);
                    if (key<0 && key%1==0){key=-1*key;}else{
                        if (key%1!=0){break;}
                    }
                    String keyS = Integer.toString(key);
                    param.put("key",keyS);
                    break;
                }catch (NumberFormatException e){break;}
                case "-alg":if(ifKeyNull(args[i+1])){break;}
                param.put("alg",args[i+1]);break;
                case "-data":if(ifKeyNull(args[i+1])){break;}
                param.put("data",args[i+1]);break;
            }
        }
        if (param.get("mode").equals("dec") && Integer.parseInt(param.get("key")) !=0){
            try {
                int key = Integer.parseInt(param.get("key"));
                key=-1*key;
                String keyS = Integer.toString(key);
                param.put("key",keyS);
            }catch (NumberFormatException e){
                System.out.println("Параметр ключ - ошибка");
            }
        }

        Cryptography.action((HashMap<String, String>) param);
    }
    // проверка на отсутствие параметра
    public static boolean ifKeyNull(String nextArgs){
        if (nextArgs.equals("-mode")||nextArgs.equals("-in")||nextArgs.equals("-out")||nextArgs.equals("-key")
        ||nextArgs.equals("-alg")||nextArgs.equals("-data")){return true;}
        return false;
    }

}
