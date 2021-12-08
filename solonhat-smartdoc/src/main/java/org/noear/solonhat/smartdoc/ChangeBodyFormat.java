package org.noear.solonhat.smartdoc;

public class ChangeBodyFormat {

    public static String urlParamToJson(String p){
        if (p==null){
            return "";
        }
        StringBuffer stringBuffer=new StringBuffer();
        String[] split = p.split("&");
        stringBuffer.append("{");
        boolean b=false;
        for (String s : split) {

            String[] split1 = s.split("=");
            stringBuffer.append("\""+split1[0]+"\":\""+(split1.length>1?split1[1]:"")+"\",");
            b=true;
        }
        if (b) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
