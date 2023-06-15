package main.scala.com.lc.configuration_generator.app

import cn.hutool.json.JSONUtil
import main.scala.com.lc.configuration_generator.bean.{ColumnAndType, MysqlTableProcess}
import main.scala.com.lc.configuration_generator.utils.DbHelper.queryDb
import main.scala.com.lc.configuration_generator.utils.TypeAndFileTool.{saveFile, typeTransformer}
import main.scala.com.lc.configuration_generator.utils.MyConfig

/**
 * ClassName:ReadHelper
 * Package:main.scala.com.lc.configuration_generator.app 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-13:51
 *
 */
object ReadHelper {

  private val config = MyConfig.getConfig

  //2.查询元数据库
  val tableBeanList = queryDb(config.mysqlSql, classOf[MysqlTableProcess], config.mysqlTableSchema)

  //3.按表名分组封装成map(表名，list((列名，类型)))
  val map = tableBeanList.groupBy(_.tableName).mapValues {_.map(str => (str.columnName, str.dataType))}

  //   println(map.get("order_detail"))

  //4.过滤出指定的表
  val tableFilter = map.filter(str => config.mysqlTableNameList.contains(str._1))
  //    println(tableFilter)

  //处理import-json
  tableFilter.groupBy(_._1).map(str => {
    val jsonObject = JSONUtil.parseObj(config.importJson)

    val readColumnArray = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("reader")
      .getJSONObject("parameter").getJSONArray("column")

    val tableArray = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("reader")
      .getJSONObject("parameter").getJSONArray("connection").getJSONObject(0).getJSONArray("table")

    val writerColumnArray = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0)
      .getJSONObject("writer").getJSONObject("parameter").getJSONArray("column")

    for (elem <- str._2) {
      for (elem <- elem._2) {
        //封装到read下的column[]
        readColumnArray.add(elem._1)
      }

      //封装到read下的table[]
      tableArray.add(elem._1)

      //转换type 并封装到writeMode下的column[]
      for (elem <- elem._2) {
        val columnAndType = ColumnAndType(elem._1, elem._2)
        val newColumnAndType = typeTransformer(columnAndType)
        writerColumnArray.add(newColumnAndType)
      }
    }

    println(jsonObject)

    val path = s"${config.importPath}\\${config.mysqlTableSchema}.${str._1}.json"
    //      println(path)
    //保存到批定目录
    saveFile(path, jsonObject.toString)
  })


}
