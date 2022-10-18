package ch11;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerExam
 *
 */
@WebListener
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {

    // 리스너 생성자
    public ListenerExam() {
        // TODO Auto-generated constructor stub
    }

    // ServletContext 시작
    public void contextInitialized(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 시작됨!!");
    }

    // ServletContext 종료
    public void contextDestroyed(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 종료됨!!");
    }

    // ServletContext에 속성 추가
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	scae.getServletContext().log("ServletContext 속성 추가: "+scae.getValue());
    }

    // ServletContext에 속성 대치
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    }        

    // ServletContext에서 속성 삭제
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	se.getSession().getServletContext().log("Session 생성됨:"+se.getSession().getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
        // TODO Auto-generated method stub
    }


	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	se.getSession().getServletContext().log("Session 속성 추가: "+se.getValue());
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

}
