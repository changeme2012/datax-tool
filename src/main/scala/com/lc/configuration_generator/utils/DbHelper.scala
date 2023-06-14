package main.scala.com.lc.configuration_generator.utils
import com.alibaba.druid.pool.DruidDataSourceFactory
import org.apache.commons.dbutils.{BasicRowProcessor, GenerousBeanProcessor, QueryRunner}
import org.apache.commons.dbutils.handlers.{BeanHandler, BeanListHandler}

import java.util.Properties
import javax.sql.DataSource
import scala.collection.JavaConverters.asScalaBufferConverter

/**
 * ClassName:DbHelper
 * Package:com.atguigu.utils 
 * Description:
 *
 * @Author: 龙成
 * @Create: 2023-06-10-15:01
 *
 */
object DbHelper {

  private val properties = new Properties()

  properties.load(DbHelper.getClass.getClassLoader.getResourceAsStream("druid.properties"))

  private val dataSource: DataSource = DruidDataSourceFactory.createDataSource(properties)

  private val runner = new QueryRunner(dataSource)


  //多行查询 带占位符
  def queryDb[T](sql: String, clazz: Class[T], params : String) = {
    runner.query(sql, new BeanListHandler[T](clazz, new BasicRowProcessor(new GenerousBeanProcessor)),params).asScala.toList
  }

  //多行查询 不带占位符
  def queryDb[T](sql: String, clazz: Class[T]) = {
    runner.query(sql, new BeanListHandler[T](clazz, new BasicRowProcessor(new GenerousBeanProcessor))).asScala.toList
  }

  //单行查询
  def queryDbOne[T](sql:String,clazz:Class[T],params : String)={
    runner.query(sql,new BeanHandler[T](clazz,new BasicRowProcessor(new GenerousBeanProcessor)),params)
  }



}
