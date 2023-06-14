package main.scala.com.lc.configuration_generator.bean

import scala.beans.BeanProperty

/**
 * ClassName:ColumnAndType
 * Package:main.scala.com.lc.configuration_generator.bean 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-13:54
 *
 */
case class ColumnAndType(@BeanProperty
                         var name: String,
                         @BeanProperty
                         var `type`: String
                        )
