package site.lanmushan.framework.datasope.jsqlparser.mysql;

/**
 * @author Administrator
 */
public class MysqlDataScopeSqlParser {


//
//
//
//    public static void main(String[] args) throws JSQLParserException {
//        List<String> sqlList = Arrays.asList(
//                "select * from auth as b,a as c  where 1=1 ",
//                "SELECT resource_url,GROUP_CONCAT(fk_role_code) as roles FROM (SELECT id,resource_url from auth_tb_resource WHERE resource_type=\"api\") as api\n" +
//                        "LEFT JOIN auth_fk_role_resource AS role ON role.fk_resource_id = api.id \n" +
//                        "WHERE\n" +
//                        "\tresource_url = '/api/test' \n" +
//                        "GROUP BY\n" +
//                        "\tresource_url\n",
//                "SELECT * FROM auth_fk_user_role,auth_fk_role_resource as r WHERE l.fk_role_code=r.fk_role_code AND l.fk_role_code='admin'"
//        );
//        MysqlDataScopeSqlParser mysqlDataScopeSqlParser = new MysqlDataScopeSqlParser();
//        sqlList.forEach(it -> {
//            System.out.println("结果:" + mysqlDataScopeSqlParser.parseSelectSql(it));
//        });
//    }
//
//    @Override
//    public String parseSql(Statement statement) throws JSQLParserException {
//        return null;
//    }
}
