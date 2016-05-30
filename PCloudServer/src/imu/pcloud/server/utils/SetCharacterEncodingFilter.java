package imu.pcloud.server.utils;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
  
/** 
 * �Զ����ַ���������� 
 * �ο���TomcatĿ¼webapps\examples\WEB-INF\classes\filters 
 * @author coderlu 
 * @since 2012-12-10 
 */  
public class SetCharacterEncodingFilter implements Filter {  
  
    protected FilterConfig filterConfig = null; //��ʼ������  
    protected String encoding = null;           //�����ַ�����  
    protected boolean ignore = true;            //�Ƿ���Դ�Сд  
      
    /* �������� 
     * @see javax.servlet.Filter#destroy() 
     */  
    @Override  
    public void destroy() {  
        this.encoding = null;  
        this.filterConfig = null;  
    }  
  
    /* ִ�й��˲��� 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
          
        if (ignore || (request.getCharacterEncoding() == null)) {  
            String encoding = selectEncoding(request);  //���Ϊ���ȴ�web.xml�еõ�  
            if (encoding != null){  
                request.setCharacterEncoding(encoding); //�����ַ�������  
            }  
        }  
        chain.doFilter(request, response);  
    }  
  
    /* ��ʼ�������� 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
     */  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.filterConfig = filterConfig;     
        this.encoding = filterConfig.getInitParameter("encoding");  //��web.xml�ļ��ж�ȡencoding��ֵ  
        String value = filterConfig.getInitParameter("ignore");     //��web.xml�ļ��ж�ȡignore��ֵ  
        //�������������Ϊ���Դ�Сд  
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
     * �õ��ַ����� 
     * @param request 
     * @return 
     */  
    protected String selectEncoding(ServletRequest request) {  
        return (this.encoding);  
    }  
}  