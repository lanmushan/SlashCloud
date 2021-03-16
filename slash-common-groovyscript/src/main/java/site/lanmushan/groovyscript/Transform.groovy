package site.lanmushan.groovyscript

import com.sun.org.apache.bcel.internal.classfile.Code

import javax.management.relation.Role

class Transform {
    private Map<String, Object> map;
    Transform(){

    }
    Transform(Map<String, Object> map) { this.map = map }
    def getProperty(String name) {map.get(name)}


    public static void main(String[] args) {
        Transform transform=new Transform();
        transform
        transform.name
    }
}
