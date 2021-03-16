package site.lanmushan.groovyscript

class GroovyTransform6ce6049d972eeac2b42f38cfd0d8216d {
    private Map<String, Object> map;

    GroovyTransform6ce6049d972eeac2b42f38cfd0d8216d(Map<String, Object> map) { this.map = map }

    def getProperty(String name) { map.get(name) }

    def Object doHandler() {
        String str = "{\"name\":\"${name}\"}"; return str;
    }
}