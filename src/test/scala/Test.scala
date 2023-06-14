package test.scala

import cn.hutool.json.JSONUtil

/**
 * ClassName:Test
 * Package:main.scala.com.lc.configuration_generator 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-13-15:29
 *
 */
object Test {
  def main(args: Array[String]): Unit = {


//    val config = ConfigFactory.load()
//    println(config)

//    val setting = new Setting("example.setting")
//
//    val str = setting.getStr("url")
    import pureconfig._
    import pureconfig.generic.auto._

/*    val source = ConfigSource.file("D:\\workspace_idea\\datax-tool\\datax-tool\\src\\main\\resources\\cdd.conf")

    val source1 = ConfigSource.resources("cdd.conf")

    val value: Result[ServiceConf] = source1.load[ServiceConf]

    val failuresOrConf = value.map(str => {
      ServiceConf(str.host, str.port, str.useHttps, str.authMethods)
    })*/

    val source = ConfigSource.resources("application.conf")

    val value = source.loadOrThrow[Config1]

    println(value.sql)

    println(value.tableSchema)


    println(value)

    val json = "{\"job\":{\"content\":[{\"reader\":{\"name\":\"mysqlreader\",\"parameter\":{\"column\":[],\"connection\":[{\"jdbcUrl\":[],\"table\":[]}],\"password\":\"\",\"splitPk\":\"\",\"username\":\"\"}},\"writer\":{\"name\":\"hdfswriter\",\"parameter\":{\"column\":[],\"compress\":\"gzip\",\"defaultFS\":\"hdfs://hadoop102:8020\",\"fieldDelimiter\":\"\\t\",\"fileName\":\"\",\"fileType\":\"text\",\"path\":\"${targetdir}\",\"writeMode\":\"truncate\",\"nullFormat\":\"\"}}}],\"setting\":{\"speed\":{\"channel\":1}}}}"

    val jsonObject = JSONUtil.parseObj(json)

    val newColumnList = List("column1", "column2", "column3")
    val jsonArray = new java.util.ArrayList[String]()
    newColumnList.foreach(column => jsonArray.add(column))

    val readColumnArray = jsonObject.getJSONArray("job.content[0].reader.parameter.column")

    jsonObject.put("job.content[0].reader.parameter.column", jsonArray)

    println(jsonObject)



  }


  case class Port(number: Int) extends AnyVal

  case class ServiceConf(
                          host: String,
                          port: Port,
                          useHttps: Boolean,
                          authMethods: List[String]
                        )

  case class Config1(
                     sql: String,

                     tableSchema: String,

                     tableNameList: List[String],

                     json: String,
                   )

  case class Config(
                     sql: String,

                     tableSchema: String,

                     tableNameList: List[String],

                     jdbcurl: String,

                     username: String,

                     password: String,

                     splitpk: String,

                     compress: String,

                     defaultFS: String,

                     fieldDelimiter: String,

                     fileType: String,

                     writeMode: String,

                     nullFormat: String,

                     channel: Int
                   )

}
