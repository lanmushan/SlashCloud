package site.lanmushan.framework.datasope.jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlParser {
    /**
     * 查询字段
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    private static List<String> test_select_items(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectitems = plain.getSelectItems();
        List<String> str_items = new ArrayList<String>();
        if (selectitems != null) {
            for (SelectItem selectitem : selectitems) {
                str_items.add(selectitem.toString());
            }
        }
        return str_items;
    }

    /**
     * 查询表名 table
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    private static List<String> test_select_table(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        //  System.out.println(statement.toString());
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        return tablesNamesFinder
                .getTableList(selectStatement);
    }

    /**
     * 查询 join
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    private static List<String> test_select_join(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;

        PlainSelect plain = (PlainSelect) selectStatement.getSelectBody();
        //System.out.println(plain.toString());
        List<Join> joinList = plain.getJoins();
        List<String> tablewithjoin = new ArrayList<String>();
        if (joinList != null) {
            for (Join join : joinList) {
                join.setLeft(false);
                tablewithjoin.add(join.toString());
                //注意 ， leftjoin rightjoin 等等的to string()区别
            }
        }
        return tablewithjoin;
    }
    /**
     * 查询where
     */
    private static String test_select_where(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where_expression = plain.getWhere();
        return where_expression.toString();
    }
    /**
     * 对where的结果进行解析
     */
    private static void testParseWhere1(String sql){
        try {
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect)selectBody;

            Expression where = plainSelect.getWhere();
            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
            plainSelect.getWhere().accept(expressionDeParser);

            // 此处根据where实际情况强转 最外层
            EqualsTo equalsTo = (EqualsTo)where;
            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * where两个条件or连接
     * 代码中有两个条件or连接，可回忆转成OrExpression,里面还是两个EqualsTo。
     */
    private static void testParseWhere2(String sql){
        try {
            Select select = (Select)CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect)selectBody;

            Expression where = plainSelect.getWhere();
            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
            plainSelect.getWhere().accept(expressionDeParser);

            // 此处根据where实际情况强转 最外层
            OrExpression orExpression = (OrExpression)where;
            EqualsTo equalsTo = (EqualsTo)orExpression.getLeftExpression();

            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());
            System.out.println("--------");
            equalsTo = (EqualsTo)orExpression.getRightExpression();

            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }
    /**
     * where三个条件or连接
     *得到的第一层的leftExpression还是一个orExpression,rightExpression是一个EqualsTo
     */
    private static void testParseWhere3(String sql){
        try {
            Select select = (Select)CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect)selectBody;

            Expression where = plainSelect.getWhere();
            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
            plainSelect.getWhere().accept(expressionDeParser);

            // 此处根据where实际情况强转 最外层
            OrExpression orExpression = (OrExpression)where;

            OrExpression leftOrExpression = (OrExpression)orExpression.getLeftExpression();

            EqualsTo equalsTo = (EqualsTo)leftOrExpression.getLeftExpression();

            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());
            System.out.println("--------");
            equalsTo = (EqualsTo)leftOrExpression.getRightExpression();

            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());
            System.out.println("--------");
            equalsTo = (EqualsTo)orExpression.getRightExpression();

            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());

        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询 group by
     * @param sql
     * @return
     * @throws JSQLParserException
     */
