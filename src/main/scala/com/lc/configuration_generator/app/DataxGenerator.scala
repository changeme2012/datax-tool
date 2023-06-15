package main.scala.com.lc.configuration_generator.app


import org.apache.logging.log4j.LogManager



/**
 * ClassName:Main
 * Package:main.scala.com.lc.configuration_generator.app 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-13-12:22
 *
 */

object DataxGenerator {
  def main(args: Array[String]): Unit = {

    val logger = LogManager.getLogger(getClass)

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
   }
}
