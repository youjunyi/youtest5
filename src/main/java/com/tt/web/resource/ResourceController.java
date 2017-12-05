package com.tt.web.resource;

import com.tt.entity.Resource;
import com.tt.service.account.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController
{
    @Autowired
    private ResourceService resourceService;
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value="sortType",defaultValue="auto") String sortType,
                       @RequestParam(value="page",defaultValue="1")int pageNumber, Model model, ServletRequest request){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<Resource> page = resourceService.getResource(searchParams,pageNumber,sortType);
        return null;
        
    }
    
}