//    private static List<String> test_select_groupby(String sql)
//            throws JSQLParserException {
//        CCJSqlParserManager parserManager = new CCJSqlParserManager();
//        Select select = (Select) parserManager.parse(new StringReader(sql));
//        PlainSelect plain = (PlainSelect) select.getSelectBody();
//        List<Expression> GroupByColumnReferences = plain
//                .getGroupByColumnReferences();
//        List<String> str_groupby = new ArrayList<String>();
//        if (GroupByColumnReferences != null) {
//            for (Expression groupByColumnReference : GroupByColumnReferences) {
//                str_groupby.add(groupByColumnReference.toString());
//            }
//        }
//        return str_groupby;
//    }
    /**
     * 查询order by
     */
    private static List<String> test_select_orderby(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<OrderByElement> OrderByElements = plain.getOrderByElements();
        List<String> str_orderby = new ArrayList<String>();
        if (OrderByElements != null) {
            for (OrderByElement orderByElement : OrderByElements) {
                str_orderby.add(orderByElement.toString());
            }
        }
        return str_orderby;
    }
    /**
     * 子查询
     */
    private static Map test_select_subselect(SelectBody selectBody) throws JSQLParserException {
        Map<String, String> map = new HashMap<String, String>();

        if (selectBody instanceof PlainSelect) {
            List<SelectItem> selectItems = ((PlainSelect) selectBody).getSelectItems();
            for (SelectItem selectItem : selectItems) {
                if (selectItem.toString().contains("(") && selectItem.toString().contains(")")) {
                    map.put("selectItemsSubselect", selectItem.toString());
                }
            }
            Expression where = ((PlainSelect) selectBody).getWhere();
            String whereStr = where.toString();
            if (whereStr.contains("(") && whereStr.contains(")")) {
                int firstIndex = whereStr.indexOf("(");
                int lastIndex = whereStr.lastIndexOf(")");
                CharSequence charSequence = whereStr.subSequence(firstIndex, lastIndex + 1);
                map.put("whereSubselect", charSequence.toString());
            }

            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
//            System.out.println("111----"+((PlainSelect) selectBody).getFromItem());
//            System.out.println(fromItem);
            if (fromItem instanceof SubSelect) {
                map.put("fromItemSubselect", fromItem.toString());
            }

        } else if (selectBody instanceof WithItem) {
            SqlParser.test_select_subselect(((WithItem) selectBody).getSelectBody());
        }
        return map;
    }
    /**
     * 单表操作测试
     * @throws Exception
     */
    @org.junit.Test
    public void jsqlparser2() throws Exception{
        /**
         * 查询字段
         */
        System.out.println("-----------查询字段----------");
        String sql = "SELECT id,name,age FROM TABLE1";
        List<String> list = test_select_items(sql);
        System.out.println(list);
        /**
         * 查询表名
         */
        System.out.println("-----------查询表名----------");
        List<String> list1 = test_select_table(sql);
        System.out.println(list1);
        /**
         * join
         */
        System.out.println("-----------join连接查询表名----------");
        String sql2 = "select id,name,age from table1 t1 left join table2 t2 on t1.id=t2.id left join table3 t3 on t3.id=t2.id";
        List<String> list2 = test_select_table(sql2);
        System.out.println(list2);
        //inner join
        System.out.println("-----------join连接查询----------");
        String sql7 = "SELECT column_name(s)\n" +
                "FROM table_name1\n" +
                "INNER JOIN table_name2 \n" +
                "ON table_name1.column_name=table_name2.column_name;";
        List<String> selectJoin1 = test_select_join(sql7);
        System.out.println(selectJoin1);
        //left join
        String sql8 = "SELECT column_name(s)\n" +
                "FROM table_name1\n" +
                "LEFT JOIN table_name2 \n" +
                "ON table_name1.column_name=table_name2.column_name;";
        List<String> selectJoin2 = test_select_join(sql8);
        System.out.println(selectJoin2);
        //right join
        String sql9 = "SELECT column_name(s)\n" +
                "FROM table_name1\n" +
                "right join table_name2 \n" +
                "ON table_name1.column_name=table_name2.column_name;";
        List<String> selectJoin3 = test_select_join(sql9);
        System.out.println(selectJoin3);
        /**
         * where
         */
        System.out.println("-----------where查询所有的条件----------");
        String sql3="select * from table1 where id=1 and id=2";
        String selectWhere = test_select_where(sql3);
        System.out.println(selectWhere);
        /**
         * where or条件查询
         */
        System.out.println("-----------where单个条件or连接----------");
        String sqlw1 = "select *from A as a left join B on a.bid = B.id left join C on A.cid = C.id left join D on B.did = D.id where a.id = 23";
        testParseWhere1(sqlw1);
        System.out.println("-----------where两个条件or连接--------");
        String sqlw2 = "select *from A as a left join B on a.bid = B.id left join C on A.cid = C.id left join D on B.did = D.id where a.id = 23 or b.id = 34";
        testParseWhere2(sqlw2);
        System.out.println("-----------where三个条件or连接--------");
        String sqlw3 = "select *from A as a left join B on a.bid = B.id left join C on A.cid = C.id left join D on B.did = D.id where a.id = 23 or b.id = 34 or c.id = 54";
        testParseWhere3(sqlw3);
        /**
         * 查询group by
         */
        System.out.println("-----------group by--------");
        String sql4="select id,name,age from core_user group by id";
      //  List<String> select_groupby = test_select_groupby(sql4);
        //System.out.println(select_groupby);
        /**
         * 查询order by
         */
        System.out.println("-----------order by--------");
        String sql5="select id,name,age from core_user order by id";
        List<String> select_orderby = test_select_orderby(sql5);
        System.out.println(select_orderby);
        /**
         * 子查询
         */
        System.out.println("-----------子查询--------");
        String sql6 = "select age,sex,(select dno from employee where salary >=5000) from (select dno from employee5 where salary >=5000) a where did in(select dno from employee3 where salary >=5000);";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select selectStatement = (Select) parserManager.parse(new StringReader(sql6));
        Map map = test_select_subselect(selectStatement.getSelectBody());
        System.out.println(map.toString());
        //查询结果
        /*{whereSubselect=(SELECT dno FROM employee3 WHERE salary >= 5000),
         fromItemSubselect=(SELECT dno FROM employee5 WHERE salary >= 5000) a,
         selectItemsSubselect=(SELECT dno FROM employee WHERE salary >= 5000)
         }
         说明： whereSubselect 条件
                fromItemSubselect 表格
                selectItemsSubselect字段
         */


    }
}
