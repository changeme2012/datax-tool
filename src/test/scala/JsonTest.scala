package test.scala

import cn.hutool.json.JSONUtil

import scala.beans.BeanProperty


/**
 * ClassName:JsonTest
 * Package:main.scala.com.lc.configuration_generator 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-13-19:59
 *
 */
object JsonTest {
  def main(args: Array[String]): Unit = {

    val json = "{\"column\":[],\"connection\":\"0000\"}"

    val nObject = JSONUtil.parseObj(json)

    val jSONObject = nObject.getJSONArray("column")

    for (elem <- 6 to 10) {
      for (elem <- 1 to 5) {
        jSONObject.add(elem.toString)
      }
    }

    println(nObject)

    //    val andType = new columnAndType("activity_reduce_amount", "decimal")
    //
    //    println(andType)
    //
    //    val str = JSONUtil.toJsonStr(andType)
    //
    //    println(str)

  }

  case class columnAndType(
                            @BeanProperty
                            var name: String,
                            @BeanProperty
                            var `type`: String
                          )


}
