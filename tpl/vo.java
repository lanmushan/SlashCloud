##定义初始变量
        #set($tableName=$tool.append("Vo",$tableInfo.name))
        ##设置回调
        $!callback.setFileName($tool.append($tableName,".java"))
        $!callback.setSavePath($tool.append($tableInfo.savePath,"/vo"))

        ##拿到主键
        #if(!$tableInfo.pkColumn.isEmpty())
        #set($pk=$tableInfo.pkColumn.get(0))
        #end

        #if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}vo;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务接口
 *
 * @author $!author
 * @since $!time.currTime()
 */
public class Vo$!{tableInfo.name}extends $!{tableInfo.name}{


        }