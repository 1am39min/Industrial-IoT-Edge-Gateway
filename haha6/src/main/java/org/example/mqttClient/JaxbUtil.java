package org.example.mqttClient;

import jakarta.xml.bind.*;

import java.io.*;


public class JaxbUtil {//工具类

    /**
     * javabean对象转换为xml文件
     * @param obj    javabean对象.Class
     * @param xmlPath  xml文件路径
     * @throws JAXBException 
     * @throws PropertyException
     * @throws IOException   
     */
    public static void beanToXml(Object obj, String xmlPath)  {    	
        try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());        
			Marshaller marshaller = context.createMarshaller();        
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			FileWriter fileWriter=new FileWriter(xmlPath);
			marshaller.marshal(obj, fileWriter);
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
    }    

    /**
     * xml文件配置转换为对象
     * @param xmlPath  xml文件路径
     * @param load    java对象.Class
     * @return    java对象
     * @throws JAXBException    
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xmlPath, Class<T> load)  {
    	T t=null;
    	File file=new File(xmlPath);
    	
        try {
			JAXBContext context = JAXBContext.newInstance(load);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t=(T) unmarshaller.unmarshal(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return t;
    }

}


