package site.lanmushan.groovyscript

class TransformUtil {
    def static Object execute(String content,Map<String,Object> map){
        def engine = new groovy.text.GStringTemplateEngine()
        def template = engine.createTemplate(content).make(map)
        return template.toString();
    }

    public static void main(String[] args) {
        TransformUtil transform = new TransformUtil();
        Map<String,String> map=new HashMap<>();
        map.put("TutorialName","我擦")
        for (int i = 0; i < 1000; i++) {
            transform.execute("\${TutorialName}",map)

        }
    }
}
