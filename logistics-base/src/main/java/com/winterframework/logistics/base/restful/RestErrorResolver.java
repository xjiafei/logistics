package com.winterframework.logistics.base.restful;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.ServletWebRequest;

import com.winterframework.logistics.base.model.Response;
import com.winterframework.spring.web.servlet.handler.RestError;
 

/**
 * A {@code RestErrorResolver} resolves an exception and produces a {@link RestError} instance that can be used
 * to render a Rest error representation to the response body.
 *
 * @author Les Hazlewood
 */
public interface RestErrorResolver {

    /**
     * Returns a {@code RestError} instance to render as the response body based on the given exception.
     *
     * @param request current {@link ServletWebRequest} that can be used to obtain the source request/response pair.
     * @param handler the executed handler, or <code>null</code> if none chosen at the time of the exception
     *                (for example, if multipart resolution failed)
     * @param ex      the exception that was thrown during handler execution
     * @return a resolved {@code RestError} instance to render as the response body or <code>null</code> for default
     *         processing
     */
	Response resolveError(HttpServletRequest request, Object handler, Exception ex);
}
