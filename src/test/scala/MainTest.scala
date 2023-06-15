package test.scala

import lombok.extern.log4j.Log4j2
import main.scala.com.lc.configuration_generator.app
import main.scala.com.lc.configuration_generator.app.{ReadHelper, WriterHelper}
import org.apache.logging.log4j.LogManager

/**
 * ClassName:MainTest
 * Package:test.scala 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-14-14:02
 *
 */
object MainTest {
  def main(args: Array[String]): Unit = {
    val logger = LogManager.getLogger(getClass)
    ReadHelper
    WriterHelper


      if (args.isEmpty || args.length>2) {
        logger.debug("获取参数失败，请检查")
      }
      else if(args.length <2 ) {

        if ( "mh".equals(args(0).toLowerCase())){
          ReadHelper
        }
        else if("hm".equals(args(0).toLowerCase())){
          WriterHelper
        }
        else if("all".equals(args(0).toLowerCase())){
          ReadHelper
          WriterHelper
        }
      }

    //        args(0).toLowerCase() match {
    //          case "mysql_hdfs" => ReadHelper
    //          case _ => logger.debug("传入参数错误，请检查")
    //        }

  }

}
