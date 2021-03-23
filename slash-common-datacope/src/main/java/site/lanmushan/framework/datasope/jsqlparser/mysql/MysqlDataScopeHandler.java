package site.lanmushan.framework.datasope.jsqlparser.mysql;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.beans.factory.annotation.Autowired;
import site.lanmushan.framework.datasope.jsqlparser.support.IDataScopeHandler;
import site.lanmushan.framework.datasope.jsqlparser.support.IDeleteDataScopeSqlParser;
import site.lanmushan.framework.datasope.jsqlparser.support.ISelectDataScopeSqlParser;
import site.lanmushan.framework.datasope.jsqlparser.support.IUpdateDataScopeSqlParser;

/**
 * @author Administrator
 */
@Slf4j
public class MysqlDataScopeHandler implements IDataScopeHandler {
    @Autowired
    ISelectDataScopeSqlParser selectDataScopeSqlParser;
    @Autowired
    IUpdateDataScopeSqlParser updateDataScopeSqlParser;
    @Autowired
    IDeleteDataScopeSqlParser deleteDataScopeSqlParser;

    //暂时就这样了吧
    @Override
    public String parseSql(String sql, String type) {
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                return selectDataScopeSqlParser.parseSql(statement);
            } else if (statement instanceof Update) {
                return updateDataScopeSqlParser.parseSql(statement);
            } else if (statement instanceof Delete) {
                return deleteDataScopeSqlParser.parseSql(statement);
            }
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return sql;
    }

    public ISelectDataScopeSqlParser getSelectDataScopeSqlParser() {
        return selectDataScopeSqlParser;
    }

    public void setSelectDataScopeSqlParser(ISelectDataScopeSqlParser selectDataScopeSqlParser) {
        this.selectDataScopeSqlParser = selectDataScopeSqlParser;
    }

    public IUpdateDataScopeSqlParser getUpdateDataScopeSqlParser() {
        return updateDataScopeSqlParser;
    }

    public void setUpdateDataScopeSqlParser(IUpdateDataScopeSqlParser updateDataScopeSqlParser) {
        this.updateDataScopeSqlParser = updateDataScopeSqlParser;
    }
}
