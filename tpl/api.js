#
#定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Api"))
#
#设置回调
$
!callback.setFileName($tool.append($tableName, ".js"))
$
!callback.setSavePath($tool.append($tableInfo.savePath, "/js"))

import http from '@/apis/http.js'

const $
!{tool.firstLowerCase($
!{tableInfo.name}
)
}
Api = {
    /**
     * 查询当前用户的
     */
    selectById: (id) => {
        let data = {id: id};
        return http.doGet('/$!{tool.firstLowerCase($!{tableInfo.name})}/selectList', data)

    },
    /**
     * 查询列表
     * @param data
     * @returns {*|Promise<AxiosResponse<T>>}
     */
    selectList: (data) => {
        return http.doGet('/$!{tool.firstLowerCase($!{tableInfo.name})}/selectList', data)
    },
    /**
     * 新增
     * @param data
     * @returns {*|Promise<AxiosResponse<T>>}
     */
    addService: (data) => {
        return http.doPost('/$!{tool.firstLowerCase($!{tableInfo.name})}/add', data)
    },
    /**
     * 更新
     * @param data
     * @returns {*|Promise<AxiosResponse<T>>}
     */
    updateService: (data) => {
        return http.doPost('/$!{tool.firstLowerCase($!{tableInfo.name})}/update', data)
    },
    /**
     * 删除
     * @param data
     * @returns {*|Promise<AxiosResponse<T>>}
     */
    deleteListService: (data) => {
        return http.doPost('/$!{tool.firstLowerCase($!{tableInfo.name})}/delete', data)
    }
}
export default $
!{tool.firstLowerCase($
!{tableInfo.name}
)
}
Api
