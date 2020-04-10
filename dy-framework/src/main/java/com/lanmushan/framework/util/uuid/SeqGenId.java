package com.lanmushan.framework.util.uuid;
import tk.mybatis.mapper.genid.GenId;

/**
 * 全局序列生成
 * 使用雪花算法
 */
public class SeqGenId implements GenId<Long> {

    @Override
    public  Long genId(String table, String column) {
        return MyUUID.getInstance().nextId();
    }

    public static void main(String args[])
    {
        SeqGenId seqGenId=new SeqGenId();
        Long id=   seqGenId.genId("11","11");
        System.out.print(id);
    }
    public static Long createId(){
        SeqGenId seqGenId=new SeqGenId();
        Long id=   seqGenId.genId("11","11");
        return id;
    }
}
