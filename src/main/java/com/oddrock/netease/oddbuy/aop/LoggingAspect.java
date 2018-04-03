package com.oddrock.netease.oddbuy.aop;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.entity.Person;
import com.oddrock.netease.oddbuy.service.PersonService;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

@Aspect
@Component
public class LoggingAspect {
	@Autowired
	private PersonService personService;
	private static String[] types = { "java.lang.Integer", "java.lang.Double",  
            "java.lang.Float", "java.lang.Long", "java.lang.Short",  
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",  
            "java.lang.String", "int", "double", "long", "short", "byte",  
            "boolean", "char", "float" }; 
	private static Logger logger = Logger.getLogger(LoggingAspect.class);
    @SuppressWarnings("unchecked")
	@Before("execution(* com.oddrock.netease.oddbuy.web.*.*(..)) && args(request, ..)")
    private void arithmeticDoLog(JoinPoint jp,HttpServletRequest request) throws ClassNotFoundException, NotFoundException {
        /*RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;  
        HttpServletRequest request = sra.getRequest();  */
    	String classType = jp.getTarget().getClass().getName();  
        Class<?> clazz = Class.forName(classType);  
        String clazzName = clazz.getName();  
        String clazzSimpleName = clazz.getSimpleName();  
        String methodName = jp.getSignature().getName();  
        String[] paramNames = getFieldsName(this.getClass(), clazzName, methodName);  
        String logContent = writeLogInfo(paramNames, jp); 
    	HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
        String userName = (String)session.getAttribute("userName");
        if(user==null && userName!=null) {
        	List<Person> list = personService.getUser(userName);
    		user = list.get(0);
        }
        logger.warn("====================开始 "+clazzName + "."+methodName+"()====================");
        logger.warn("类    名："+clazzSimpleName);
        logger.warn("方法名："+methodName);
        logger.warn("参    数："+logContent);
    	logger.warn("Session中user为:" + user);
    	Map<Long, Content> cart = (Map<Long, Content>)session.getAttribute("cart");
    	if(cart!=null) {
    		StringBuffer sb = new StringBuffer();
    		boolean first = true;
    		for(Content content : cart.values()) {
    			if(first) {
    				first = false;
    			}else {
    				sb = sb.append("，");
    			}
    			sb = sb.append(content.getBuyNum()+"件"+content.getTitle());
    		}
    		logger.warn("购物车内有："+sb.toString());
    	}else {
    		logger.warn("购物车为空");
    	}
    	logger.warn("====================结束 "+clazzName + "."+methodName+"()====================\n");
    }
    
    private static String writeLogInfo(String[] paramNames, JoinPoint joinPoint){  
        Object[] args = joinPoint.getArgs();  
        StringBuilder sb = new StringBuilder();  
        boolean clazzFlag = true;  
        for(int k=0; k<args.length; k++){  
            Object arg = args[k];  
            sb.append(paramNames[k]+" ");  
            // 获取对象类型  
            String typeName = arg.getClass().getTypeName();  
              
            for (String t : types) {  
                if (t.equals(typeName)) {  
                    sb.append("=" + arg+"; ");  
                }  
            }  
            if (clazzFlag) {  
                sb.append(getFieldsValue(arg));  
            }  
        }  
        return sb.toString();  
    } 
    
    @SuppressWarnings("rawtypes")
	private static String[] getFieldsName(Class cls, String clazzName, String methodName) throws NotFoundException{  
        ClassPool pool = ClassPool.getDefault();  
        //ClassClassPath classPath = new ClassClassPath(this.getClass());  
        ClassClassPath classPath = new ClassClassPath(cls);  
        pool.insertClassPath(classPath);  
          
        CtClass cc = pool.get(clazzName);  
        CtMethod cm = cc.getDeclaredMethod(methodName);  
        MethodInfo methodInfo = cm.getMethodInfo();  
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
        String[] paramNames = new String[cm.getParameterTypes().length];  
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
        for (int i = 0; i < paramNames.length; i++){  
            paramNames[i] = attr.variableName(i + pos); //paramNames即参数名  
        }  
        return paramNames;  
    }  
    /** 
     * 得到参数的值 
     * @param obj 
     */  
    public static String getFieldsValue(Object obj) {  
        Field[] fields = obj.getClass().getDeclaredFields();  
        String typeName = obj.getClass().getTypeName();  
        for (String t : types) {  
            if(t.equals(typeName))  
                return "";  
        }  
        StringBuilder sb = new StringBuilder();  
        sb.append("【");  
        for (Field f : fields) {  
            f.setAccessible(true);  
            try {  
                for (String str : types) {  
                    if (f.getType().getName().equals(str)){  
                        sb.append(f.getName() + " = " + f.get(obj)+"; ");  
                    }  
                }  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
        sb.append("】");  
        return sb.toString();  
    }  
}
