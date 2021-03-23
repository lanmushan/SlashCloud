package site.lanmushan.framework.datasope.jsqlparser.mysql

import site.lanmushan.framework.datasope.bean.DataScopeUserContext

/**
 * cur.hasRole("xxx")||cur.role[xxx]
 */
class DataScopeDynamicGroovy {
    def Boolean doHandler(DataScopeUserContext ctx) {
        return ctx.hasRole("admin")
    }
}
