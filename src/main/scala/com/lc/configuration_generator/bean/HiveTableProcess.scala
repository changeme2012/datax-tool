package main.scala.com.lc.configuration_generator.bean

import scala.beans.BeanProperty

/**
 * ClassName:HiveTableProcess
 * Package:main.scala.com.lc.configuration_generator.bean 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-14:44
 *
 */
class HiveTableProcess{
  @BeanProperty
  var dbName: String = ""
  @BeanProperty
  var tableName: String = ""
  @BeanProperty
  var columnName: String = ""
  @BeanProperty
  var columnType: String = ""

}


