package main.scala.com.lc.configuration_generator.bean

import scala.beans.BeanProperty

/**
 * ClassName:TableProcess
 * Package:main.scala.com.lc.configuration_generator.bean 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-13-19:06
 *
 */
class MysqlTableProcess {
  @BeanProperty
  var tableSchema: String = ""
  @BeanProperty
  var tableName: String = ""
  @BeanProperty
  var columnName: String = ""
  @BeanProperty
  var dataType: String = ""

}


