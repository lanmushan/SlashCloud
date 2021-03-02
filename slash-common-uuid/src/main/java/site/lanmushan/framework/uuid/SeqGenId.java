package site.lanmushan.framework.uuid;

import tk.mybatis.mapper.genid.GenId;

/**
 * 全局序列生成
 * 使用雪花算法
 */
public class SeqGenId implements GenId<Long> {

    @Override
    public Long genId(String table, String column) {
        return MyUUID.getInstance().nextId();
    }


}
