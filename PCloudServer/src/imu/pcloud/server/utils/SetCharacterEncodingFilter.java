package imu.pcloud.server.utils;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
  
/** 
 * 自定义字符处理过滤器 
 * 参考自Tomcat目录webapps\examples\WEB-INF\classes\filters 
 * @author coderlu 
 * @since 2012-12-10 
 */  
public class SetCharacterEncodingFilter implements Filter {  
  
    protected FilterConfig filterConfig = null; //初始化配置  
    protected String encoding = null;           //接收字符编码  
    protected boolean ignore = true;            //是否忽略大小写  
      
    /* 析构函数 
     * @see javax.servlet.Filter#destroy() 
     */  
    @Override  
    public void destroy() {  
        this.encoding = null;  
        this.filterConfig = null;  
    }  
  
    /* 执行过滤操作 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
          
        if (ignore || (request.getCharacterEncoding() == null)) {  
            String encoding = selectEncoding(request);  //如果为空先从web.xml中得到  
            if (encoding != null){  
                request.setCharacterEncoding(encoding); //设置字符集编码  
            }  
        }  
        chain.doFilter(request, response);  
    }  
  
    /* 初始化过滤器 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
     */  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.filterConfig = filterConfig;     
        this.encoding = filterConfig.getInitParameter("encoding");  //从web.xml文件中读取encoding的值  
        String value = filterConfig.getInitParameter("ignore");     //从web.xml文件中读取ignore的值  
        //以下三种情况均为忽略大小写  
        if (value == null)  
            this.ignore = true;  
        else if (value.equalsIgnoreCase("true"))  
            this.ignore = true;  
        else if (value.equalsIgnoreCase("yes"))  
            this.ignore = true;  
        else  
            this.ignore = false;  
    }  
      
    /** 
     * 得到字符编码 
     * @param request 
     * @return 
     */  
    protected String selectEncoding(ServletRequest request) {  
        return (this.encoding);  
    }  
}  