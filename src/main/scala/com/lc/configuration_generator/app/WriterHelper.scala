package main.scala.com.lc.configuration_generator.app

import cn.hutool.json.JSONUtil
import main.scala.com.lc.configuration_generator.bean.HiveTableProcess
import main.scala.com.lc.configuration_generator.utils.DbHelper.queryDb
import main.scala.com.lc.configuration_generator.utils.MyConfig
import main.scala.com.lc.configuration_generator.utils.TypeAndFileTool.saveFile
/**
 * ClassName:WriterHelper
 * Package:main.scala.com.lc.configuration_generator.app 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-13:51
 *
 */
object WriterHelper {

  private val config = MyConfig.getConfig

  //2.查询元数据库
  val tableBeanList = queryDb(config.hiveSql, classOf[HiveTableProcess], config.hiveTableSchema)

//  println(tableBeanList)

  //3.按表名分组封装成map
  val map = tableBeanList.groupBy(_.tableName).mapValues {
    _.map(str => {
      str.columnName
    })
  }

//     println(map)

  //4.过滤出指定的表
  val tableFilter = map.filter(str => config.hiveTableNameList.contains(str._1))
//      println(tableFilter)

  //处理import-json
  tableFilter.groupBy(_._1).map(str => {

    val jsonObject = JSONUtil.parseObj(config.exportJson)

//    println(s"原json${jsonObject}")

    val writerColumnArray = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0)
      .getJSONObject("writer").getJSONObject("parameter").getJSONArray("column")

    val tableArray = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0)
      .getJSONObject("writer").getJSONObject("parameter").getJSONArray("connection").getJSONObject(0).getJSONArray("table")


    for (elem <- str._2) {
      //封装到writer下的table[]
      tableArray.add(elem._1)

      // //封装到writer下的column[]
      for (elem <- elem._2) {
        //封装到read下的column[]
        writerColumnArray.add(elem)
      }
    }

    println(jsonObject)
    val path = s"${config.exportPath}\\${config.mysqlTableSchema}_report.${str._1}.json"
    //      println(path)

    //保存到批定目录
    saveFile(path, jsonObject.toString)
  })
}
