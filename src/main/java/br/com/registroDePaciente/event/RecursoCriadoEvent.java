package br.com.registroDePaciente.event;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author alciran
 */
public class RecursoCriadoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    
    private HttpServletResponse response;
    private int id;
    
    public RecursoCriadoEvent(Object source, HttpServletResponse response, int id) {
		super(source);
		this.response = response;
		this.id = id;
		
    }
    
    public HttpServletResponse getResponse() {
		return response;
    }

    public int getId() {
        return id;
    }
    
    
}
