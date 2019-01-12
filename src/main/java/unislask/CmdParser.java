package unislask;

import java.util.Vector;

class CmdParser{

    void parse(String line){
        String[] splited = line.split(" ");
        cmd = splited[0];
        int i = 1;
        while(splited[i++].charAt(0) == '-'){
            options.add(splited[i]);
        }
        while( i < splited.length){
            values.add(splited[i++]);
        }
    }

    String cmd;
    Vector<String> values;
    Vector<String> options;
}