package site.lanmushan.auth.app.controller;



import java.util.*;
//
///**
// * @author dy
// */
//@RestController
//@RequestMapping("/authNocas")
//@Slf4j
//@Api(tags = "注册中心接口")
//public class NocasController {
//    @Value("${spring.cloud.nacos.discovery.server-addr}")
//    private String nacosServerAddr;
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    public DictService dictService;
//    @Value("${spring.application.name}")
//    private String currentServiceName;
//    public static String[] apiExcludeService={"gateway-service"};
//
//    /**
//     * 查询所有的微服务
//     *
//     * @return
//     */
//    @GetMapping("/selectAllService")
//    public Message selectAllService() {
//        Message msg = new Message();
//        JSONObject resultJson = this.doSelectAllService();
//        msg.setRows(resultJson.getJSONArray("serviceList"));
//        msg.setTotal(resultJson.getInteger("count"));
//        return msg;
//    }
//
//    @GetMapping("/selectAllApis")
//    public Message selectAllApis(String serviceName, String searchKey) {
//        Message msg = new Message();
//        if (serviceName != null) {
//            Map<String, String> queryMap = new HashMap<>(2);
//            queryMap.put("searchKey", searchKey);
//            String url = "http://" + serviceName + "/api/selectApiList";
//            msg = restTemplate.getForObject(url, Message.class, searchKey, queryMap);
//            return msg;
//        } else {
//            List<Object> list = new ArrayList<>();
//            JSONObject resultJson = this.doSelectAllService();
//            resultJson.getJSONArray("serviceList").forEach(it -> {
//                JSONObject data = (JSONObject) JSONObject.toJSON(it);
//                String name = data.getString("name");
//                if(!Arrays.asList(apiExcludeService).contains(name))
//                {
//                    Map<String, String> queryMap = new HashMap<>(2);
//                    queryMap.put("searchKey", searchKey);
//                    String url = "http://" + name + "/api/selectApiList";
//                    Message resultMsg = restTemplate.getForObject(url, Message.class, searchKey, queryMap);
//                    list.addAll((Collection<?>) resultMsg.getRows());
//                }
//            });
//            msg.setRow(list);
//        }
//        return msg;
//    }
//
//    private JSONObject doSelectAllService() {
//        String url = "http://" + nacosServerAddr + "/nacos/v1/ns/catalog/services";
//        JSONObject queryJson = new JSONObject();
//        queryJson.put("hasIpCount", Boolean.TRUE);
//        queryJson.put("withInstances", Boolean.FALSE);
//        queryJson.put("pageNo", 1);
//        queryJson.put("pageSize", 100);
//        JSONObject resultJson = HttpClientUtils.doGetJson(url, queryJson, null);
//        return resultJson;
//    }
//
//    @GetMapping("/test")
//    public String test(String serviceName, String searchKey) {
//        Message msg = new Message();
//        return dictService.test();
//    }
//}
