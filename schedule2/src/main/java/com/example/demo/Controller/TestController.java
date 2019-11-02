package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController



public class TestController {
    @RequestMapping(value="/greeting",method = RequestMethod.POST) // it is mapped to the TestControlle
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";   // html name
    }


}
