package be.ehb.opdracht_form_handeling_internationalisatie.controllers;

import be.ehb.opdracht_form_handeling_internationalisatie.model.Blogpost;
import be.ehb.opdracht_form_handeling_internationalisatie.model.BlogpostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {
    @Autowired
    BlogpostDAO dao;

    @ModelAttribute(value = "alleBlogposts")
    public Iterable<Blogpost> getAllBlogposts(){
        return dao.findAll();
    }
    //model atribute vooe één nieuwe blogpost
    @ModelAttribute(value = "nBlogpost")
    public Blogpost blogpostToSave(){
        return new Blogpost();
    }
    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.GET)
    public String showIndex(ModelMap map){
        return "index";
    }
    //opslaan blogpost
    @RequestMapping(value = {"","/","/index"}, method = RequestMethod.POST)
    public String saveBlogpost (@ModelAttribute("nBlogpost")@Valid Blogpost nBlogpost, BindingResult bindingResult){
    if(bindingResult.hasErrors())
        return "index";
    dao.save(nBlogpost);
    return "redict:/index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBlogpost(@PathVariable(value = "id")int id){
        dao.deleteById(id);
        return "redict:/index";
    }

}
