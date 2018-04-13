package com.winterframework.logistics.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.winterframework.logistics.base.http.RequestContext;
import com.winterframework.logistics.base.model.Request;
import com.winterframework.logistics.base.model.Response;
import com.winterframework.logistics.portal.dto.PtlNewsWebRequest;
import com.winterframework.logistics.portal.dto.PtlNewsWebResponse;
import com.winterframework.logistics.portal.entity.News;
import com.winterframework.logistics.portal.enums.EPtlNews;
import com.winterframework.logistics.portal.exception.PortalException;
import com.winterframework.logistics.portal.service.IPtlNewsService;

@Controller("ptlNewsController")
@RequestMapping("/portal/news")
public class PtlNewsController {
	private static final Logger log = LoggerFactory.getLogger(PtlNewsController.class);
	@Resource(name = "ptlNewsServiceImpl")
	IPtlNewsService ptlNewsService;

	/**
	 * 新闻列表
	 * 
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/showNewsList")
	public @ResponseBody Response<List<PtlNewsWebResponse>> showNewsList(
			@RequestBody Request<Map<String, String>> request) throws PortalException {
		log.info("PtlNewsController:/portal/news/showNewsList");
		Response<List<PtlNewsWebResponse>> response = new Response<>();
		int count = ptlNewsService.getCount(EPtlNews.NEWS_STATUS_PUBLISHED.getValue());
		List<News> nList = ptlNewsService.getEntitys(EPtlNews.NEWS_STATUS_PUBLISHED.getValue(), request.getPager(),
				count);
		List<PtlNewsWebResponse> ptlNewsWebResponses = new ArrayList<PtlNewsWebResponse>();
		for (News n : nList) {
			PtlNewsWebResponse ptlNewsWebResponse = new PtlNewsWebResponse();
			ptlNewsWebResponse.setId(n.getId());
			ptlNewsWebResponse.setType(n.getType());
			ptlNewsWebResponse.setTitle(n.getTitle());
			ptlNewsWebResponse.setStatus(n.getStatus());
			ptlNewsWebResponse.setCreatorId(n.getCreatorId());
			ptlNewsWebResponse.setCreateTime(n.getCreateTime());
			ptlNewsWebResponse.setContent(n.getContent());
			ptlNewsWebResponses.add(ptlNewsWebResponse);
		}
		response.setData(ptlNewsWebResponses);
		response.setCount(count);
		return response;
	}

	/**
	 * 新闻详情
	 * 
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/showNewsDetails")
	public @ResponseBody Response<PtlNewsWebResponse> showNewsDetails(@RequestBody Request<Map<String, Long>> request)
			throws PortalException {
		log.info("PtlNewsController:/portal/news/newsDetails");
		Long id = Optional.ofNullable(request.getData().get("id")).orElse(0L);
		News news = ptlNewsService.getEntity(id);
		Response<PtlNewsWebResponse> response = new Response<>();
		PtlNewsWebResponse ptlNewsWebResponse = new PtlNewsWebResponse();
		ptlNewsWebResponse.setId(news.getId());
		ptlNewsWebResponse.setType(news.getType());
		ptlNewsWebResponse.setTitle(news.getTitle());
		ptlNewsWebResponse.setStatus(news.getStatus());
		ptlNewsWebResponse.setCreatorId(news.getCreatorId());
		ptlNewsWebResponse.setCreateTime(news.getCreateTime());
		ptlNewsWebResponse.setContent(news.getContent());
		response.setData(ptlNewsWebResponse);
		return response;
	}

	/**
	 * 创建新闻
	 * 
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/addNews")
	public @ResponseBody Response<Object> addNews(@RequestBody Request<PtlNewsWebRequest> request)
			throws PortalException {
		log.info("PtlNewsController:/portal/news/addNews");
		Long userId = RequestContext.getUserId();
		News news = new News();
		news.setType(request.getData().getType());
		news.setTitle(request.getData().getTitle());
		news.setContent(request.getData().getContent());
		news.setStatus(EPtlNews.NEWS_STATUS_BUILTED.getValue());
		news.setCreateTime(System.currentTimeMillis());
		news.setCreatorId(userId);
		int result = ptlNewsService.addNews(news);
		Response<Object> response = new Response<>();
		response.setStatus(result);
		return response;

	}

	/**
	 * 删除新闻
	 * 
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/deleteNews")
	public @ResponseBody Response<Object> deleteNews(@RequestBody Request<Map<String, Long>> request)
			throws PortalException {
		log.info("PtlNewsController:/portal/news/deleteNews");
		Long userId = RequestContext.getUserId();
		Long newsId = request.getData().get("id");
		News news = new News();
		news.setId(newsId);
		news.setStatus(EPtlNews.NEWS_STATUS_CANCELED.getValue());
		news.setUpdatorId(userId);
		news.setUpdateTime(System.currentTimeMillis());
		int result = ptlNewsService.updateNews(news);
		Response<Object> response = new Response<>();
		response.setStatus(result);
		return response;

	}
	
	/**
	 * 修改新闻状态
	 * @param request
	 * @return
	 * @throws PortalException
	 */
	@RequestMapping("/updateNewsStatus")
	public @ResponseBody Response<Object> updateNewsStatus(@RequestBody Request<PtlNewsWebRequest> request) throws PortalException {
		log.info("PtlNewsController:/portal/news/updateNewsStatus");
		Long userId = RequestContext.getUserId();
		News news = new News();
		news.setId(request.getData().getId());
		news.setStatus(request.getData().getStatus());
		news.setCreatorId(userId);
		news.setCreateTime(System.currentTimeMillis());
		int result = ptlNewsService.updateNews(news);
		Response<Object> response = new Response<>();
		response.setStatus(result);
		return response;
	}

}
