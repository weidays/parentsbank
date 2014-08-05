package com.weidays.parentsbank.server.entity.po;  
  
import java.beans.IntrospectionException;  
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.springframework.jdbc.core.RowMapper;  
import org.springframework.jdbc.support.JdbcUtils;  
  
/** 
 *
 * 对查询数据结果集进行bean自动装箱 
 * (注:结果集与bean之间满足toLowerCase(columnName) = toLowerCase(propertyName))时才自动装箱 
 */  
public class AutoBoxingRowMapper implements RowMapper {  
      
    protected final Log logger = LogFactory.getLog(this.getClass());  
      
    private boolean stringNotEmpty = true;//结果集中由字段值全部以string类型返回其值,并且为空时以空串""返回  
      
    private Class<Object> beanClass;//即将要进行包装的bean的Class  
      
    private Map<String,PropertyDescriptor> beanPropertyMap = new HashMap<String,PropertyDescriptor>();//即将要进行包装的bean的属性描述,以Map形式保存  
      
    public boolean isStringNotEmpty() {  
        return stringNotEmpty;  
    }  
  
    public void setStringNotEmpty(boolean stringNotEmpty) {  
        this.stringNotEmpty = stringNotEmpty;  
    }  
  
    public Class<Object> getBeanClass() {  
        return beanClass;  
    }  
  
    public void setBeanClass(Class<Object> beanClass) {  
        this.beanClass = beanClass;  
    }  
      
    public Map<String, PropertyDescriptor> getBeanPropertyMap() {  
        return beanPropertyMap;  
    }  
  
    public void setBeanPropertyMap(Map<String, PropertyDescriptor> beanPropertyMap) {  
        this.beanPropertyMap = beanPropertyMap;  
    }  
      
    /** 
     * 自动装配sql查询结果集的各列值到beanClass所指定的bean中的对应属性上,满足toLowerCase(columnName) = toLowerCase(propertyName)时进行装配 
     * @param beanClass - 要装配的bean的java.lang.Class 
     */  
    @SuppressWarnings({"rawtypes", "unchecked"})  
    public AutoBoxingRowMapper(Class beanClass){  
        super();  
        this.beanClass = beanClass;  
          
        //初始化beanClass对应的bean的所有属性  
        PropertyDescriptor[] props = null;  
        try {  
            props = Introspector.getBeanInfo(this.getBeanClass(), Object.class)  
                    .getPropertyDescriptors();  
            if (props != null) {  
                for (int i = 0; i < props.length; i++) {  
                    this.beanPropertyMap.put(props[i].getName().toLowerCase().trim(), props[i]);  
                }  
            }  
        } catch (IntrospectionException e) {  
            logger.error(e, e);  
        }  
    }  
      
    /** 
     * 自动装配sql查询结果集的各列值到beanClass所指定的bean中的对应属性上,满足toLowerCase(columnName) = toLowerCase(propertyName)时进行装配 
     * @param beanClass - 要装配的bean的java.lang.Class 
     * @param stringNotEmpty - 是否将结果集中由字段值全部以string类型返回其值,并且为空时以空串""返回,default true 
     */  
    @SuppressWarnings({"rawtypes"})  
    public AutoBoxingRowMapper(Class beanClass,boolean stringNotEmpty){  
        this(beanClass);  
        this.stringNotEmpty = stringNotEmpty;  
    }  
      
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {  
          
        Object defaultInstance = null;  
        try {  
            defaultInstance = this.getBeanClass().newInstance();//获取bean的实例  
        } catch (Exception e) {  
            logger.error(e, e);  
        }  
        if(defaultInstance == null){  
            logger.error("attempt to new a instance of class named : " + this.getBeanClass().getName() + " but failed!!!");  
            return null;  
        }  
        //获取元数据  
        ResultSetMetaData rsmd = rs.getMetaData();  
        int columnCount = rsmd.getColumnCount();  
          
        PropertyDescriptor propertyDescriptor = null;  
        try {  
            for (int i = 1; i <= columnCount; i++) {  
                String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));//获取resultset结果集中第i列对应的列名  
                Object obj = getColumnValue(rs, i, key);//获取resultset结果集中第i列对应的值  
                  
                propertyDescriptor = this.beanPropertyMap.get(key);  
                if(propertyDescriptor == null){//当前列名key对应的属性在bean中找不到,给予警告  
                    logger.warn("no corresponding property defined in class : " + this.getBeanClass().getName() + " for sql query resultset column '" + key + "' !!!");  
                    continue;  
                }else{////当前列名key对应的属性在bean中找到,则调用其setXxx方法设置其值  
                    propertyDescriptor.getWriteMethod().invoke(defaultInstance, obj);  
                }  
            }  
        } catch (Exception e) {  
            logger.error(e, e);  
        }   
          
        return defaultInstance;  
    }  
      
    protected String getColumnKey(String columnName) {  
        return columnName == null ? null : columnName.toLowerCase().trim();  
    }  
      
    @SuppressWarnings("rawtypes")  
    protected Object getColumnValue(ResultSet rs, int index, String columnName) throws SQLException {  
        Object val = null;  
        if(stringNotEmpty){//所有列以string类型解析(前提做到要约定你的bean中的所有属性都定义成string类型)  
            val = rs.getString(index) == null ? "" : rs.getString(index);  
        }else{//所有类型以其bean类中对应的属性类型进行解析  
            PropertyDescriptor propertyDescriptor = this.beanPropertyMap.get(columnName);  
              
            if(propertyDescriptor != null){  
                Class propertyTypeClass = propertyDescriptor.getPropertyType();  
                String className = propertyTypeClass.getName();  
                if(className.equals("int") || className.equals(Integer.class.getName())){  
                    val = rs.getInt(index);  
                    return val;  
                }else if(className.equals("short") || className.equals(Short.class.getName())){  
                    val = rs.getShort(index);  
                    return val;  
                }else if(className.equals("byte") || className.equals(Byte.class.getName())){  
                    val = rs.getByte(index);  
                    return val;  
                }else if(className.equals("long") || className.equals(Long.class.getName())){  
                    val = rs.getLong(index);  
                    return val;  
                }else if(className.equals("float") || className.equals(Float.class.getName())){  
                    val = rs.getFloat(index);  
                    return val;  
                }else if(className.equals("double") || className.equals(Double.class.getName())){  
                    val = rs.getDouble(index);  
                    return val;  
                }else if(className.equals("char") || className.equals(Character.class.getName())){  
                    val = rs.getString(index);  
                    return val;  
                }else if(className.equals(String.class.getName())){  
                    val = rs.getString(index);  
                    return val;  
                }else if(className.equals(java.util.Date.class.getName())){  
                    val = rs.getDate(index);  
                    return val;  
                }else if(className.equals(java.math.BigDecimal.class.getName())){  
                    val = rs.getBigDecimal(index);  
                    return val;  
                }else{//Object  
                    val = rs.getObject(index);  
                    return val;  
                }  
            }else{  
                val = JdbcUtils.getResultSetValue(rs, index);  
            }  
        }  
        return val;  
    }  
}  