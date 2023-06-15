package main.scala.com.lc.configuration_generator.utils



/**
 * ClassName:MyConfig
 * Package:main.scala.com.lc.configuration_generator.bean 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-13-12:47
 *
 */
object MyConfig {

  import pureconfig._
  import pureconfig.generic.auto._

  //读取配置文件
  private val source = ConfigSource.resources("application.conf")

  //封装成Config对象，有异常抛出
  def getConfig = {
    source.loadOrThrow[Config]
  }


  case class Config(
                     mysqlSql: String,

                     hiveSql: String,

                     mysqlTableSchema: String,

                     mysqlTableNameList: List[String],

                     hiveTableSchema: String,

                     hiveTableNameList: List[String],

                     importPath: String,

                     exportPath: String,

                     importJson: String,

                     exportJson: String
                   )

/*  def getConfig = {
    val sql = configFactory.getString("SQL")
    val tableSchema = configFactory.getString("TABLE_SCHEMA")
    val tableNameList: Array[String] = configFactory.getString("TABLE_NAME").split(",")
    val jdbcurl = configFactory.getString("jdbcurl")
    val username = configFactory.getString("username")
    val password = configFactory.getString("password")
    val splitpk = configFactory.getString("splitpk")
    val compress = configFactory.getString("compress")
    val defaultFS = configFactory.getString("defaultFS")
    val fieldDelimiter = configFactory.getString("fieldDelimiter")
    val fileType = configFactory.getString("fileType")
    val writeMode = configFactory.getString("writeMode")
    val nullFormat = configFactory.getString("nullFormat")
    val channel = configFactory.getInt("channel")

    Config(
      sql,
      tableSchema,
      tableNameList,
      jdbcurl,
      username,
      password,
      splitpk,
      compress,
      defaultFS,
      fieldDelimiter,
      fileType,
      writeMode,
      nullFormat,
      channel)
  }*/


}
