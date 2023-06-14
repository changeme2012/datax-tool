package main.scala.com.lc.configuration_generator.utils

import cn.hutool.core.io.file.FileWriter
import main.scala.com.lc.configuration_generator.bean.ColumnAndType

/**
 * ClassName:TypeAndFileTool
 * Package:main.scala.com.lc.configuration_generator.utils 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-13:57
 *
 */
object TypeAndFileTool {

  //类型转换
  def typeTransformer(columnAndType: ColumnAndType): ColumnAndType = {

    val newType = columnAndType.`type` match {
      case "int" => "bigint"
      case "bigint" => "bigint"
      case "smallint" => "bigint"
      case "tinyint" => "bigint"
      case "decimal" => "string"
      case "double" => "double"
      case "float" => "float"
      case "binary" => "string"
      case "char" => "string"
      case "varchar" => "string"
      case "datetime" => "string"
      case "time" => "string"
      case "timestamp" => "string"
      case "date" => "string"
      case "text" => "string"
    }
    ColumnAndType(columnAndType.name, newType)
  }

  //写文件 覆盖写
  def saveFile(path: String, data: String) = {

    new FileWriter(path).write(data, false)
  }

}
